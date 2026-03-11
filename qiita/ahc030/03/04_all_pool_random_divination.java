import java.util.*;

public class Main {
	// ループ内のerr出力
	// java起動時に-DDEBUG=trueを付けると、実行時にtrueに切り替える
	static boolean DEBUG=false;
    static final double SMALL_VALUE = 1e-6;
    static final int[][] DIJ = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // 疑似乱数は使わずRandomを使う。呼び出し元をそのままにするため、クラスは残す。
    static class Xorshift {
        private long x;
        public Xorshift(long seed) { this.x = seed; }
//        public long next() {
//            x ^= x << 13;
//            x ^= x >>> 17;
//            x ^= x << 5;
//            return x;
//        }
//        public double random() {
//            return (double)(next() & 0x7FFFFFFFFFFFFFFFL) / Long.MAX_VALUE;
//        }
//        public double random() { return (next() >>> 11) * 0x1.0p-53; }
        public double random() {
        	return rand.nextDouble();
        }
        public boolean genBool(double p) { return random() < p; }
    }

    static Xorshift rng = new Xorshift(1);

    static class OilLayout {
        double lnPRifX;
        double pxIfR;
        int[] topLefts;
        int[] volume;

        OilLayout(double ln, double px, int[] tls, int[] vol) {
            this.lnPRifX = ln;
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
        int n, n2, m, total;
        double eps;
        OilShape[] oils;

        BitSet getPositives(int[] topLefts) {
            BitSet positives = new BitSet(n2);
            for (int oilId = 0; oilId < m; oilId++) {
                int tl = topLefts[oilId];
                for (int ij : oils[oilId].coordinateIds) {
                    positives.set(ij + tl);
                }
            }
            return positives;
        }

        int[] getVolume(int[] topLefts) {
            int[] vol = new int[n2];
            for (int oilId = 0; oilId < m; oilId++) {
                int pij = topLefts[oilId];
                for (int ij : oils[oilId].coordinateIds) {
                    vol[ij + pij]++;
                }
            }
            return vol;
        }
    }

    static class OilState {
        List<List<Integer>> topLeftQueryVolumes;
        OilState(int n2) {
            topLeftQueryVolumes = new ArrayList<>(n2);
            for (int i = 0; i < n2; i++) topLeftQueryVolumes.add(new ArrayList<>());
        }
    }

    static class State {
        OilState[] oilStates;
        int[] topLefts;
        int[] volumes;
        List<Integer> queryVolumes = new ArrayList<>();
        Input input;

        State(Input input) {
            this.input = input;
            this.oilStates = new OilState[input.m];
            for (int i = 0; i < input.m; i++) this.oilStates[i] = new OilState(input.n2);
            this.topLefts = new int[input.m];
            this.volumes = new int[input.n2];
        }

        void moveTo(int oilId, int newTopLeft) {
            OilState os = oilStates[oilId];
            for (int q = 0; q < queryVolumes.size(); q++) {
                int diff = os.topLeftQueryVolumes.get(newTopLeft).get(q) 
                         - os.topLeftQueryVolumes.get(topLefts[oilId]).get(q);
                queryVolumes.set(q, queryVolumes.get(q) + diff);
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
                for (int di = 0; di < input.n - oil.maxI; di++) {
                    for (int dj = 0; dj < input.n - oil.maxJ; dj++) {
                        int topLeft = di * input.n + dj;
                        int c = 0;
                        for (int ij : oil.coordinateIds) {
                            if (inQuery[topLeft + ij]) c++;
                        }
                        os.topLeftQueryVolumes.get(topLeft).add(c);
                    }
                }
            }
            int[] currentVol = input.getVolume(topLefts);
            int c = 0;
            for (int ij : queryCoordinates) c += currentVol[ij];
            queryVolumes.add(c);
        }
    }

    static class Sim {
        int n, n2, m, total, rem;
        double eps;
        List<List<Double>> lnPrIfSQuery = new ArrayList<>();
        List<List<Integer>> failed = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        Sim(Input input) {
            this.n = input.n; this.n2 = input.n2; this.m = input.m;
            this.total = input.total; this.eps = input.eps;
            this.rem = n2 * 2;
        }

