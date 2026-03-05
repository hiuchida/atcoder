import java.util.*;
import java.io.*;

public class Main {
    static final double SMALL_VALUE = 1e-6;

    // 乱数生成器 (Xorshift)
    static class Xorshift {
        private long x;
        public Xorshift(long seed) { this.x = seed; }
        public long next() {
            x ^= x << 13;
            x ^= x >>> 17; // 論理右シフト
            x ^= x << 5;
            return x;
        }
        public long randRange(long stop) {
            return (next() & Long.MAX_VALUE) % stop;
        }
        public double random() {
            return (next() >>> 11) * 0x1.0p-53;
        }
    }

    static Xorshift rng = new Xorshift(1);

    // 油田の配置情報
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

    // 油田の形状情報
    static class OilShape {
        int maxI, maxJ;
        int[] coordinateIds;
        List<int[]> coordinates = new ArrayList<>();
        BitSet mask = new BitSet(400);
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
            byte[] vol = new byte[n2];
            for (int i = 0; i < m; i++) {
                int pij = topLefts[i];
                for (int ij : oils[i].coordinateIds) {
                    vol[ij + pij]++;
                }
            }
            return vol;
        }
    }

    // 油田の状態管理（高速化のためのメモ化）
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
        List<Byte> queryVolumes = new ArrayList<>();
        Input input;

        State(Input input) {
            this.input = input;
            this.oilStates = new OilState[input.m];
            for (int i = 0; i < input.m; i++) oilStates[i] = new OilState(input.n2);
            this.topLefts = new int[input.m];
            this.volumes = new byte[input.n2];
        }

        void moveTo(int oilId, int newTopLeft) {
            OilState os = oilStates[oilId];
            for (int q = 0; q < queryVolumes.size(); q++) {
                int oldVal = queryVolumes.get(q) & 0xFF;
                int diff = (os.topLeftQueryVolumes.get(newTopLeft).get(q) & 0xFF) 
                         - (os.topLeftQueryVolumes.get(topLefts[oilId]).get(q) & 0xFF);
                queryVolumes.set(q, (byte)(oldVal + diff));
            }
            for (int ij : input.oils[oilId].coordinateIds) {
                volumes[ij + topLefts[oilId]]--;
                volumes[ij + newTopLeft]++;
            }
            topLefts[oilId] = newTopLeft;
        }

        void addQuery(List<Integer> coords) {
            boolean[] inQ = new boolean[input.n2];
            for (int ij : coords) inQ[ij] = true;
            for (int i = 0; i < input.m; i++) {
                OilShape oil = input.oils[i];
                for (int tl = 0; tl < input.n2; tl++) {
                    int r = tl / input.n, c = tl % input.n;
                    if (r > input.n - oil.maxI - 1 || c > input.n - oil.maxJ - 1) {
                        oilStates[i].topLeftQueryVolumes.get(tl).add((byte)0);
                        continue;
                    }
                    byte count = 0;
                    for (int ij : oil.coordinateIds) if (inQ[tl + ij]) count++;
                    oilStates[i].topLeftQueryVolumes.get(tl).add(count);
                }
            }
            byte[] currentVol = input.getVolume(topLefts);
            byte sum = 0;
            for (int ij : coords) sum += currentVol[ij];
            queryVolumes.add(sum);
        }
    }

    static class Sim {
        int n, n2, m, total;
        double eps;
        int rem;
        List<List<Double>> lnPrIfSQuery = new ArrayList<>();
        List<List<Integer>> failed = new ArrayList<>();
        Scanner sc;

        Sim(Input input, Scanner sc) {
            this.n = input.n; this.n2 = input.n2; this.m = input.m;
            this.total = input.total; this.eps = input.eps;
            this.rem = n * n * 2; this.sc = sc;
        }

        double erf(double x) {
            double p = 0.3275911, a1 = 0.254829592, a2 = -0.284496736, a3 = 1.421413741, a4 = -1.453152027, a5 = 1.061405429;
            int sign = (x < 0) ? -1 : 1;
            x = Math.abs(x);
            double t = 1.0 / (1.0 + p * x);
            double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);
            return sign * y;
        }

        double normalCdf(double m, double s, double x) {
            return 0.5 * (1.0 + erf((x - m) / (s * Math.sqrt(2.0))));
        }

        double likelihood(double m, double s, int r) {
            double b = r + 0.5;
            if (r == 0) return normalCdf(m, s, b);
            return normalCdf(m, s, b) - normalCdf(m, s, r - 0.5);
        }

        int query(List<Integer> coords) {
            if (rem-- <= 0) System.exit(0);
            System.out.print("q " + coords.size());
            for (int ij : coords) System.out.print(" " + (ij / n) + " " + (ij % n));
            System.out.println();
            int res = sc.nextInt();
            List<Double> lnPr = new ArrayList<>();
            for (int s = 0; s <= total; s++) {
                double mu = (coords.size() - s) * eps + s * (1.0 - eps);
                double sig = Math.sqrt(coords.size() * eps * (1.0 - eps));
                lnPr.add(Math.log(likelihood(mu, sig, res)));
            }
            lnPrIfSQuery.add(lnPr);
            return res;
        }

        boolean ans(List<Integer> T) {
            if (rem-- <= 0) System.exit(0);
            System.out.print("a " + T.size());
            for (int ij : T) System.out.print(" " + (ij / n) + " " + (ij % n));
            System.out.println();
            int res = sc.nextInt();
            if (res == 1) return true;
            failed.add(T);
            return false;
        }

        double getLnPRifX(OilState[] osList, byte[] vol, int[] tls) {
            for (List<Integer> f : failed) {
                boolean same = true;
                int count = 0;
                for (int i = 0; i < n2; i++) if (vol[i] > 0) count++;
                if (count != f.size()) continue;
                for (int ij : f) if (vol[ij] == 0) { same = false; break; }
                if (same) return -1e20;
            }
            double lnPR = 0.0;
            for (int q = 0; q < lnPrIfSQuery.size(); q++) {
                int S = 0;
                for (int i = 0; i < m; i++) S += osList[i].topLeftQueryVolumes.get(tls[i]).get(q) & 0xFF;
                lnPR += lnPrIfSQuery.get(q).get(S);
            }
            return lnPR;
        }

        void giveup() { System.exit(0); }
    }

    // 占いの良さを評価するクラス
    static class Query {
        boolean[] inQuery;
        int[] layoutVolumes;
        int k;
        List<OilLayout> pool;

        Query(Input in, List<OilLayout> pool) {
            this.inQuery = new boolean[in.n2];
            this.layoutVolumes = new int[pool.size()];
            this.pool = pool;
        }

        void flip(int ij) {
            if (inQuery[ij]) {
                for (int x = 0; x < pool.size(); x++) layoutVolumes[x] -= pool.get(x).volume[ij];
                k--;
            } else {
                for (int x = 0; x < pool.size(); x++) layoutVolumes[x] += pool.get(x).volume[ij];
                k++;
            }
            inQuery[ij] = !inQuery[ij];
        }

        double eval(Sim sim, Input input) {
            if (k == 0) return -1e100;
            double[] pr_r = new double[sim.total + 2]; // 占い結果rの生起確率 p(r)
            for (int x = 0; x < pool.size(); x++) {
                int s = layoutVolumes[x];
                double mu = (k - s) * input.eps + s * (1.0 - input.eps);
                double sigma = Math.sqrt(k * input.eps * (1.0 - input.eps));
                for (int r = 0; r <= sim.total + 1; r++) {
                    pr_r[r] += sim.likelihood(mu, sigma, r) * pool.get(x).pxIfR;
                }
            }
            double info = 0.0;
            for (int x = 0; x < pool.size(); x++) {
                int s = layoutVolumes[x];
                double mu = (k - s) * input.eps + s * (1.0 - input.eps);
                double sigma = Math.sqrt(k * input.eps * (1.0 - input.eps));
                for (int r = 0; r <= sim.total + 1; r++) {
                    double p_r_x = sim.likelihood(mu, sigma, r);
                    if (p_r_x > SMALL_VALUE && pr_r[r] > SMALL_VALUE) {
                        info += p_r_x * pool.get(x).pxIfR * (Math.log(p_r_x) - Math.log(pr_r[r]));
                    }
                }
            }
            return info * Math.sqrt(k); // コスト 1/√k で割る = √k を掛ける
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Input input = new Input();
        input.n = sc.nextInt(); input.m = sc.nextInt(); input.eps = sc.nextDouble();
        input.n2 = input.n * input.n;
        input.oils = new OilShape[input.m];
        for (int i = 0; i < input.m; i++) {
            input.oils[i] = new OilShape();
            int size = sc.nextInt();
            for (int j = 0; j < size; j++) input.oils[i].coordinates.add(new int[]{sc.nextInt(), sc.nextInt()});
            input.total += size;
            OilShape os = input.oils[i];
            os.coordinateIds = new int[size];
            for (int j = 0; j < size; j++) {
                os.maxI = Math.max(os.maxI, os.coordinates.get(j)[0]);
                os.maxJ = Math.max(os.maxJ, os.coordinates.get(j)[1]);
                os.coordinateIds[j] = os.coordinates.get(j)[0] * input.n + os.coordinates.get(j)[1];
                os.mask.set(os.coordinateIds[j]);
            }
        }

        Sim sim = new Sim(input, sc);
        State state = new State(input);
        List<OilLayout> pool = new ArrayList<>();

        // M=2専用の全配置生成
        OilShape oilA = input.oils[0], oilB = input.oils[1];
        for (int iA = 0; iA <= input.n - oilA.maxI - 1; iA++) {
            for (int jA = 0; jA <= input.n - oilA.maxJ - 1; jA++) {
                for (int iB = 0; iB <= input.n - oilB.maxI - 1; iB++) {
                    for (int jB = 0; jB <= input.n - oilB.maxJ - 1; jB++) {
                        OilLayout l = new OilLayout(input.m, input.n2);
                        l.topLefts[0] = iA * input.n + jA;
                        l.topLefts[1] = iB * input.n + jB;
                        l.volume = input.getVolume(l.topLefts);
                        pool.add(l);
                    }
                }
            }
        }

        long start = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - start > 2800) sim.giveup();

            for (OilLayout l : pool) l.lnPRifX = sim.getLnPRifX(state.oilStates, l.volume, l.topLefts);
            Collections.shuffle(pool, new Random(1));
            pool.sort((a, b) -> Double.compare(b.lnPRifX, a.lnPRifX));

            double maxLn = pool.get(0).lnPRifX, sumPx = 0;
            for (OilLayout l : pool) {
                l.pxIfR = Math.exp(l.lnPRifX - maxLn);
                sumPx += l.pxIfR;
            }
            for (OilLayout l : pool) l.pxIfR /= sumPx;

            if (pool.get(0).pxIfR > 0.8) {
                BitSet bits = input.getPositives(pool.get(0).topLefts);
                List<Integer> T = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) if (bits.get(i)) T.add(i);
                if (sim.ans(T)) break;
            } else {
                // 山登り法で占いを決定
                Query q = new Query(input, pool);
                double currentEval = -1e100;
                for (int iter = 0; iter < 3; iter++) {
                    boolean changed = false;
                    List<Integer> indices = new ArrayList<>();
                    for (int i = 0; i < input.n2; i++) indices.add(i);
                    Collections.shuffle(indices, new Random(iter));
                    for (int ij : indices) {
                        q.flip(ij);
                        double nextEval = q.eval(sim, input);
                        if (nextEval > currentEval) { currentEval = nextEval; changed = true; }
                        else q.flip(ij);
                    }
                    if (!changed) break;
                }
                List<Integer> coords = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) if (q.inQuery[i]) coords.add(i);
                if (coords.isEmpty()) coords.add(0);
                sim.query(coords);
                state.addQuery(coords);
            }
        }
    }
}
