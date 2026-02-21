import java.util.*;

public class Main {
    static final double SMALL_VALUE = 1e-6;
    static final int[][] DIJ = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static Xorshift rng = new Xorshift(1);
    static long startTime;

    // 高速な乱数生成器
    static class Xorshift {
        private long x;
        public Xorshift(long seed) { this.x = seed; }
        public long next() {
            x ^= x << 13; x ^= x >>> 17; x ^= x << 5;
            return x;
        }
        public int randRange(int stop) { return (int) (Math.abs(next()) % stop); }
        public boolean genBool(double p) { 
            return (double)(next() & 0x7FFFFFFFFFFFFFFFL) / Long.MAX_VALUE < p; 
        }
    }

    static class OilShape {
        int maxI, maxJ;
        List<Integer> coordinateIds = new ArrayList<>();
        List<int[]> coordinates = new ArrayList<>();
        BitSet mask = new BitSet(400); // 20*20
    }

    static class Input {
        int n, n2, m;
        double eps;
        OilShape[] oils;
        int total;

        BitSet getPositives(List<Integer> topLefts) {
            BitSet positives = new BitSet(n2);
            for (int oilId = 0; oilId < m; oilId++) {
                // maskをtopLefts[oilId]分シフトしてOR
                for (int id : oils[oilId].coordinateIds) {
                    positives.set(id + topLefts.get(oilId));
                }
            }
            return positives;
        }

        int[] getVolume(List<Integer> topLefts) {
            int[] volume = new int[n2];
            for (int oilId = 0; oilId < m; oilId++) {
                for (int id : oils[oilId].coordinateIds) {
                    volume[id + topLefts.get(oilId)]++;
                }
            }
            return volume;
        }
    }

    static class OilLayout {
        long hash;
        double lnPRifX; // 対数尤度
        double pxIfR;   // 事後確率
        List<Integer> topLefts;
        int[] volume;

        OilLayout(long hash, double ln, double px, List<Integer> tls, int[] vol) {
            this.hash = hash; this.lnPRifX = ln; this.pxIfR = px;
            this.topLefts = new ArrayList<>(tls);
            this.volume = (vol != null) ? vol.clone() : null;
        }
    }

    static class OilState {
        List<List<Integer>> topLeftQueryVolumes;
        long[] hashes;
        OilState(Input input) {
            topLeftQueryVolumes = new ArrayList<>(input.n2);
            for (int i = 0; i < input.n2; i++) topLeftQueryVolumes.add(new ArrayList<>());
            hashes = new long[input.n2];
        }
    }

    static class State {
        OilState[] oilStates;
        List<Integer> topLefts;
        int[] volumes;
        List<Integer> queryVolumes;
        long hash;
        Input input;

        State(Input input) {
            this.input = input;
            this.oilStates = new OilState[input.m];
            for (int i = 0; i < input.m; i++) {
                oilStates[i] = new OilState(input);
                if (i > 0 && input.oils[i-1].coordinateIds.equals(input.oils[i].coordinateIds)) {
                    oilStates[i].hashes = oilStates[i-1].hashes;
                } else {
                    for (int j = 0; j < input.n2; j++) oilStates[i].hashes[j] = rng.next();
                }
            }
            this.hash = 0;
            for (int i = 0; i < input.m; i++) this.hash ^= oilStates[i].hashes;
            this.topLefts = new ArrayList<>(Collections.nCopies(input.m, 0));
            this.volumes = new int[input.n2];
            this.queryVolumes = new ArrayList<>();
        }

        // 差分更新を伴う移動メソッド
        void moveTo(int oilId, int newTL) {
            OilState os = oilStates[oilId];
            hash ^= os.hashes[topLefts.get(oilId)] ^ os.hashes[newTL];
            for (int q = 0; q < queryVolumes.size(); q++) {
                queryVolumes.set(q, queryVolumes.get(q) + os.topLeftQueryVolumes.get(newTL).get(q) - os.topLeftQueryVolumes.get(topLefts.get(oilId)).get(q));
            }
            if (volumes.length > 0) {
                for (int idCoord : input.oils[oilId].coordinateIds) {
                    volumes[idCoord + topLefts.get(oilId)]--;
                    volumes[idCoord + newTL]++;
                }
            }
            topLefts.set(oilId, newTL);
        }

