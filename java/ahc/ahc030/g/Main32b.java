import java.util.*;

public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	
	static int lastid=1;
	static int seq() {
		return lastid++;
	}
	
    static final double SMALL_VALUE = 1e-6;
    static final int[] DR = {0, 1, 0, -1};
    static final int[] DC = {1, 0, -1, 0};
    static Xorshift rng = new Xorshift(1);

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

    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }

    static class OilShape {
        int maxI, maxJ;
        List<Integer> coordinateIds = new ArrayList<>();
        List<int[]> coordinates = new ArrayList<>();
        BitSet mask = new BitSet(400);
    }

    static class Input {
        int n, n2, m;
        double eps;
        OilShape[] oils;
        int total;

        BitSet getPositives(List<Integer> topLefts) {
            BitSet positives = new BitSet(n2);
            for (int oil_id = 0; oil_id < m; oil_id++) {
                for (int id : oils[oil_id].coordinateIds) {
                    positives.set(id + topLefts.get(oil_id));
                }
            }
            return positives;
        }

        int[] getVolume(List<Integer> topLefts) {
            int[] volume = new int[n2];
            for (int oil_id = 0; oil_id < m; oil_id++) {
                for (int id : oils[oil_id].coordinateIds) {
                    volume[id + topLefts.get(oil_id)] += 1;
                }
            }
            return volume;
        }
    }

    static class OilLayout {
    	int id=seq();
        long hash;
        double lnPRifX;
        double pxIfR;
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
        @Override
        public String toString() {
        	return topLeftQueryVolumes.toString();
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
            for (OilState os : oilStates) this.hash ^= os.hashes[0];
            this.topLefts = new ArrayList<>(Collections.nCopies(input.m, 0));
//            this.volumes = new int[input.n2];
            this.volumes = null;
            this.queryVolumes = new ArrayList<>();
        }

        void moveTo(int oilId, int newTL) {
            OilState os = oilStates[oilId];
            hash ^= os.hashes[topLefts.get(oilId)] ^ os.hashes[newTL];
            for (int q = 0; q < queryVolumes.size(); q++) {
                queryVolumes.set(q, queryVolumes.get(q) + os.topLeftQueryVolumes.get(newTL).get(q) - os.topLeftQueryVolumes.get(topLefts.get(oilId)).get(q));
            }
            if (volumes!=null) {
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
        @Override
        public String toString() {
        	StringBuilder sb=new StringBuilder();
//        	for (OilState os : oilStates) {
//        		sb.append(os.toString()).append("\n");
//        	}
        	if (volumes!=null) {
            	for (int y=0; y<input.n; y++) {
            		for (int x=0; x<input.n; x++) {
            			sb.append(volumes[y*input.n+x]).append(" ");
            		}
            		sb.append("\n");
            	}
        	}
        	return sb.toString();
        }
    }

    static class Sim {
        int n, n2, m, total, rem;
        double eps;
        List<int[]> queries = new ArrayList<>();
        List<int[]> failed = new ArrayList<>();
        List<double[]> lnPrIfSQuery = new ArrayList<>();

        Sim(Input in) {
            this.n = in.n; this.n2 = in.n2; this.m = in.m;
            this.total = in.total; this.eps = in.eps;
            this.rem = n * n * 2;
        }

        int query(List<Integer> coords, Scanner sc) {
            rem--;
            System.out.print("q " + coords.size());
            for (int id : coords) System.out.print(" " + (id / n) + " " + (id % n));
            System.out.println(); System.out.flush();
            int ret = sc.nextInt();
            double[] lnPrIfS = new double[total + 1];
            int k = coords.size();
            for (int s = 0; s <= total; s++) {
                double mu = (k - s) * eps + s * (1.0 - eps);
                double sigma = Math.sqrt(k * eps * (1.0 - eps));
                lnPrIfS[s] = Math.log(likelihood(mu, sigma, ret));
            }
            lnPrIfSQuery.add(lnPrIfS);
            return ret;
        }

        boolean ans(List<Integer> list, Scanner sc) {
            rem--;
            System.out.print("a " + list.size());
            for (int id : list) System.out.print(" " + (id / n) + " " + (id % n));
            System.out.println(); System.out.flush();
            int ret = sc.nextInt();
            if (ret == 1) return true;
            int[] f = new int[list.size()];
            for(int i=0; i<list.size(); i++) f[i] = list.get(i);
            failed.add(f);
            return false;
        }

        int mine(int r, int c) {
            rem--;
            System.out.println("q 1 " + r + " " + c);
            System.out.flush();
            return new Scanner(System.in).nextInt();
        }

        double likelihood(double mu, double sigma, int res) {
            double b = res + 0.5;
            if (res == 0) return normalCdf(mu, sigma, b);
            return normalCdf(mu, sigma, b) - normalCdf(mu, sigma, res - 0.5);
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

        double getLnPRifX(State state, int[] vol, List<Integer> tls) {
            for (int[] f : failed) {
                boolean same = true;
                int count = 0;
                for(int id : f) if(vol[id] == 0) { same = false; break; }
                if(same) {
                    for(int v : vol) if(v > 0) count++;
                    if(count == f.length) return -1e20;
                }
            }
            double res = 0;
            for (int q = 0; q < lnPrIfSQuery.size(); q++) {
                int s = 0;
                for (int oilId = 0; oilId < tls.size(); oilId++) {
                    s += state.oilStates[oilId].topLeftQueryVolumes.get(tls.get(oilId)).get(q);
                }
                res += lnPrIfSQuery.get(q)[s];
            }
            return res;
        }

        void giveup(Scanner sc) {
            Deque<Point> que = new ArrayDeque<>();
            que.add(new Point(n / 2, n / 2));
            List<Integer> list = new ArrayList<>();
            int remT = total;
            boolean[][] used = new boolean[n][n];
            while (!que.isEmpty()) {
            	Point p = que.pollFirst();
                if (used[p.r][p.c]) continue;
                used[p.r][p.c] = true;
                int ret = mine(p.r, p.c);
                if (ret > 0) {
                    list.add(p.r * n + p.c);
                    remT -= ret; if (remT <= 0) break;
                }
                for (int d=0; d<4; d++) {
                    int ni = p.r + DR[d], nj = p.c + DC[d];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < n && !used[ni][nj]) {
                        if (ret == 0) que.addLast(new Point(ni, nj));
                        else que.addFirst(new Point(ni, nj));
                    }
                }
            }
            ans(list, sc);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
		start=System.currentTimeMillis();
        long startTime = System.currentTimeMillis();
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
//        System.err.println(state);
        List<OilLayout> pool = new ArrayList<>();
        int ITER = 4000000 / (2 * in.n2);
//        int ITER = 100;

        for (int t = 0;; t++) {
            if (sim.rem <= 0) break;
            if (System.currentTimeMillis() - startTime > 2900) { sim.giveup(sc); break; }

            // 各配置の対数尤度を更新
            for (OilLayout layout : pool) {
                if (layout.volume == null && !sim.failed.isEmpty()) {
                    layout.volume = in.getVolume(layout.topLefts);
                }
                layout.lnPRifX = sim.getLnPRifX(state, layout.volume, layout.topLefts);
            }
            Collections.shuffle(pool);
            pool.sort((a, b) -> Double.compare(b.lnPRifX, a.lnPRifX));

            Map<Long, Double> hashLogL = new HashMap<>();
            for (OilLayout l : pool) hashLogL.put(l.hash, l.lnPRifX);

            // 初回のみランダムな配置を生成
            if (t == 0) {
                for (int i = 0; i < ITER; i++) {
                    for (int oilId = 0; oilId < in.m; oilId++) {
                        OilShape oil = in.oils[oilId]; // ループ内でインデックスを指定して取得
                        int r = rng.randRange(in.n - oil.maxI);
                        int c = rng.randRange(in.n - oil.maxJ);
                        state.moveTo(oilId, r * in.n + c);
                    }
//                    System.err.println("i="+i+" "+state);
                    if (!hashLogL.containsKey(state.hash)) {
                        hashLogL.put(state.hash, 0.0);
                        pool.add(new OilLayout(state.hash, 0.0, 0.0, state.topLefts, state.volumes));
                    }
                }
            }

            // 正規化とプールの削減
            double maxLn = pool.get(0).lnPRifX;
            for (OilLayout l : pool) l.pxIfR = Math.exp(l.lnPRifX - maxLn);
            double sumExp = pool.stream().mapToDouble(l -> l.pxIfR).sum();
            for (OilLayout l : pool) l.pxIfR /= sumExp;

            while (pool.size() > 1 && pool.get(pool.size() - 1).pxIfR < 1e-9) pool.remove(pool.size() - 1);
            
            // プールサイズの動的変更
            double timeRem = Math.max(0, 1.0 - (System.currentTimeMillis() - startTime) / 3000.0);
            int targetSize = (int) Math.max(2, (ITER * 0.01) * timeRem);
            if (pool.size() > targetSize) {
                int s = pool.size();
                while (s > 2 && pool.get(0).pxIfR * 1e-4 > pool.get(s - 1).pxIfR) s--;
                if (pool.size() > s) pool = new ArrayList<>(pool.subList(0, Math.max(2, s)));
            }

            // 自信があれば回答
            OilLayout best = pool.get(0);
            double bestProb = pool.get(0).pxIfR;
            long elap=System.currentTimeMillis();
            if (!RELEASE) System.err.println((t+1)+"("+(elap-start)+"):id="+best.id+" px_if_R="+best.pxIfR);
//            System.err.println(state);
            if (bestProb > 0.9) {
                BitSet bits = in.getPositives(pool.get(0).topLefts);
                List<Integer> target = new ArrayList<>();
                for (int i = 0; i < in.n2; i++) if (bits.get(i)) target.add(i);
                if (sim.ans(target, sc)) break;
            } else {
                // ランダムな占い
                List<Integer> qCoords = new ArrayList<>();
                for (int i = 0; i < in.n2; i++) if (rng.genBool(0.5)) qCoords.add(i);
                sim.query(qCoords, sc);
                state.addQuery(qCoords);
            }
        }
    }
	static long start;
	static int iteration = 0;
}
