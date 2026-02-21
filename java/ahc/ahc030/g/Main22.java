import java.util.*;

public class Main {
    static final double SMALL_VALUE = 1e-6;
    static final int[][] DIJ = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static Xorshift rng = new Xorshift(1);

    static class Xorshift {
        private long x;
        public Xorshift(long seed) { this.x = seed; }
        public long next() {
            x ^= x << 13; x ^= x >>> 17; x ^= x << 5;
            return x;
        }
        public boolean genBool(double p) { 
            return (double)(next() & 0x7FFFFFFFFFFFFFFFL) / Long.MAX_VALUE < p; 
        }
    }

    static class OilShape {
        int maxI, maxJ;
        List<Integer> coordinateIds = new ArrayList<>();
        List<int[]> coordinates = new ArrayList<>();
    }

    static class Input {
        int n, n2, m;
        double eps;
        OilShape[] oils;
        int total;

        List<Integer> getPositives(List<Integer> topLefts) {
            BitSet bits = new BitSet(n2);
            for (int i = 0; i < m; i++) {
                for (int id : oils[i].coordinateIds) bits.set(id + topLefts.get(i));
            }
            List<Integer> res = new ArrayList<>();
            for (int i = bits.nextSetBit(0); i >= 0; i = bits.nextSetBit(i + 1)) res.add(i);
            return res;
        }

        int[] getVolume(List<Integer> topLefts) {
            int[] vol = new int[n2];
            for (int i = 0; i < m; i++) {
                for (int id : oils[i].coordinateIds) vol[id + topLefts.get(i)]++;
            }
            return vol;
        }
    }

    static class OilLayout {
        double lnPRifX;
        double pxIfR;
        List<Integer> topLefts;
        int[] volume;

        OilLayout(double ln, double px, List<Integer> tls, int[] vol) {
            this.lnPRifX = ln; this.pxIfR = px;
            this.topLefts = new ArrayList<>(tls);
            this.volume = vol;
        }
    }

    static class State {
        Input input;
        List<Integer> topLefts;
        int[] volumes;
        State(Input in) {
            this.input = in;
            this.topLefts = new ArrayList<>(Collections.nCopies(in.m, 0));
            this.volumes = new int[in.n2];
        }
        void moveTo(int id, int newTL) {
            for (int idCoord : input.oils[id].coordinateIds) volumes[idCoord + topLefts.get(id)]--;
            topLefts.set(id, newTL);
            for (int idCoord : input.oils[id].coordinateIds) volumes[idCoord + topLefts.get(id)]++;
        }
    }

    static class Sim {
        Input input;
        int rem;
        List<double[]> lnPrIfSQuery = new ArrayList<>();
        List<List<Integer>> queryCoords = new ArrayList<>();
        // pr_if_x[k][S] -> list of (prob, logProb) for results r
        List<Map<Integer, List<double[]>>> pr_if_x; 
        int[][] pr_if_x_lb;

        Sim(Input in) { 
            this.input = in; 
            this.rem = in.n * in.n * 2; 
            this.pr_if_x_lb = new int[in.n2 + 1][in.total + 1];
            this.pr_if_x = new ArrayList<>(in.n2 + 1);
            for(int i=0; i<=in.n2; i++) pr_if_x.add(new HashMap<>());
            
            // 尤度の事前計算 (Source 42-43)
            for (int k = 1; k <= in.n2; k++) {
                for (int s = 0; s <= in.total; s++) {
                    double mu = (double)(k - s) * in.eps + s * (1.0 - in.eps);
                    double sigma = Math.sqrt(k * in.eps * (1.0 - in.eps));
                    List<double[]> list = new ArrayList<>();
                    int startR = (int)Math.round(mu);
                    
                    for (int r = startR; r >= 0; r--) {
                        double p = likelihood(mu, sigma, r);
                        if (p < SMALL_VALUE) { pr_if_x_lb[k][s] = r + 1; break; }
                        list.add(new double[]{p, Math.log(p)});
                    }
                    Collections.reverse(list);
                    for (int r = startR + 1; ; r++) {
                        double p = likelihood(mu, sigma, r);
                        if (p < SMALL_VALUE) break;
                        list.add(new double[]{p, Math.log(p)});
                    }
                    pr_if_x.get(k).put(s, list);
                }
            }
        }