        void addQuery(List<Integer> queryCoords) {
            boolean[] inQ = new boolean[input.n2];
            for (int id : queryCoords) inQ[id] = true;
            for (int oilId = 0; oilId < input.m; oilId++) {
                OilShape oil = input.oils[oilId];
                for (int di = 0; di <= input.n - 1 - oil.maxI; di++) {
                    for (int dj = 0; dj <= input.n - 1 - oil.maxJ; dj++) {
                        int tl = di * input.n + dj;
                        int c = 0;
                        for (int id : oil.coordinateIds) if (inQ[tl + id]) c++;
                        oilStates[oilId].topLeftQueryVolumes.get(tl).add(c);
                    }
                }
            }
            int[] currentVol = input.getVolume(topLefts);
            int sum = 0;
            for (int id : queryCoords) sum += currentVol[id];
            queryVolumes.add(sum);
        }
    }

    static class Sim {
        Input input;
        int rem;
        List<double[]> lnPrIfSQuery = new ArrayList<>();
        List<int[]> failed = new ArrayList<>();
        // prIfX[k][S] -> List of {prob, logProb}
        List<Map<Integer, List<double[]>>> prIfX;
        int[][] prIfXLb;

        Sim(Input in) {
            this.input = in; this.rem = in.n * in.n * 2;
            this.prIfXLb = new int[in.n2 + 1][in.total + 1];
            this.prIfX = new ArrayList<>(in.n2 + 1);
            for(int i=0; i<=in.n2; i++) prIfX.add(new HashMap<>());
            
            // 尤度の事前計算
            for (int k = 1; k <= in.n2; k++) {
                for (int s = 0; s <= in.total; s++) {
                    double mu = (double)(k - s) * in.eps + s * (1.0 - in.eps);
                    double sigma = Math.sqrt(k * in.eps * (1.0 - in.eps));
                    List<double[]> list = new ArrayList<>();
                    int startR = (int)Math.round(mu);
                    for (int r = startR; r >= 0; r--) {
                        double p = likelihood(mu, sigma, r);
                        if (p < SMALL_VALUE) { prIfXLb[k][s] = r + 1; break; }
                        list.add(new double[]{p, Math.log(p)});
                    }
                    Collections.reverse(list);
                    for (int r = startR + 1; ; r++) {
                        double p = likelihood(mu, sigma, r);
                        if (p < SMALL_VALUE) break;
                        list.add(new double[]{p, Math.log(p)});
                    }
                    prIfX.get(k).put(s, list);
                }
            }
        }

        int query(List<Integer> coords, Scanner sc) {
            rem--;
            System.out.print("q " + coords.size());
            for (int id : coords) System.out.print(" " + (id / input.n) + " " + (id % input.n));
            System.out.println(); System.out.flush();
            int ret = sc.nextInt();
            double[] lnPrIfS = new double[input.total + 1];
            for (int s = 0; s <= input.total; s++) {
                double mu = (double)(coords.size() - s) * input.eps + s * (1.0 - input.eps);
                double sigma = Math.sqrt(coords.size() * input.eps * (1.0 - input.eps));
                lnPrIfS[s] = Math.log(likelihood(mu, sigma, ret));
            }
            // 尤度補正
            for (int i = 0; i < lnPrIfS.length - 1; i++) {
                if (!Double.isInfinite(lnPrIfS[i]) && Double.isInfinite(lnPrIfS[i+1])) lnPrIfS[i+1] = lnPrIfS[i] - 10.0;
            }
            for (int i = lnPrIfS.length - 1; i > 0; i--) {
                if (!Double.isInfinite(lnPrIfS[i]) && Double.isInfinite(lnPrIfS[i-1])) lnPrIfS[i-1] = lnPrIfS[i] - 10.0;
            }
            lnPrIfSQuery.add(lnPrIfS);
            return ret;
        }

