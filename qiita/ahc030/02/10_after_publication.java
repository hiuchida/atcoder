import java.util.*;
import java.io.*;

public class Main {
    static final double SMALL_VALUE = 1e-6;
    static final double TIME_LIMIT = 3.0;
    static final int[][] DIJ = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

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

    // グローバルな配置プール
    static List<OilLayout> pool = new ArrayList<>();

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
        double difficulty;

        void setDifficulty() {
            // N^2 * M^2 * eps > 2000 を難易度の閾値とする
            this.difficulty = n2 * m * m * eps;
        }

        boolean isDifficult() {
            return difficulty > 2000;
        }

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
            for (OilState os : oilStates) this.hash ^= os.hashes[0];
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

    static class Sim {
        int n, n2, m, total, rem;
        double eps;
        List<List<Integer>> queries = new ArrayList<>();
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
            queries.add(coords);
            List<Double> lnPr = new ArrayList<>();
            for (int s = 0; s <= total; s++) {
                double mu = (coords.size() - s) * eps + s * (1.0 - eps);
                double sig = Math.sqrt(coords.size() * eps * (1.0 - eps));
                lnPr.add(Math.log(likelihood(mu, sig, res)));
            }
            // 尤度の不連続性を補正
            for (int i = 0; i < lnPr.size() - 1; i++) if (Double.isInfinite(lnPr.get(i+1)) && !Double.isInfinite(lnPr.get(i))) lnPr.set(i+1, lnPr.get(i) - 10.0);
            for (int i = lnPr.size() - 1; i > 0; i--) if (Double.isInfinite(lnPr.get(i-1)) && !Double.isInfinite(lnPr.get(i))) lnPr.set(i-1, lnPr.get(i) - 10.0);
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

    static class Query {
        boolean[] inQuery;
        int[] volume;
        int coordinateSize;

        Query(Input input) {
            this.inQuery = new boolean[input.n2];
            this.volume = new int[pool.size()];
        }

        void flip(int ij) {
            int sign = inQuery[ij] ? -1 : 1;
            for (int x = 0; x < pool.size(); x++) volume[x] += sign * (pool.get(x).volume[ij] & 0xFF);
            coordinateSize += sign;
            inQuery[ij] = !inQuery[ij];
        }

        double eval(Sim sim, Input input, int addK, int addV) {
            int tk = coordinateSize + addK;
            if (tk == 0) return -1e100;
            double[] pr_r = new double[sim.total + 1];
            for (int x = 0; x < pool.size(); x++) {
                int s = volume[x] + addV;
                double mu = (tk - s) * input.eps + s * (1.0 - input.eps);
                double sig = Math.sqrt(tk * input.eps * (1.0 - input.eps));
                // 理論式 P(r) = Σ P(r|x)P(x)
                double mu_rounded = Math.round(mu);
                for (int r = Math.max(0, (int)mu_rounded - 10); r <= Math.min(sim.total, (int)mu_rounded + 10); r++) {
                    pr_r[r] += sim.likelihood(mu, sig, r) * pool.get(x).pxIfR;
                }
            }
            double info = 0.0;
            for (int x = 0; x < pool.size(); x++) {
                int s = volume[x] + addV;
                double mu = (tk - s) * input.eps + s * (1.0 - input.eps);
                double sig = Math.sqrt(tk * input.eps * (1.0 - input.eps));
                double mu_rounded = Math.round(mu);
                for (int r = Math.max(0, (int)mu_rounded - 10); r <= Math.min(sim.total, (int)mu_rounded + 10); r++) {
                    double p_r_x = sim.likelihood(mu, sig, r);
                    if (p_r_x > SMALL_VALUE && pr_r[r] > SMALL_VALUE) {
                        info += p_r_x * pool.get(x).pxIfR * (Math.log(p_r_x) - Math.log(pr_r[r]));
                    }
                }
            }
            return info * Math.sqrt(tk);
        }
    }