        int query(List<Integer> coords, Scanner sc) {
            rem--;
            System.out.print("q " + coords.size());
            for (int id : coords) System.out.print(" " + (id / input.n) + " " + (id % input.n));
            System.out.println(); System.out.flush();
            int res = sc.nextInt();
            
            double[] lnPrIfS = new double[input.total + 1];
            for (int s = 0; s <= input.total; s++) {
                double mu = (coords.size() - s) * input.eps + s * (1.0 - input.eps);
                double sigma = Math.sqrt(coords.size() * input.eps * (1.0 - input.eps));
                lnPrIfS[s] = Math.log(likelihood(mu, sigma, res));
            }
            lnPrIfSQuery.add(lnPrIfS);
            queryCoords.add(new ArrayList<>(coords));
            return res;
        }

        double likelihood(double mu, double sigma, int res) {
            if (res == 0) return normalCdf(mu, sigma, 0.5);
            return normalCdf(mu, sigma, res + 0.5) - normalCdf(mu, sigma, res - 0.5);
        }

        double normalCdf(double mu, double sigma, double x) {
            return 0.5 * (1.0 + erf((x - mu) / (sigma * Math.sqrt(2.0))));
        }

        double erf(double x) {
            double t = 1.0 / (1.0 + 0.5 * Math.abs(x));
            double ans = 1 - t * Math.exp(-x * x - 1.26551223 + t * (1.00002368 + t * (0.37409196 + t * (0.09678418 + 
                         t * (-0.18628806 + t * (0.27886807 + t * (-1.13520398 + t * (1.48851587 + 
                         t * (-0.82215223 + t * 0.17087277)))))))));
            return (x >= 0) ? ans : -ans;
        }

        void giveup(Scanner sc) {
            Deque<int[]> que = new ArrayDeque<>();
            que.add(new int[]{input.n / 2, input.n / 2});
            List<Integer> found = new ArrayList<>();
            int remTotal = input.total;
            boolean[][] used = new boolean[input.n][input.n];
            while (!que.isEmpty()) {
                int[] p = que.pollFirst();
                if (used[p][p]) continue;
                used[p][p] = true;
                System.out.println("q 1 " + p + " " + p); System.out.flush();
                int ret = sc.nextInt();
                if (ret > 0) {
                    found.add(p * input.n + p);
                    remTotal -= ret; if (remTotal <= 0) break;
                }
                for (int[] d : DIJ) {
                    int ni = p + d, nj = p + d;
                    if (ni >= 0 && ni < input.n && nj >= 0 && nj < input.n && !used[ni][nj]) {
                        if (ret == 0) que.addLast(new int[]{ni, nj});
                        else que.addFirst(new int[]{ni, nj});
                    }
                }
            }
            System.out.print("a " + found.size());
            for (int id : found) System.out.print(" " + (id / input.n) + " " + (id % input.n));
            System.out.println(); System.out.flush();
            System.exit(0);
        }
    }

    static class QueryHandler {
        boolean[] inQuery;
        int[] volumesInPool;
        int coordSize;
        List<OilLayout> pool;

        QueryHandler(Input input, List<OilLayout> pool) {
            this.inQuery = new boolean[input.n2];
            this.volumesInPool = new int[pool.size()];
            this.coordSize = 0;
            this.pool = pool;
        }

        void flip(int ij) {
            if (inQuery[ij]) {
                inQuery[ij] = false;
                for (int x = 0; x < pool.size(); x++) volumesInPool[x] -= pool.get(x).volume[ij];
                coordSize--;
            } else {
                inQuery[ij] = true;
                for (int x = 0; x < pool.size(); x++) volumesInPool[x] += pool.get(x).volume[ij];
                coordSize++;
            }
        }