        boolean ans(List<Integer> list, Scanner sc) {
            rem--;
            System.out.print("a " + list.size());
            for (int id : list) System.out.print(" " + (id / input.n) + " " + (id % input.n));
            System.out.println(); System.out.flush();
            if (sc.nextInt() == 1) return true;
            int[] f = new int[list.size()];
            for(int i=0; i<list.size(); i++) f[i] = list.get(i);
            failed.add(f);
            return false;
        }

        double likelihood(double mu, double sigma, int res) {
            if (res == 0) return 0.5 * (1.0 + erf((0.5 - mu) / (sigma * Math.sqrt(2.0))));
            return 0.5 * (erf((res + 0.5 - mu) / (sigma * Math.sqrt(2.0))) - erf((res - 0.5 - mu) / (sigma * Math.sqrt(2.0))));
        }

        double erf(double x) {
            double t = 1.0 / (1.0 + 0.5 * Math.abs(x));
            double ans = 1 - t * Math.exp(-x * x - 1.26551223 + t * (1.00002368 + t * (0.37409196 + t * (0.09678418 + 
                         t * (-0.18628806 + t * (0.27886807 + t * (-1.13520398 + t * (1.48851587 + 
                         t * (-0.82215223 + t * 0.17087277)))))))));
            return (x >= 0) ? ans : -ans;
        }

        // BFSによる最後の掘り
        void giveup(Scanner sc) {
            Deque<int[]> que = new ArrayDeque<>();
            que.add(new int[]{input.n / 2, input.n / 2});
            List<Integer> found = new ArrayList<>();
            int remT = input.total;
            boolean[][] used = new boolean[input.n][input.n];
            while (!que.isEmpty()) {
                int[] p = que.pollFirst();
                int r = p, c = p;
                if (used[r][c]) continue;
                used[r][c] = true;
                System.out.println("q 1 " + r + " " + c); System.out.flush();
                int ret = sc.nextInt();
                if (ret > 0) {
                    found.add(r * input.n + c);
                    remT -= ret; if (remT <= 0) break;
                }
                for (int[] d : DIJ) {
                    int ni = r + d, nj = c + d;
                    if (ni >= 0 && ni < input.n && nj >= 0 && nj < input.n && !used[ni][nj]) {
                        if (ret == 0) que.addLast(new int[]{ni, nj});
                        else que.addFirst(new int[]{ni, nj});
                    }
                }
            }
            ans(found, sc); System.exit(0);
        }

        double lnProbState(State state) {
            for (int[] f : failed) {
                boolean match = true;
                int count = 0;
                for(int id : f) if(state.volumes[id] == 0) { match = false; break; }
                if(match) {
                    for(int v : state.volumes) if(v > 0) count++;
                    if(count == f.length) return -1e20;
                }
            }
            double res = 0;
            for (int q = 0; q < lnPrIfSQuery.size(); q++) res += lnPrIfSQuery.get(q)[state.queryVolumes.get(q)];
            return res;
        }
    }

