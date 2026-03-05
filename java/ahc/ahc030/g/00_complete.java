import java.util.*;
import java.io.*;

public class Main {
    static final double SMALL_VALUE = 1e-6;
    static final int[][] DIJ = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static final double TIME_LIMIT = 2.9;

    // 乱数生成器 (Xorshift) - 論理右シフト >>> を使用
    static class Xorshift {
        private long x;
        public Xorshift(long seed) { this.x = seed; }
        public long next() {
            x ^= x << 13;
            x ^= x >>> 17;
            x ^= x << 5;
            return x;
        }
        public long randRange(long stop) {
            return (next() & Long.MAX_VALUE) % stop;
        }
        public double random() {
            return (next() >>> 11) * 0x1.0p-53;
        }
        public boolean genBool(double p) {
            return random() < p;
        }
    }

    static Xorshift rng = new Xorshift(1);

    // 配置情報クラス
    static class OilLayout {
        long hash;
        double lnPRifX;
        double pxIfR;
        int[] topLefts;
        byte[] volume;

        OilLayout(long hash, double lnPR, double px, int[] tls, byte[] vol) {
            this.hash = hash;
            this.lnPRifX = lnPR;
            this.pxIfR = px;
            this.topLefts = tls.clone();
            this.volume = (vol != null) ? vol.clone() : null;
        }
    }