    static void simulatedAnnealing(Input in, Sim sim, State s, int[][][] swaps, Map<Long, Double> hL, int iter, long startT) {
        double crt = pool.get(0).lnPRifX;
        for (int i = 0; i < in.m; i++) s.moveTo(i, pool.get(0).topLefts[i]);
        double maxCrt = crt;
        iter = (int)(iter * Math.min(TIME_LIMIT - (System.currentTimeMillis() - startT)/1000.0, 1.0));

        for (int t = 0; t < iter; t++) {
            double temp = 2.0 + (1.0 - 2.0) * t / iter;
            int coin = (int)rng.randRange(100);
            int m1 = (int)rng.randRange(in.m);
            int bk1 = s.topLefts[m1];

            if (coin < 30) { // Slide
                int[] d = DIJ[(int)rng.randRange(4)];
                int ni = bk1 / in.n + d[0], nj = bk1 % in.n + d[1];
                if (ni >= 0 && nj >= 0 && ni < in.n - in.oils[m1].maxI && nj < in.n - in.oils[m1].maxJ) {
                    s.moveTo(m1, ni * in.n + nj);
                    crt = updateSA(s, sim, hL, maxCrt, crt, temp, m1, bk1, -1, -1);
                }
            } else if (coin < 40) { // Warp
                s.moveTo(m1, (int)rng.randRange(in.n - in.oils[m1].maxI) * in.n + (int)rng.randRange(in.n - in.oils[m1].maxJ));
                crt = updateSA(s, sim, hL, maxCrt, crt, temp, m1, bk1, -1, -1);
            } else { // Swap
                int m2 = (int)rng.randRange(in.m);
                if (m1 == m2) continue;
                int bk2 = s.topLefts[m2];
                int d1 = swaps[m2][m1][(int)rng.randRange(swaps[m1][m2].length)];
                int d2 = swaps[m1][m2][(int)rng.randRange(swaps[m2][m1].length)];
                int ni1 = (bk2 / in.n) + (d1 / in.n), nj1 = (bk2 % in.n) + (d1 % in.n);
                int ni2 = (bk1 / in.n) + (d2 / in.n), nj2 = (bk1 % in.n) + (d2 % in.n);
                if (ni1 >= 0 && nj1 >= 0 && ni1 < in.n - in.oils[m1].maxI && nj1 < in.n - in.oils[m1].maxJ &&
                    ni2 >= 0 && nj2 >= 0 && ni2 < in.n - in.oils[m2].maxI && nj2 < in.n - in.oils[m2].maxJ) {
                    s.moveTo(m1, ni1 * in.n + nj1); s.moveTo(m2, ni2 * in.n + nj2);
                    crt = updateSA(s, sim, hL, maxCrt, crt, temp, m1, bk1, m2, bk2);
                }
            }
            if (maxCrt < crt) maxCrt = crt;
        }
    }