        double normalCdf(double mean, double stdDev, double x) {
            return 0.5 * (1.0 + erf((x - mean) / (stdDev * Math.sqrt(2.0))));
        }

        double erf(double x) {
            double a1 = 0.254829592, a2 = -0.284496736, a3 = 1.421413741, a4 = -1.453152027, a5 = 1.061405429, p = 0.3275911;
            int sign = (x < 0) ? -1 : 1; x = Math.abs(x);
            double t = 1.0 / (1.0 + p * x);
            double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);
            return sign * y;
        }

        double likelihood(double mean, double stdDev, int res) {
            double b = res + 0.5;
            if (res == 0) return normalCdf(mean, stdDev, b);
            return normalCdf(mean, stdDev, b) - normalCdf(mean, stdDev, res - 0.5);
        }

        int query(List<Integer> coords) {
            if (rem-- <= 0) {
                System.err.println(now()+"giveup by query");
            	System.exit(0);
            }
            System.out.print("q " + coords.size());
            for (int ij : coords) System.out.print(" " + (ij / n) + " " + (ij % n));
            System.out.println(); System.out.flush();
            int ret = sc.nextInt();
            List<Double> lnPrIfS = new ArrayList<>();
            double sigma = Math.sqrt(coords.size() * eps * (1.0 - eps));
            for (int S = 0; S <= total; S++) {
                double mu = (coords.size() - S) * eps + S * (1.0 - eps);
                lnPrIfS.add(Math.log(Math.max(likelihood(mu, sigma, ret), 1e-300)));
            }
            lnPrIfSQuery.add(lnPrIfS);
            return ret;
        }

        boolean ans(List<Integer> T) {
            if (rem-- <= 0) {
                System.err.println(now()+"giveup by ans");
            	System.exit(0);
            }
            System.out.print("a " + T.size());
            for (int ij : T) System.out.print(" " + (ij / n) + " " + (ij % n));
            System.out.println(); System.out.flush();
            int ret = sc.nextInt();
            if (ret == 1) return true;
            failed.add(T);
            return false;
        }

        int mine(int r, int c) {
            if (rem-- <= 0) {
                System.err.println(now()+"giveup by mine");
            	System.exit(0);
            }
            System.out.println("q 1 " + r + " " + c); System.out.flush();
            return sc.nextInt();
        }

        double getLnPRifX(OilState[] oilStates, int[] layoutVol, int[] topLefts) {
            for (List<Integer> f : failed) {
                int count = 0;
                for (int i = 0; i < n2; i++) if (layoutVol[i] > 0) count++;
                if (count != f.size()) continue;
                boolean same = true;
                for (int ij : f) if (layoutVol[ij] == 0) { same = false; break; }
                if (same) return -1e20;
            }
            double lnPR = 0.0;
            for (int q = 0; q < lnPrIfSQuery.size(); q++) {
                int S = 0;
                for (int oilId = 0; oilId < m; oilId++) {
                    S += oilStates[oilId].topLeftQueryVolumes.get(topLefts[oilId]).get(q);
                }
                lnPR += lnPrIfSQuery.get(q).get(S);
            }
            return lnPR;
        }