    static class QueryHandler {
        boolean[] inQ; int[] poolVol; int size; List<OilLayout> pool;
        QueryHandler(Input in, List<OilLayout> pool) {
            this.inQ = new boolean[in.n2]; this.poolVol = new int[pool.size()];
            this.size = 0; this.pool = pool;
        }
        void flip(int ij) {
            if (inQ[ij]) {
                inQ[ij] = false; for(int x=0; x<pool.size(); x++) poolVol[x] -= pool.get(x).volume[ij]; size--;
            } else {
                inQ[ij] = true; for(int x=0; x<pool.size(); x++) poolVol[x] += pool.get(x).volume[ij]; size++;
            }
        }
        // 相互情報量評価
        double eval(Sim sim, int addK, int addV) {
            int k = size + addK; if (k == 0) return -1e20;
            double cost = 1.0 / Math.sqrt(k);
            List<Double> prR = new ArrayList<>();
            for (int x = 0; x < pool.size(); x++) {
                int v = poolVol[x] + addV; int lb = sim.prIfXLb[k][v];
                List<double[]> probs = sim.prIfX.get(k).get(v);
                while (prR.size() < lb + probs.size()) prR.add(0.0);
                double px = pool.get(x).pxIfR;
                for (int i = 0; i < probs.size(); i++) prR.set(lb + i, prR.get(lb + i) + probs.get(i) * px);
            }
            double info = 0.0;
            for (int x = 0; x < pool.size(); x++) {
                double px = pool.get(x).pxIfR; int v = poolVol[x] + addV; int lb = sim.prIfXLb[k][v];
                List<double[]> probs = sim.prIfX.get(k).get(v);
                for (int i = 0; i < probs.size(); i++) {
                    double prRgX = probs.get(i); double lnPrRgX = probs.get(i);
                    info += prRgX * px * (lnPrRgX - Math.log(prR.get(lb + i) + 1e-20));
                }
            }
            return info / cost;
        }
    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        Input in = new Input();
        if (!sc.hasNextInt()) return;
        in.n = sc.nextInt(); in.m = sc.nextInt(); in.eps = sc.nextDouble();
        in.n2 = in.n * in.n; in.oils = new OilShape[in.m];
        for (int i = 0; i < in.m; i++) {
            in.oils[i] = new OilShape();
            int d = sc.nextInt();
            for (int j = 0; j < d; j++) {
                int r = sc.nextInt(), c = sc.nextInt();
                in.oils[i].coordinates.add(new int[]{r, c});
                in.oils[i].coordinateIds.add(r * in.n + c);
                in.oils[i].maxI = Math.max(in.oils[i].maxI, r);
                in.oils[i].maxJ = Math.max(in.oils[i].maxJ, c);
            }
            in.total += d;
        }

        List<int[]>[][] swaps = getSwaps(in); // Swap近傍の事前計算
        Sim sim = new Sim(in);
        State state = new State(in);
        List<OilLayout> pool = new ArrayList<>();
        int ITER = 4000000 / (2 * in.n2);

        for (int t = 0;; t++) {
            if (sim.rem <= 0) break;
            if (System.currentTimeMillis() - startTime > 2900) sim.giveup(sc);

            // 尤度更新
            for (OilLayout l : pool) {
                if (l.volume == null && !sim.failed.isEmpty()) l.volume = in.getVolume(l.topLefts);
                l.lnPRifX = sim.lnProbState(new State(in){{ topLefts=l.topLefts; volumes=(l.volume!=null?l.volume:new int); queryVolumes=state.queryVolumes; oilStates=state.oilStates; }});
            }
            Collections.shuffle(pool);
            pool.sort((a, b) -> Double.compare(b.lnPRifX, a.lnPRifX));

            Map<Long, Double> seen = new HashMap<>();
            for (OilLayout l : pool) seen.put(l.hash, l.lnPRifX);

            if (t == 0) {
                // 初回ランダム生成
                for (int i = 0; i < ITER; i++) {
                    for (int m = 0; m < in.m; m++) {
                        int r = rng.randRange(in.n - in.oils[m].maxI);
                        int c = rng.randRange(in.n - in.oils[m].maxJ);
                        state.moveTo(m, r * in.n + c);
                    }
                    if (!seen.containsKey(state.hash)) {
                        seen.put(state.hash, 0.0);
                        pool.add(new OilLayout(state.hash, 0.0, 0.0, state.topLefts, in.getVolume(state.topLefts)));
                    }
                }
            } else {
                // 工夫された焼きなましによるプール補充
                performSA(in, sim, state, pool, seen, swaps, ITER);
            }

            double maxLn = pool.get(0).lnPRifX;
            double sumExp = 0;
            for (OilLayout l : pool) { l.pxIfR = Math.exp(l.lnPRifX - maxLn); sumExp += l.pxIfR; }
            for (OilLayout l : pool) l.pxIfR /= sumExp;
            while (pool.size() > 1 && pool.get(pool.size() - 1).pxIfR < 1e-9) pool.remove(pool.size() - 1);

            double bestProb = pool.get(0).pxIfR;
            concatPool(pool, ITER); // 時間経過でプールを絞る

            if (bestProb > 0.8) {
                // 回答アクション
                BitSet bits = in.getPositives(pool.get(0).topLefts);
                List<Integer> target = new ArrayList<>();
                for(int i=0; i<in.n2; i++) if(bits.get(i)) target.add(i);
                if (sim.ans(target, sc)) break;
            } else {
                // MIクエリ選択
                List<Integer> qCoords = getDivinationQuery(in, pool, sim);
                sim.query(qCoords, sc);
                state.addQuery(qCoords);
            }
        }
    }