        // 相互情報量 I(X;R) / cost の評価 (Source 53-54)
        double eval(Sim sim) {
            if (coordSize == 0) return -1e20;
            int k = coordSize;
            double cost = 1.0 / Math.sqrt(k);
            List<Double> lnPrR = new ArrayList<>();
            
            for (int x = 0; x < pool.size(); x++) {
                int v = volumesInPool[x];
                int lb = sim.pr_if_x_lb[k][v];
                List<double[]> probs = sim.pr_if_x.get(k).get(v);
                while (lnPrR.size() < lb + probs.size()) lnPrR.add(0.0);
                double px = pool.get(x).pxIfR;
                for (int i = 0; i < probs.size(); i++) {
                    lnPrR.set(lb + i, lnPrR.get(lb + i) + probs.get(i) * px);
                }
            }
            for (int i = 0; i < lnPrR.size(); i++) lnPrR.set(i, Math.log(lnPrR.get(i) + 1e-20));

            double info = 0.0;
            for (int x = 0; x < pool.size(); x++) {
                double px = pool.get(x).pxIfR;
                int v = volumesInPool[x];
                int lb = sim.pr_if_x_lb[k][v];
                List<double[]> probs = sim.pr_if_x.get(k).get(v);
                for (int i = 0; i < probs.size(); i++) {
                    info += probs.get(i) * px * (probs.get(i) - lnPrR.get(lb + i));
                }
            }
            return info / cost;
        }

        List<Integer> getCoords() {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < inQuery.length; i++) if (inQuery[i]) res.add(i);
            return res;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Input in = new Input();
        if (!sc.hasNextInt()) return;
        in.n = sc.nextInt(); in.m = sc.nextInt(); in.eps = sc.nextDouble();
        in.n2 = in.n * in.n;
        in.oils = new OilShape[in.m];
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

        Sim sim = new Sim(in);
        State state = new State(in);
        List<OilLayout> pool = new ArrayList<>();
        
        // 【重要】M=2を前提とした全配置生成。インデックス0と1を明示的に使用。 (Source 197)
        assert in.m == 2;
        OilShape oilA = in.oils; 
        OilShape oilB = in.oils;

        for (int iA = 0; iA <= in.n - 1 - oilA.maxI; iA++) {
            for (int jA = 0; jA <= in.n - 1 - oilA.maxJ; jA++) {
                state.moveTo(0, iA * in.n + jA);
                for (int iB = 0; iB <= in.n - 1 - oilB.maxI; iB++) {
                    for (int jB = 0; jB <= in.n - 1 - oilB.maxJ; jB++) {
                        state.moveTo(1, iB * in.n + jB);
                        pool.add(new OilLayout(0.0, 0.0, state.topLefts, state.volumes.clone()));
                    }
                }
            }
        }

        while (true) {
            if (sim.rem <= 0) sim.giveup(sc);

            // 対数尤度の更新 (Source 198)
            for (OilLayout layout : pool) {
                layout.lnPRifX = 0;
                for (int q = 0; q < sim.lnPrIfSQuery.size(); q++) {
                    int s = 0;
                    for (int id : sim.queryCoords.get(q)) s += layout.volume[id];
                    layout.lnPRifX += sim.lnPrIfSQuery.get(q)[s];
                }
            }

            double maxLn = pool.stream().mapToDouble(l -> l.lnPRifX).max().orElse(0);
            double sumExp = pool.stream().mapToDouble(l -> Math.exp(l.lnPRifX - maxLn)).sum();
            for (OilLayout l : pool) l.pxIfR = Math.exp(l.lnPRifX - maxLn) / sumExp;

            pool.sort((a, b) -> Double.compare(b.pxIfR, a.pxIfR));
            
            if (pool.get(0).pxIfR > 0.8) {
                List<Integer> target = in.getPositives(pool.get(0).topLefts);
                System.out.print("a " + target.size());
                for (int id : target) System.out.print(" " + (id / in.n) + " " + (id % in.n));
                System.out.println(); System.out.flush();
                if (sc.nextInt() == 1) break;
            } else {
                // 山登り法による相互情報量最大のクエリ探索 (Source 195-197)
                QueryHandler qh = new QueryHandler(in, pool);
                List<Double> evals = new ArrayList<>();
                for (int i = 0; i < in.n2; i++) {
                    qh.flip(i);
                    evals.add(qh.eval(sim));
                    qh.flip(i);
                }
                List<Integer> indices = new ArrayList<>();
                for(int i=0; i<in.n2; i++) indices.add(i);
                indices.sort((a, b) -> Double.compare(evals.get(b), evals.get(a)));

                double bestEval = -1e20;
                for (int iter = 0; iter < 3; iter++) {
                    boolean changed = false;
                    for (int idx : indices) {
                        qh.flip(idx);
                        double e = qh.eval(sim);
                        if (e > bestEval) { bestEval = e; changed = true; }
                        else qh.flip(idx);
                    }
                    if (!changed) break;
                }
                sim.query(qh.getCoords(), sc);
            }
        }
    }
}