    // 油田形状クラス
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
            BitSet pos = new BitSet(n2);
            for (int i = 0; i < m; i++) {
                for (int id : oils[i].coordinateIds) pos.set(id + topLefts[i]);
            }
            return pos;
        }

        byte[] getVolume(int[] topLefts) {
            byte[] vol = new byte[n2];
            for (int i = 0; i < m; i++) {
                int pij = topLefts[i];
                for (int id : oils[i].coordinateIds) vol[id + pij]++;
            }
            return vol;
        }
    }

    // 差分更新のための状態保持
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
                if (i > 0 && Arrays.equals(input.oils[i-1].coordinateIds, input.oils[i].coordinateIds)) {
                    this.oilStates[i].hashes = this.oilStates[i-1].hashes;
                } else {
                    for (int j = 0; j < input.n2; j++) this.oilStates[i].hashes[j] = rng.next();
                }
            }
            this.hash = 0;
            for (OilState os : oilStates) this.hash ^= os.hashes;
            this.topLefts = new int[input.m];
            this.volumes = new byte[input.n2];
        }

        void moveTo(int oilId, int newTL) {
            OilState os = oilStates[oilId];
            hash ^= os.hashes[topLefts[oilId]] ^ os.hashes[newTL];
            for (int q = 0; q < queryVolumes.size(); q++) {
                int diff = (os.topLeftQueryVolumes.get(newTL).get(q) & 0xFF) 
                         - (os.topLeftQueryVolumes.get(topLefts[oilId]).get(q) & 0xFF);
                queryVolumes.set(q, (byte)((queryVolumes.get(q) & 0xFF) + diff));
            }
            if (volumes != null) {
                for (int id : input.oils[oilId].coordinateIds) {
                    volumes[id + topLefts[oilId]]--;
                    volumes[id + newTL]++;
                }
            }
            topLefts[oilId] = newTL;
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
                    for (int id : oil.coordinateIds) if (inQ[tl + id]) count++;
                    oilStates[i].topLeftQueryVolumes.get(tl).add(count);
                }
            }
            byte[] currentVol = input.getVolume(topLefts);
            byte sum = 0;
            for (int ij : coords) sum += currentVol[ij];
            queryVolumes.add(sum);
        }
    }

    // シミュレーターと統計計算
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

        double erf(double x) {
            double p = 0.3275911, a1 = 0.254829592, a2 = -0.284496736, a3 = 1.421413741, a4 = -1.453152027, a5 = 1.061405429;
            int sign = (x < 0) ? -1 : 1; x = Math.abs(x);
            double t = 1.0 / (1.0 + p * x);
            return sign * (1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x));
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
            // 尤度が-infになる際の連続性を担保
            for (int i = 0; i < lnPr.size() - 1; i++) if (!lnPr.get(i).isInfinite() && lnPr.get(i+1).isInfinite()) lnPr.set(i+1, lnPr.get(i) - 10.0);
            for (int i = lnPr.size() - 1; i > 0; i--) if (!lnPr.get(i).isInfinite() && lnPr.get(i-1).isInfinite()) lnPr.set(i-1, lnPr.get(i) - 10.0);
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

        double lnProbState(State state) {
            double prob = 0.0;
            for (int q = 0; q < lnPrIfSQuery.size(); q++) prob += lnPrIfSQuery.get(q).get(state.queryVolumes.get(q) & 0xFF);
            return prob;
        }
        
        void giveup() { System.exit(0); }
    }

    // 相互情報量の評価
    static class QueryEvaluator {
        boolean[] inQ;
        int[] layoutVols;
        int k;
        List<OilLayout> pool;

        QueryEvaluator(Input in, List<OilLayout> pool) {
            this.inQ = new boolean[in.n2];
            this.layoutVols = new int[pool.size()];
            this.pool = pool;
        }

        void flip(int ij) {
            int sign = inQ[ij] ? -1 : 1;
            for (int x = 0; x < pool.size(); x++) layoutVols[x] += sign * (pool.get(x).volume[ij] & 0xFF);
            k += sign;
            inQ[ij] = !inQ[ij];
        }

        double eval(Sim sim, Input input, int addK, int addV) {
            int tk = k + addK;
            if (tk == 0) return -1e100;
            double[] pr_r = new double[sim.total + 1];
            for (int x = 0; x < pool.size(); x++) {
                int s = layoutVols[x] + addV;
                double mu = (tk - s) * input.eps + s * (1.0 - input.eps);
                double sig = Math.sqrt(tk * input.eps * (1.0 - input.eps));
                for (int r = 0; r <= sim.total; r++) pr_r[r] += sim.likelihood(mu, sig, r) * pool.get(x).pxIfR;
            }
            double info = 0.0;
            for (int x = 0; x < pool.size(); x++) {
                int s = layoutVols[x] + addV;
                double mu = (tk - s) * input.eps + s * (1.0 - input.eps);
                double sig = Math.sqrt(tk * input.eps * (1.0 - input.eps));
                for (int r = 0; r <= sim.total; r++) {
                    double p_r_x = sim.likelihood(mu, sig, r);
                    if (p_r_x > SMALL_VALUE && pr_r[r] > SMALL_VALUE) info += p_r_x * pool.get(x).pxIfR * (Math.log(p_r_x) - Math.log(pr_r[r]));
                }
            }
            return info * Math.sqrt(tk);
        }
    }

    // 焼きなまし法
    static void simulatedAnnealing(Input input, Sim sim, State state, List<OilLayout> pool, int[][][] swaps, Map<Long, Double> hashL, int iter, long startT) {
        double crt = pool.get(0).lnPRifX;
        for (int i = 0; i < input.m; i++) state.moveTo(i, pool.get(0).topLefts[i]);
        double maxCrt = crt;
        iter = (int)(iter * Math.min(3.0 - (System.currentTimeMillis() - startT)/1000.0, 1.0));

        for (int t = 0; t < iter; t++) {
            double temp = 2.0 + (1.0 - 2.0) * t / iter;
            int coin = (int)rng.randRange(100);
            int m1 = (int)rng.randRange(input.m);
            int bk1 = state.topLefts[m1];

            if (coin < 30) { // Slide neighbor
                int[] d = DIJ[(int)rng.randRange(4)];
                int ni = bk1 / input.n + d, nj = bk1 % input.n + d;
                if (ni >= 0 && nj >= 0 && ni < input.n - input.oils[m1].maxI && nj < input.n - input.oils[m1].maxJ) {
                    state.moveTo(m1, ni * input.n + nj);
                    processSA(state, sim, hashL, pool, maxCrt, crt, temp, m1, bk1);
                }
            } else if (coin < 40) { // Warp neighbor
                state.moveTo(m1, (int)rng.randRange(input.n - input.oils[m1].maxI) * input.n + (int)rng.randRange(input.n - input.oils[m1].maxJ));
                processSA(state, sim, hashL, pool, maxCrt, crt, temp, m1, bk1);
            } else { // Swap neighbor
                int m2 = (int)rng.randRange(input.m);
                if (m1 == m2) continue;
                int bk2 = state.topLefts[m2];
                int[] d1 = swaps[m2][m1][(int)rng.randRange(swaps[m1][m2].length)];
                int[] d2 = swaps[m1][m2][(int)rng.randRange(swaps[m2][m1].length)];
                int ni1 = (bk2 / input.n) + d1, nj1 = (bk2 % input.n) + d1;
                int ni2 = (bk1 / input.n) + d2, nj2 = (bk1 % input.n) + d2;
                if (ni1 >= 0 && nj1 >= 0 && ni1 < input.n - input.oils[m1].maxI && nj1 < input.n - input.oils[m1].maxJ &&
                    ni2 >= 0 && nj2 >= 0 && ni2 < input.n - input.oils[m2].maxI && nj2 < input.n - input.oils[m2].maxJ) {
                    state.moveTo(m1, ni1 * input.n + nj1);
                    state.moveTo(m2, ni2 * input.n + nj2);
                    double next = hashL.getOrDefault(state.hash, sim.lnProbState(state));
                    if (!hashL.containsKey(state.hash)) {
                        hashL.put(state.hash, next);
                        if (next - maxCrt >= -10.0) pool.add(new OilLayout(state.hash, next, 0.0, state.topLefts, state.volumes));
                    }
                    if (crt <= next || rng.genBool(Math.exp((next - crt) / temp))) crt = next;
                    else { state.moveTo(m1, bk1); state.moveTo(m2, bk2); }
                }
            }
            if (maxCrt < crt) maxCrt = crt;
        }
    }

    static double processSA(State s, Sim sim, Map<Long, Double> hL, List<OilLayout> p, double max, double crt, double temp, int m, int bk) {
        double next = hL.getOrDefault(s.hash, sim.lnProbState(s));
        if (!hL.containsKey(s.hash)) {
            hL.put(s.hash, next);
            if (next - max >= -10.0) p.add(new OilLayout(s.hash, next, 0.0, s.topLefts, s.volumes));
        }
        if (crt <= next || rng.genBool(Math.exp((next - crt) / temp))) return next;
        else { s.moveTo(m, bk); return crt; }
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
                os.mask.set(os.coordinateIds[j]);
            }
        }

        // Swaps precomputation
        int[][][] swaps = new int[input.m][input.m][];
        for (int a = 0; a < input.m; a++) {
            for (int b = 0; b < input.m; b++) {
                if (a == b) continue;
                List<int[]> list = new ArrayList<>();
                for (int di = -input.oils[b].maxI; di <= input.oils[a].maxI; di++) {
                    for (int dj = -input.oils[b].maxJ; dj <= input.oils[a].maxJ; dj++) {
                        int vol = 0;
                        for (int id : input.oils[b].coordinateIds) {
                            int ni = id / input.n + di, nj = id % input.n + dj;
                            if (ni >= 0 && nj >= 0 && ni < input.n && nj < input.n) {
                                if (input.oils[a].mask.get(ni * input.n + nj)) vol++;
                            }
                        }
                        list.add(new int[]{vol, di, dj});
                    }
                }
                list.sort((x, y) -> y - x);
                int sz = Math.min(4, list.size());
                swaps[a][b] = new int[sz];
                for (int i = 0; i < sz; i++) { swaps[a][b][i] = list.get(i); swaps[a][b][i] = list.get(i); }
            }
        }

        Sim sim = new Sim(input, sc);
        State state = new State(input);
        List<OilLayout> pool = new ArrayList<>();
        long startT = System.currentTimeMillis();
        int saIter = 4000000 / (2 * input.n2);

        for (int t = 0; ; t++) {
            if ((System.currentTimeMillis() - startT) / 1000.0 > TIME_LIMIT) sim.giveup();
            for (OilLayout l : pool) l.lnPRifX = sim.getLnPRifX(state.oilStates, l.volume, l.topLefts);
            Collections.shuffle(pool, new Random(rng.next()));
            pool.sort((a, b) -> Double.compare(b.lnPRifX, a.lnPRifX));

            Map<Long, Double> hashL = new HashMap<>();
            for (OilLayout l : pool) hashL.put(l.hash, l.lnPRifX);

            if (t == 0) { // 初回ランダム生成
                for (int i = 0; i < saIter; i++) {
                    for (int m = 0; m < input.m; m++) state.moveTo(m, (int)rng.randRange(input.n - input.oils[m].maxI) * input.n + (int)rng.randRange(input.n - input.oils[m].maxJ));
                    if (!hashL.containsKey(state.hash)) {
                        hashL.put(state.hash, 0.0);
                        pool.add(new OilLayout(state.hash, 0.0, 0.0, state.topLefts, input.getVolume(state.topLefts)));
                    }
                }
            } else {
                simulatedAnnealing(input, sim, state, pool, swaps, hashL, saIter, startT);
            }

            double maxLn = pool.get(0).lnPRifX, sumPx = 0;
            for (OilLayout l : pool) { l.pxIfR = Math.exp(l.lnPRifX - maxLn); sumPx += l.pxIfR; }
            for (OilLayout l : pool) l.pxIfR /= sumPx;
            while (pool.size() > 1 && pool.get(pool.size()-1).pxIfR < 1e-9) pool.remove(pool.size()-1);

            double bestProb = pool.get(0).pxIfR;
            if (bestProb > 0.8) {
                BitSet bits = input.getPositives(pool.get(0).topLefts);
                List<Integer> T = new ArrayList<>(), revT = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) { if (bits.get(i)) T.add(i); else revT.add(i); }
                if (input.m <= 4 || (!sim.lnPrIfSQuery.isEmpty() && sim.rem < 100)) { // 簡略化した推測判断
                    if (sim.ans(T)) break;
                } else {
                    sim.query(revT); state.addQuery(revT);
                }
            } else {
                // 山登りによる相互情報量最大の占い生成
                QueryEvaluator qe = new QueryEvaluator(input, pool);
                double bestEval = -1e100;
                for (int iter = 0; iter < 3; iter++) {
                    for (int i = 0; i < input.n2; i++) {
                        qe.flip(i);
                        double e = qe.eval(sim, input, 0, 0);
                        if (e > bestEval) bestEval = e; else qe.flip(i);
                    }
                }
                List<Integer> coords = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) if (qe.inQ[i]) coords.add(i);
                if (coords.isEmpty()) coords.add(0);
                sim.query(coords); state.addQuery(coords);
            }
        }
    }
}