    static double updateSA(State s, Sim sim, Map<Long, Double> hL, double max, double crt, double temp, int m1, int bk1, int m2, int bk2) {
        double next = hL.getOrDefault(s.hash, sim.lnProbState(s));
        if (!hL.containsKey(s.hash)) {
            hL.put(s.hash, next);
            if (next - max >= -10.0) pool.add(new OilLayout(s.hash, next, 0.0, s.topLefts, s.volumes));
        }
        if (crt <= next || rng.genBool(Math.exp((next - crt) / temp))) return next;
        else { s.moveTo(m1, bk1); if (m2 != -1) s.moveTo(m2, bk2); return crt; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long startT = System.currentTimeMillis();
        Input input = new Input();
        input.n = sc.nextInt(); input.m = sc.nextInt(); input.eps = sc.nextDouble();
        input.n2 = input.n * input.n; input.oils = new OilShape[input.m];
        for (int i = 0; i < input.m; i++) {
            input.oils[i] = new OilShape(); int size = sc.nextInt();
            for (int j = 0; j < size; j++) input.oils[i].coordinates.add(new int[]{sc.nextInt(), sc.nextInt()});
            input.total += size; OilShape os = input.oils[i]; os.coordinateIds = new int[size];
            for (int j = 0; j < size; j++) {
                os.maxI = Math.max(os.maxI, os.coordinates.get(j)[0]); os.maxJ = Math.max(os.maxJ, os.coordinates.get(j)[1]);
                os.coordinateIds[j] = os.coordinates.get(j)[0] * input.n + os.coordinates.get(j)[1]; os.mask.set(os.coordinateIds[j]);
            }
        }
        input.setDifficulty(); // 難易度判定

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
                            if (ni >= 0 && nj >= 0 && ni < input.n && nj < input.n && input.oils[a].mask.get(ni * input.n + nj)) vol++;
                        }
                        list.add(new int[]{vol, di, dj});
                    }
                }
                list.sort((x, y) -> y[0] - x[0]);
                int sz = Math.min(4, list.size()); swaps[a][b] = new int[sz];
                for (int i = 0; i < sz; i++) { swaps[a][b][i] = list.get(i)[1] * input.n + list.get(i)[2]; }
            }
        }

        Sim sim = new Sim(input, sc); State state = new State(input);
        int saIter = 4000000 * 5 / (2 * input.n2);
        if (input.isDifficult()) saIter /= 5; // 簡単なケースなら5倍探索する

        for (int t = 0; ; t++) {
            if ((System.currentTimeMillis() - startT) / 1000.0 > TIME_LIMIT - 0.1) sim.giveup();
            for (OilLayout l : pool) l.lnPRifX = sim.getLnPRifX(state.oilStates, l.volume, l.topLefts);
            Collections.shuffle(pool, new Random(rng.next()));
            pool.sort((a, b) -> Double.compare(b.lnPRifX, a.lnPRifX));

            Map<Long, Double> hashL = new HashMap<>();
            for (OilLayout l : pool) hashL.put(l.hash, l.lnPRifX);

            if (t == 0) {
                for (int i = 0; i < saIter; i++) {
                    for (int m = 0; m < input.m; m++) state.moveTo(m, (int)rng.randRange(input.n - input.oils[m].maxI) * input.n + (int)rng.randRange(input.n - input.oils[m].maxJ));
                    if (!hashL.containsKey(state.hash)) {
                        hashL.put(state.hash, 0.0);
                        pool.add(new OilLayout(state.hash, 0.0, 0.0, state.topLefts, input.getVolume(state.topLefts)));
                    }
                }
            } else {
                simulatedAnnealing(input, sim, state, swaps, hashL, saIter, startT);
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
                if (!input.isDifficult() || (sim.queries.size() > 0 && sim.queries.get(sim.queries.size()-1).equals(revT))) {
                    if (sim.ans(T)) break;
                } else {
                    sim.query(revT); state.addQuery(revT);
                }
            } else {
                // 相互情報量を最大化する占い生成
                Query qe = new Query(input);
                BitSet same = new BitSet(input.n2); same.set(0, input.n2);
                for (int x = 1; x < pool.size(); x++) {
                    for (int i = 0; i < input.n2; i++) if (pool.get(x).volume[i] != pool.get(0).volume[i]) same.clear(i);
                }
                List<Integer> noInfo = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) if (same.get(i)) noInfo.add(i);
                noInfo.sort((a, b) -> (pool.get(0).volume[b]*1000 + (int)rng.randRange(1000)) - (pool.get(0).volume[a]*1000 + (int)rng.randRange(1000)));

                double bestEval = -1e100; int addK = 0, addV = 0;
                for (int iter = 0; iter < 3; iter++) {
                    boolean change = false;
                    for (int i = 0; i < input.n2; i++) {
                        if (same.get(i)) continue; qe.flip(i);
                        double e = qe.eval(sim, input, addK, addV);
                        if (e > bestEval) { bestEval = e; change = true; } else qe.flip(i);
                    }
                    if (!input.isDifficult()) { // 簡単なケースでは情報量0のマスを山登りで追加
                        while (addK < noInfo.size()) {
                            double e = qe.eval(sim, input, addK + 1, addV + (pool.get(0).volume[noInfo.get(addK)] & 0xFF));
                            if (e > bestEval) { bestEval = e; addV += (pool.get(0).volume[noInfo.get(addK)] & 0xFF); addK++; change = true; } else break;
                        }
                    }
                    if (!change) break;
                }
                List<Integer> coords = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) if (qe.inQuery[i]) coords.add(i);
                for (int i = 0; i < addK; i++) coords.add(noInfo.get(i));
                if (coords.isEmpty()) coords.add(0);
                sim.query(coords); state.addQuery(coords);
            }
        }
    }
}