    static void performSA(Input in, Sim sim, State state, List<OilLayout> pool, Map<Long, Double> seen, List<int[]>[][] swaps, int ITER) {
        double currentL = pool.get(0).lnPRifX;
        for(int i=0; i<in.m; i++) state.moveTo(i, pool.get(0).topLefts.get(i));
        double maxL = currentL;
        int saIter = (int)(ITER * Math.min(3.0 - (double)(System.currentTimeMillis()-startTime)/1000.0, 1.0));
        
        for (int i = 0; i < saIter; i++) {
            double temp = 2.0 + (1.0 - 2.0) * i / saIter;
            int coin = rng.randRange(100);
            int id = rng.randRange(in.m);
            int oldTL = state.topLefts.get(id);

            if (coin < 30) { // Slide: 1マス移動
                int[] d = DIJ[rng.randRange(4)];
                int ni = oldTL / in.n + d, nj = oldTL % in.n + d;
                if (ni >= 0 && nj >= 0 && ni < in.n - in.oils[id].maxI && nj < in.n - in.oils[id].maxJ) {
                    state.moveTo(id, ni * in.n + nj);
                    updatePool(sim, state, pool, seen, maxL, currentL, temp, oldTL, id);
                }
            } else if (coin < 40) { // Warp: ランダム移動
                int nextTL = rng.randRange(in.n - in.oils[id].maxI) * in.n + rng.randRange(in.n - in.oils[id].maxJ);
                state.moveTo(id, nextTL);
                updatePool(sim, state, pool, seen, maxL, currentL, temp, oldTL, id);
            } else { // Swap: 2つを入れ替え
                int id2 = rng.randRange(in.m); if(id == id2) continue;
                int oldTL2 = state.topLefts.get(id2);
                int[] daij = swaps[id2][id].get(rng.randRange(swaps[id2][id].size()));
                int[] dbij = swaps[id][id2].get(rng.randRange(swaps[id][id2].size()));
                int ni1 = (oldTL2 / in.n) + daij, nj1 = (oldTL2 % in.n) + daij;
                int ni2 = (oldTL / in.n) + dbij, nj2 = (oldTL % in.n) + dbij;
                if (ni1 >= 0 && nj1 >= 0 && ni1 < in.n - in.oils[id].maxI && nj1 < in.n - in.oils[id].maxJ &&
                    ni2 >= 0 && nj2 >= 0 && ni2 < in.n - in.oils[id2].maxI && nj2 < in.n - in.oils[id2].maxJ) {
                    state.moveTo(id, ni1 * in.n + nj1); state.moveTo(id2, ni2 * in.n + nj2);
                    double nextL = seen.getOrDefault(state.hash, sim.lnProbState(state));
                    if (!seen.containsKey(state.hash)) {
                        seen.put(state.hash, nextL);
                        if (nextL - maxL >= -10.0) pool.add(new OilLayout(state.hash, nextL, 0.0, state.topLefts, in.getVolume(state.topLefts)));
                    }
                    if (nextL >= currentL || rng.genBool(Math.exp((nextL - currentL)/temp))) currentL = nextL;
                    else { state.moveTo(id, oldTL); state.moveTo(id2, oldTL2); }
                }
            }
        }
    }

