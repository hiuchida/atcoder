import java.util.*;
import java.io.*;

public class Main {
    static final double SMALL_VALUE = 1e-6;

    // 座標などのペアを扱うクラス
    static class Pair<T, U> {
        T first;
        U second;
        Pair(T first, U second) { this.first = first; this.second = second; }
    }

    // 論理右シフトを適切に扱う乱数生成器
    static class Xorshift {
        private long x;
        public Xorshift(long seed) { this.x = seed; }

        public long next() {
            x ^= x << 13;
            x ^= x >>> 17; // Javaでは論理右シフトを使用
            x ^= x << 5;
            return x;
        }

        public long randRange(long stop) {
            return Math.abs(next()) % stop;
        }

        public double random() {
            // 53ビットの精度で[0.0, 1.0]を生成
            return (next() >>> 11) * 0x1.0p-53;
        }

        public boolean genBool(double p) {
            return random() < p;
        }
    }

    static Xorshift rng = new Xorshift(1);

    static class OilLayout {
        double lnPRifX;
        double pxIfR;
        int[] topLefts;
        byte[] volume;

        OilLayout(int m, int n2) {
            this.topLefts = new int[m];
            this.volume = new byte[n2];
        }
    }

    static class OilShape {
        int maxI, maxJ;
        int[] coordinateIds;
        List<Pair<Integer, Integer>> coordinates;
        long[] mask = new long; // bitset<400>の代用（必要に応じて）

        // 簡易的なBitSetの実装
        BitSet bitMask = new BitSet(400);
    }

    static class Input {
        int n, n2, m;
        double eps;
        OilShape[] oils;
        int total;

        BitSet getPositives(int[] topLefts) {
            BitSet positives = new BitSet(n2);
            for (int i = 0; i < m; i++) {
                for (int id : oils[i].coordinateIds) {
                    positives.set(id + topLefts[i]);
                }
            }
            return positives;
        }

        byte[] getVolume(int[] topLefts) {
            byte[] volume = new byte[n2];
            for (int oilId = 0; oilId < m; oilId++) {
                int pij = topLefts[oilId];
                for (int ij : oils[oilId].coordinateIds) {
                    volume[ij + pij]++;
                }
            }
            return volume;
        }
    }

    static class OilState {
        List<List<Byte>> topLeftQueryVolumes;
        OilState(int n2) {
            topLeftQueryVolumes = new ArrayList<>(n2);
            for (int i = 0; i < n2; i++) topLeftQueryVolumes.add(new ArrayList<>());
        }
    }

    static class State {
        OilState[] oilStates;
        int[] topLefts;
        byte[] volumes;
        List<Byte> queryVolumes;
        Input input;

        State(Input input) {
            this.input = input;
            this.oilStates = new OilState[input.m];
            for (int i = 0; i < input.m; i++) this.oilStates[i] = new OilState(input.n2);
            this.topLefts = new int[input.m];
            this.volumes = new byte[input.n2];
            this.queryVolumes = new ArrayList<>();
        }

        void moveTo(int oilId, int newTopLeft) {
            OilState os = oilStates[oilId];
            for (int q = 0; q < queryVolumes.size(); q++) {
                int oldVal = queryVolumes.get(q) & 0xFF;
                int diff = (os.topLeftQueryVolumes.get(newTopLeft).get(q) & 0xFF) 
                         - (os.topLeftQueryVolumes.get(topLefts[oilId]).get(q) & 0xFF);
                queryVolumes.set(q, (byte)(oldVal + diff));
            }
            if (volumes != null) {
                for (int ij : input.oils[oilId].coordinateIds) {
                    volumes[ij + topLefts[oilId]]--;
                    volumes[ij + newTopLeft]++;
                }
            }
            topLefts[oilId] = newTopLeft;
        }

        void addQuery(List<Integer> queryCoordinates) {
            boolean[] inQuery = new boolean[input.n2];
            for (int ij : queryCoordinates) inQuery[ij] = true;

            for (int oilId = 0; oilId < input.m; oilId++) {
                OilShape oil = input.oils[oilId];
                OilState os = oilStates[oilId];
                for (int di = 0; di <= input.n - oil.maxI - 1; di++) {
                    for (int dj = 0; dj <= input.n - oil.maxJ - 1; dj++) {
                        int topLeft = di * input.n + dj;
                        byte c = 0;
                        for (int ij : oil.coordinateIds) {
                            if (inQuery[topLeft + ij]) c++;
                        }
                        os.topLeftQueryVolumes.get(topLeft).add(c);
                    }
                }
            }
            byte[] currentVol = input.getVolume(topLefts);
            byte c = 0;
            for (int ij : queryCoordinates) c += currentVol[ij];
            queryVolumes.add(c);
        }
    }

    static class Sim {
        int n, n2, m, total;
        double eps;
        int rem;
        List<List<Double>> lnPrIfSQuery = new ArrayList<>();
        List<List<Integer>> failed = new ArrayList<>();

        Sim(Input input) {
            this.n = input.n; this.n2 = input.n2; this.m = input.m;
            this.total = input.total; this.eps = input.eps;
            this.rem = n * n * 2;
        }

        // 誤差関数erfの近似実装
        double erf(double x) {
            double a1 =  0.254829592; double a2 = -0.284496736;
            double a3 =  1.421413741; double a4 = -1.453152027;
            double a5 =  1.061405429; double p  =  0.3275911;
            int sign = (x < 0) ? -1 : 1;
            x = Math.abs(x);
            double t = 1.0 / (1.0 + p * x);
            double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);
            return sign * y;
        }