        void giveup() {
            System.err.println(now()+"giveup start");
            Deque<int[]> que = new ArrayDeque<>();
            que.add(new int[]{n / 2, n / 2});
            List<Integer> list = new ArrayList<>();
            int remaining = total;
            boolean[][] used = new boolean[n][n];
            while (!que.isEmpty() && remaining > 0) {
                int[] curr = que.pollFirst();
                int r = curr[0], c = curr[1];
                if (used[r][c]) continue; used[r][c] = true;
                int ret = mine(r, c);
                if (ret > 0) {
                    list.add(r * n + c); remaining -= ret;
                }
                for (int[] d : DIJ) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < n && !used[nr][nc]) {
                        if (ret == 0) que.addLast(new int[]{nr, nc});
                        else que.addFirst(new int[]{nr, nc});
                    }
                }
            }
            ans(list); System.exit(0);
        }
    }

    public static void main(String[] args) {
    	if (System.getProperty("DEBUG")!=null) DEBUG=true;
        Scanner sc = new Scanner(System.in);
        Input input = new Input();
        input.n = sc.nextInt(); input.m = sc.nextInt(); input.eps = sc.nextDouble();
        input.n2 = input.n * input.n;
        input.oils = new OilShape[input.m];
        for (int i = 0; i < input.m; i++) {
            int d = sc.nextInt();
            input.oils[i] = new OilShape();
            for (int j = 0; j < d; j++) {
                int r = sc.nextInt(), c = sc.nextInt();
                input.oils[i].coordinates.add(new int[]{r, c});
            }
            input.total += d;
            OilShape os = input.oils[i];
            os.coordinateIds = new int[d];
            for (int j = 0; j < d; j++) {
                os.maxI = Math.max(os.maxI, os.coordinates.get(j)[0]);
                os.maxJ = Math.max(os.maxJ, os.coordinates.get(j)[1]);
                os.coordinateIds[j] = os.coordinates.get(j)[0] * input.n + os.coordinates.get(j)[1];
                os.mask.set(os.coordinateIds[j]);
            }
        }
        if (input.m != 2) {
        	System.err.println(now()+"not support m="+input.m);
        	System.exit(0);
        }

        Sim sim = new Sim(input);
        State state = new State(input);
        List<OilLayout> pool = new ArrayList<>();
        
        if (input.m == 2) {
            OilShape s0 = input.oils[0], s1 = input.oils[1];
            for (int i0 = 0; i0 < input.n - s0.maxI; i0++) {
                for (int j0 = 0; j0 < input.n - s0.maxJ; j0++) {
                    state.moveTo(0, i0 * input.n + j0);
                    for (int i1 = 0; i1 < input.n - s1.maxI; i1++) {
                        for (int j1 = 0; j1 < input.n - s1.maxJ; j1++) {
                            state.moveTo(1, i1 * input.n + j1);
                            pool.add(new OilLayout(0.0, 0.0, state.topLefts, input.getVolume(state.topLefts)));
                        }
                    }
                }
            }
        }
        System.err.println(now()+" pool.size()="+pool.size()+" total="+input.total);

        for (int t=0; true; t++) {
            if (System.currentTimeMillis() - startTime > 2900) sim.giveup();
            // 各配置の対数尤度を更新
            for (OilLayout l : pool) {
                l.lnPRifX = sim.getLnPRifX(state.oilStates, l.volume, l.topLefts);
            }
            Collections.shuffle(pool, rand);
            pool.sort((a, b) -> Double.compare(b.lnPRifX, a.lnPRifX));
            
            double maxLn = pool.get(0).lnPRifX, sumPx = 0;
            for (OilLayout l : pool) {
                l.pxIfR = Math.exp(l.lnPRifX - maxLn); sumPx += l.pxIfR;
            }
            for (OilLayout l : pool) l.pxIfR /= sumPx;

            if (DEBUG) System.err.println(now()+t+" pool.get(0).pxIfR="+pool.get(0).pxIfR);
            if (pool.get(0).pxIfR > 0.8) {
                BitSet bestBits = input.getPositives(pool.get(0).topLefts);
                List<Integer> T = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) if (bestBits.get(i)) T.add(i);
                if (sim.ans(T)) break;
            } else {
                List<Integer> q = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) if (rng.genBool(0.5)) q.add(i);
                if (q.isEmpty()) q.add(0);
                sim.query(q);
                state.addQuery(q); // これにより oilStates 内の各座標での合計埋蔵量が記録され、尤度計算に反映される
            }
        }
    }
    static String now() {
    	return String.format("%04d:", System.currentTimeMillis()-startTime);
    }
    static long startTime = System.currentTimeMillis();
    static Random rand = new Random(0);
}
