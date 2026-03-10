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
            x ^= x >>> 17; // Javaでは論理右シフトが必須
            x ^= x << 5;
            return x;
        }

        public long randRange(long stop) {
            // 負の値を避けるために符号ビットを考慮
            return (next() & Long.MAX_VALUE) % stop;
        }

        public double random() {
            // doubleの仮数部53ビットに合わせて精度を最適化
            return (next() >>> 11) * 0x1.0p-53;
        }

        public boolean genBool(double p) {
            return random() < p;
        }
    }

    static Xorshift rng = new Xorshift(1);

    // 油田の配置情報
    static class OilLayout {
        long hash;
        double lnPRifX;
        double pxIfR;
        int[] topLefts;
        byte[] volume;

        OilLayout(long hash, double lnPRifX, double pxIfR, int[] topLefts, byte[] volume) {
            this.hash = hash;
            this.lnPRifX = lnPRifX;
            this.pxIfR = pxIfR;
            this.topLefts = topLefts.clone();
            this.volume = (volume != null) ? volume.clone() : null;
        }
    }

    static class OilShape {
        int maxI, maxJ;
        int[] coordinateIds;
        List<int[]> coordinates = new ArrayList<>();
        BitSet mask = new BitSet(400); // 20*20 = 400 bits
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

    static class OilState {
        List<List<Byte>> topLeftQueryVolumes;
        long[] hashes;

        OilState(int n2) {
            topLeftQueryVolumes = new ArrayList<>(n2);
            for (int i = 0; i < n2; i++) topLeftQueryVolumes.add(new ArrayList<>());
            hashes = new long[n2];
        }
    }

    static class State {
        OilState[] oilStates;
        int[] topLefts;
        byte[] volumes;
        List<Byte> queryVolumes = new ArrayList<>();
        long hash;
        Input input;

        State(Input input) {
            this.input = input;
            this.oilStates = new OilState[input.m];
            for (int i = 0; i < input.m; i++) {
                this.oilStates[i] = new OilState(input.n2);
                // Zobrist Hashの初期化
                if (i > 0 && Arrays.equals(input.oils[i-1].coordinateIds, input.oils[i].coordinateIds)) {
                    this.oilStates[i].hashes = this.oilStates[i-1].hashes;
                } else {
                    for (int ij = 0; ij < input.n2; ij++) this.oilStates[i].hashes[ij] = rng.next();
                }
            }
            this.topLefts = new int[input.m];
            this.volumes = new byte[input.n2];
            this.hash = 0;
            for (OilState os : oilStates) this.hash ^= os.hashes;
        }

        void moveTo(int oilId, int newTopLeft) {
            OilState os = oilStates[oilId];
            hash ^= os.hashes[topLefts[oilId]] ^ os.hashes[newTopLeft];
            for (int q = 0; q < queryVolumes.size(); q++) {
                int diff = (os.topLeftQueryVolumes.get(newTopLeft).get(q) & 0xFF) 
                         - (os.topLeftQueryVolumes.get(topLefts[oilId]).get(q) & 0xFF);
                queryVolumes.set(q, (byte)((queryVolumes.get(q) & 0xFF) + diff));
            }
            if (volumes != null && volumes.length > 0) {
                for (int ij : input.oils[oilId].coordinateIds) {
                    volumes[ij + topLefts[oilId]]--;
                    volumes[ij + newTopLeft]++;
                }
            }
            topLefts[oilId] = newTopLeft;
        }

        void addQuery(List<Integer> queryCoords) {
            boolean[] inQ = new boolean[input.n2];
            for (int ij : queryCoords) inQ[ij] = true;

            for (int i = 0; i < input.m; i++) {
                OilShape oil = input.oils[i];
                for (int di = 0; di < input.n - oil.maxI; di++) {
                    for (int dj = 0; dj < input.n - oil.maxJ; dj++) {
                        int tl = di * input.n + dj;
                        byte c = 0;
                        for (int ij : oil.coordinateIds) if (inQ[tl + ij]) c++;
                        oilStates[i].topLeftQueryVolumes.get(tl).add(c);
                    }
                }
            }
            byte[] currentVol = input.getVolume(topLefts);
            byte c = 0;
            for (int ij : queryCoords) c += currentVol[ij];
            queryVolumes.add(c);
        }
    }

    static class Sim {
        int n, n2, m, total, rem;
        double eps;
        List<List<Double>> lnPrIfSQuery = new ArrayList<>();
        List<List<Integer>> failed = new ArrayList<>();
        Scanner sc;

        Sim(Input input, Scanner sc) {
            this.n = input.n; this.n2 = input.n2; this.m = input.m;
            this.total = input.total; this.eps = input.eps;
            this.rem = n * n * 2; this.sc = sc;
        }

        // 誤差関数の近似
        double erf(double x) {
            double a1 = 0.254829592, a2 = -0.284496736, a3 = 1.421413741, a4 = -1.453152027, a5 = 1.061405429, p = 0.3275911;
            int sign = (x < 0) ? -1 : 1;
            x = Math.abs(x);
            double t = 1.0 / (1.0 + p * x);
            double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);
            return sign * y;
        }

        double normalCdf(double mu, double sigma, double x) {
            return 0.5 * (1.0 + erf((x - mu) / (sigma * Math.sqrt(2.0))));
        }

        double likelihood(double mu, double sigma, int res) {
            double b = res + 0.5;
            if (res == 0) return normalCdf(mu, sigma, b);
            return normalCdf(mu, sigma, b) - normalCdf(mu, sigma, res - 0.5);
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

        double getLnPRifX(OilState[] os, byte[] vol, int[] tls) {
            // 失敗した配置との比較
            for (List<Integer> f : failed) {
                int count = 0;
                for (int i = 0; i < n2; i++) if (vol[i] > 0) count++;
                if (count != f.size()) continue;
                boolean same = true;
                for (int ij : f) if (vol[ij] == 0) { same = false; break; }
                if (same) return -1e20;
            }
            double lnPR = 0.0;
            for (int q = 0; q < lnPrIfSQuery.size(); q++) {
                int S = 0;
                for (int i = 0; i < m; i++) S += os[i].topLeftQueryVolumes.get(tls[i]).get(q) & 0xFF;
                lnPR += lnPrIfSQuery.get(q).get(S);
            }
            return lnPR;
        }
        
        void giveup() { System.exit(0); }
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
                os.maxI = Math.max(os.maxI, os.coordinates.get(j));
                os.maxJ = Math.max(os.maxJ, os.coordinates.get(j));
                os.coordinateIds[j] = os.coordinates.get(j) * input.n + os.coordinates.get(j);
            }
        }

        Sim sim = new Sim(input, sc);
        State state = new State(input);
        List<OilLayout> pool = new ArrayList<>();
        int ITER = 4000000 / (2 * input.n2);
        long start = System.currentTimeMillis();

        for (int t = 0; ; t++) {
            if (System.currentTimeMillis() - start > 2900) sim.giveup();

            // プール内の重複管理と配置更新
            Map<Long, Double> hashLikelihood = new HashMap<>();
            for (OilLayout l : pool) hashLikelihood.put(l.hash, l.lnPRifX);

            // ランダムに候補を生成
            for (int i = 0; i < ITER; i++) {
                for (int m = 0; m < input.m; m++) {
                    int ri = (int)rng.randRange(input.n - input.oils[m].maxI);
                    int rj = (int)rng.randRange(input.n - input.oils[m].maxJ);
                    state.moveTo(m, ri * input.n + rj);
                }
                if (!hashLikelihood.containsKey(state.hash)) {
                    hashLikelihood.put(state.hash, 0.0);
                    pool.add(new OilLayout(state.hash, 0, 0, state.topLefts, state.volumes));
                }
            }

            // 尤度の更新とソート
            for (OilLayout l : pool) {
                if (l.volume == null) l.volume = input.getVolume(l.topLefts);
                l.lnPRifX = sim.getLnPRifX(state.oilStates, l.volume, l.topLefts);
            }
            Collections.shuffle(pool, new Random(rng.next()));
            pool.sort((a, b) -> Double.compare(b.lnPRifX, a.lnPRifX));

            // 事後確率の計算
            double maxLn = pool.get(0).lnPRifX, sumPx = 0;
            for (OilLayout l : pool) {
                l.pxIfR = Math.exp(l.lnPRifX - maxLn);
                sumPx += l.pxIfR;
            }
            for (OilLayout l : pool) l.pxIfR /= sumPx;

            // 尤度が低いものを削除
            while (pool.size() > 1 && pool.get(pool.size()-1).pxIfR < 1e-9) pool.remove(pool.size()-1);

            // 推測または占い
            if (pool.get(0).pxIfR > 0.9) {
                BitSet bits = input.getPositives(pool.get(0).topLefts);
                List<Integer> T = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) if (bits.get(i)) T.add(i);
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