    static void updatePool(Sim sim, State state, List<OilLayout> pool, Map<Long, Double> seen, double maxL, double currentL, double temp, int oldTL, int id) {
        double nextL = seen.getOrDefault(state.hash, sim.lnProbState(state));
        if (!seen.containsKey(state.hash)) {
            seen.put(state.hash, nextL);
            if (nextL - maxL >= -10.0) pool.add(new OilLayout(state.hash, nextL, 0.0, state.topLefts, null));
        }
        if (nextL >= currentL || rng.genBool(Math.exp((nextL - currentL)/temp))) currentL = nextL;
        else state.moveTo(id, oldTL);
    }

    static List<Integer> getDivinationQuery(Input in, List<OilLayout> pool, Sim sim) {
        boolean[] same = new boolean[in.n2]; Arrays.fill(same, true);
        for(int x=1; x<pool.size(); x++) for(int i=0; i<in.n2; i++) same[i] &= (pool.get(x).volume[i] == pool.get(0).volume[i]);
        QueryHandler qh = new QueryHandler(in, pool);
        List<Double> evals = new ArrayList<>();
        for(int i=0; i<in.n2; i++) { if(!same[i]) { qh.flip(i); evals.add(qh.eval(sim,0,0)); qh.flip(i); } else evals.add(-1e20); }
        List<Integer> idx = new ArrayList<>(); for(int i=0; i<in.n2; i++) idx.add(i);
        idx.sort((a,b) -> Double.compare(evals.get(b), evals.get(a)));
        double bestE = -1e20;
        for (int iter = 0; iter < 3; iter++) {
            boolean changed = false;
            for (int i : idx) {
                if(evals.get(i) < -1e19) continue;
                qh.flip(i); double e = qh.eval(sim,0,0);
                if (e > bestE) { bestE = e; changed = true; } else qh.flip(i);
            }
            if(!changed) break;
        }
        List<Integer> res = new ArrayList<>(); for(int i=0; i<in.n2; i++) if(qh.inQ[i]) res.add(i);
        if(res.isEmpty()) res.add(rng.randRange(in.n2));
        return res;
    }

    static void concatPool(List<OilLayout> pool, int ITER) {
        double remTime = Math.max(0, 3.0 - (double)(System.currentTimeMillis()-startTime)/1000.0);
        int target = (int)Math.max(2, (ITER * 0.01) * Math.min(remTime, 1.0));
        if(pool.size() > target) {
            int s = pool.size();
            while(s > 2 && pool.get(0).pxIfR * 1e-4 > pool.get(s-1).pxIfR) s--;
            if(pool.size() > s) pool.subList(Math.max(2, s), pool.size()).clear();
        }
    }

    static List<int[]>[][] getSwaps(Input in) {
        List<int[]>[][] res = new List[in.m][in.m];
        for (int a = 0; a < in.m; a++) {
            boolean[] isA = new boolean[in.n2];
            for(int id : in.oils[a].coordinateIds) isA[id] = true;
            for (int b = 0; b < in.m; b++) {
                res[a][b] = new ArrayList<>();
                if(a == b) continue;
                List<int[]> list = new ArrayList<>();
                for (int di = -in.oils[b].maxI; di <= in.oils[a].maxI; di++) {
                    for (int dj = -in.oils[b].maxJ; dj <= in.oils[a].maxJ; dj++) {
                        int vol = 0;
                        for(int id : in.oils[b].coordinateIds) {
                            int ni = id/in.n + di, nj = id%in.n + dj;
                            if(ni>=0 && nj>=0 && ni<in.n && nj<in.n && isA[ni*in.n+nj]) vol++;
                        }
                        list.add(new int[]{vol, di, dj});
                    }
                }
                list.sort((x, y) -> y - x);
                for(int i=0; i<Math.min(4, list.size()); i++) res[a][b].add(new int[]{list.get(i), list.get(i)});
            }
        }
        return res;
    }
}