        double normalCdf(double mean, double stdDev, double x) {
            return 0.5 * (1.0 + erf((x - mean) / (stdDev * Math.sqrt(2.0))));
        }

        double likelihood(double mean, double stdDev, int res) {
            double b = res + 0.5;
            if (res == 0) return normalCdf(mean, stdDev, b);
            return normalCdf(mean, stdDev, b) - normalCdf(mean, stdDev, res - 0.5);
        }

        int query(List<Integer> coords) {
            if (rem <= 0) System.exit(0);
            rem--;
            System.out.print("q " + coords.size());
            for (int ij : coords) System.out.print(" " + (ij / n) + " " + (ij % n));
            System.out.println();
            int ret = scanner.nextInt();

            List<Double> lnPrIfS = new ArrayList<>();
            for (int S = 0; S <= total; S++) {
                double mu = (coords.size() - S) * eps + S * (1.0 - eps);
                double sigma = Math.sqrt(coords.size() * eps * (1.0 - eps));
                lnPrIfS.add(Math.log(likelihood(mu, sigma, ret)));
            }
            lnPrIfSQuery.add(lnPrIfS);
            return ret;
        }

        boolean ans(List<Integer> T) {
            if (rem <= 0) System.exit(0);
            rem--;
            System.out.print("a " + T.size());
            for (int ij : T) System.out.print(" " + (ij / n) + " " + (ij % n));
            System.out.println();
            int ret = scanner.nextInt();
            if (ret == 1) return true;
            failed.add(T);
            return false;
        }

        double getLnPRifX(State state, byte[] layoutVol, int[] topLefts) {
            for (List<Integer> f : failed) {
                boolean same = true;
                int count = 0;
                for (int ij = 0; ij < n2; ij++) if (layoutVol[ij] > 0) count++;
                if (count != f.size()) continue;
                for (int ij : f) if (layoutVol[ij] == 0) { same = false; break; }
                if (same) return -1e20;
            }

            double lnPR = 0.0;
            for (int q = 0; q < lnPrIfSQuery.size(); q++) {
                int S = 0;
                for (int oilId = 0; oilId < m; oilId++) {
                    S += state.oilStates[oilId].topLeftQueryVolumes.get(topLefts[oilId]).get(q) & 0xFF;
                }
                lnPR += lnPrIfSQuery.get(q).get(S);
            }
            return lnPR;
        }

        void giveup() {
            // BFS等で全掘りする処理（省略可）
            System.exit(0);
        }
    }

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Input input = new Input();
        input.n = scanner.nextInt();
        input.m = scanner.nextInt();
        input.eps = scanner.nextDouble();
        input.n2 = input.n * input.n;
        input.oils = new OilShape[input.m];
        for (int i = 0; i < input.m; i++) {
            int size = scanner.nextInt();
            input.oils[i] = new OilShape();
            input.oils[i].coordinates = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                input.oils[i].coordinates.add(new Pair<>(scanner.nextInt(), scanner.nextInt()));
            }
            input.total += size;
            OilShape os = input.oils[i];
            os.coordinateIds = new int[size];
            for (int j = 0; j < size; j++) {
                os.maxI = Math.max(os.maxI, os.coordinates.get(j).first);
                os.maxJ = Math.max(os.maxJ, os.coordinates.get(j).second);
                os.coordinateIds[j] = os.coordinates.get(j).first * input.n + os.coordinates.get(j).second;
            }
        }

        Sim sim = new Sim(input);
        State state = new State(input);
        List<OilLayout> pool = new ArrayList<>();

        // M=2の全配置生成
        if (input.m == 2) {
            for (int i0 = 0; i0 <= input.n - input.oils.maxI - 1; i0++) {
                for (int j0 = 0; j0 <= input.n - input.oils.maxJ - 1; j0++) {
                    state.topLefts = i0 * input.n + j0;
                    for (int i1 = 0; i1 <= input.n - input.oils.maxI - 1; i1++) {
                        for (int j1 = 0; j1 <= input.n - input.oils.maxJ - 1; j1++) {
                            state.topLefts = i1 * input.n + j1;
                            OilLayout layout = new OilLayout(input.m, input.n2);
                            System.arraycopy(state.topLefts, 0, layout.topLefts, 0, input.m);
                            layout.volume = input.getVolume(layout.topLefts);
                            pool.add(layout);
                        }
                    }
                }
            }
        }

        long startTime = System.currentTimeMillis();

        while (true) {
            if (System.currentTimeMillis() - startTime > 2800) {
                sim.giveup(); break;
            }

            // 尤度の更新
            for (OilLayout l : pool) {
                l.lnPRifX = sim.getLnPRifX(state, l.volume, l.topLefts);
            }

            // ソート
            pool.sort((a, b) -> Double.compare(b.lnPRifX, a.lnPRifX));

            double maxLn = pool.get(0).lnPRifX;
            double sumPx = 0;
            for (OilLayout l : pool) {
                l.pxIfR = Math.exp(l.lnPRifX - maxLn);
                sumPx += l.pxIfR;
            }
            for (OilLayout l : pool) l.pxIfR /= sumPx;

            if (pool.get(0).pxIfR > 0.8) {
                BitSet bestBits = input.getPositives(pool.get(0).topLefts);
                List<Integer> T = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) if (bestBits.get(i)) T.add(i);
                if (sim.ans(T)) break;
            } else {
                // ランダムな占い
                List<Integer> q = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) if (rng.genBool(0.5)) q.add(i);
                if (q.isEmpty()) q.add(0);
                sim.query(q);
                state.addQuery(q);
            }
        }
    }
}
