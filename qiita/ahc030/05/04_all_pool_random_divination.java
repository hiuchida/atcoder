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

    // 油田の配置情報
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

    // 油田の状態管理（高速化のためのメモ化）
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
            for (int i = 0; i < input.m; i++) oilStates[i] = new OilState(input.n2);
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
            if (rem-- <= 0) {
                System.err.println(now()+"giveup by query");
                throw new AppException();
            }
            System.out.print("q " + coords.size());
            for (int ij : coords) System.out.print(" " + (ij / n) + " " + (ij % n));
            System.out.println();
            System.out.flush();
            int ret = sc.nextInt();
            List<Double> lnPrIfS = new ArrayList<>();
            double sigma = Math.sqrt(coords.size() * eps * (1.0 - eps));
            for (int s = 0; s <= total; s++) {
                double mu = (coords.size() - s) * eps + s * (1.0 - eps);
                double v = likelihood(mu, sigma, ret);
                v = log(v);
                lnPrIfS.add(v);
            }
            lnPrIfSQuery.add(lnPrIfS);
            return ret;
        }

        boolean ans(List<Integer> T) {
            if (rem-- <= 0) {
                System.err.println(now()+"giveup by ans");
                throw new AppException();
            }
            System.out.print("a " + T.size());
            for (int ij : T) System.out.print(" " + (ij / n) + " " + (ij % n));
            System.out.println();
            System.out.flush();
            int ret = sc.nextInt();
            if (ret == 1) return true;
            failed.add(T);
            return false;
        }

        int mine(int r, int c) {
            if (rem-- <= 0) {
                System.err.println(now()+"giveup by mine");
                throw new AppException();
            }
            System.out.println("q 1 " + r + " " + c);
            System.out.flush();
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

        void giveup(Input input, List<OilLayout> pool) {
            System.err.println(now()+"giveup start");
            double[] prob = getPosteriorProbability(input, pool);
            List<Integer> indices = new ArrayList<>();
            for (int ij = 0; ij < input.n2; ij++) indices.add(ij);
            Collections.sort(indices, (a, b) -> Double.compare(prob[b], prob[a]));
            Deque<int[]> que = new ArrayDeque<>();
            for (int ij : indices) que.add(new int[] {ij / input.n, ij % input.n});
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
                        if (ret == 1) que.addFirst(new int[]{nr, nc});
                    }
                }
            }
            ans(list);
            throw new AppException();
        }
    }

    static double[] getPosteriorProbability(Input input, List<OilLayout> pool) {
    	// 盤面の事後確率を求める
    	// 前提としてpxIfRが正規化されている（全poolの合計が1）
    	// 正規化されていない場合、ここで累計double totalを計算し、もう一つループしてprob[ij]/=totalで最大1の確率にする
    	double[] prob = new double[input.n2];
		for (OilLayout l : pool) {
			for (int ij = 0; ij < input.n2; ij++) {
        		if (l.volume[ij] > 0) prob[ij] += l.pxIfR;
    		}
    	}
    	return prob;
    }
    static List<Integer> getDivinationQuery(Input input, List<OilLayout> pool, Sim sim, int t) {
        // ランダムな占い
        List<Integer> coords = new ArrayList<>();
        for (int i = 0; i < input.n2; i++) {
            if (rng.genBool(0.5)) coords.add(i);
        }
        if (coords.isEmpty()) coords.add(0);
        return coords;
    }

    static void solve() {
        if (System.getProperty("DEBUG")!=null) DEBUG=true;
        Scanner sc = new Scanner(System.in);
        Input input = new Input();
        input.n = sc.nextInt(); input.m = sc.nextInt(); input.eps = sc.nextDouble();
        input.n2 = input.n * input.n;
        input.oils = new OilShape[input.m];
        for (int i = 0; i < input.m; i++) {
            input.oils[i] = new OilShape();
            int d = sc.nextInt();
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

        Sim sim = new Sim(input, sc);
        State state = new State(input);
        List<OilLayout> pool = new ArrayList<>();

        // M=2専用の全配置生成
        OilShape oilA = input.oils[0], oilB = input.oils[1];
        for (int iA = 0; iA < input.n - oilA.maxI; iA++) {
            for (int jA = 0; jA < input.n - oilA.maxJ; jA++) {
                state.moveTo(0, iA * input.n + jA);
                for (int iB = 0; iB < input.n - oilB.maxI; iB++) {
                    for (int jB = 0; jB < input.n - oilB.maxJ; jB++) {
                        state.moveTo(1, iB * input.n + jB);
                        pool.add(new OilLayout(0.0, 0.0, state.topLefts, input.getVolume(state.topLefts)));
                    }
                }
            }
        }
        System.err.println(now()+" pool.size()="+pool.size()+" total="+input.total);

        for (int t=0; true; t++) {
            if (System.currentTimeMillis() - startTime > 2900) sim.giveup(input, pool);
            // 各配置の対数尤度を更新
            for (OilLayout l : pool) {
                l.lnPRifX = sim.getLnPRifX(state.oilStates, l.volume, l.topLefts);
            }
            Collections.shuffle(pool, rand);
            pool.sort((a, b) -> Double.compare(b.lnPRifX, a.lnPRifX));

            double maxLn = pool.get(0).lnPRifX, sumPx = 0;
            for (OilLayout l : pool) {
                l.pxIfR = Math.exp(l.lnPRifX - maxLn);
                sumPx += l.pxIfR;
            }
            for (OilLayout l : pool) l.pxIfR /= sumPx;

            if (DEBUG) System.err.println(now()+t+" pool.get(0).pxIfR="+pool.get(0).pxIfR);
            if (pool.get(0).pxIfR > 0.8) {
                BitSet bestBits = input.getPositives(pool.get(0).topLefts);
                List<Integer> T = new ArrayList<>();
                for (int i = 0; i < input.n2; i++) {
                    if (bestBits.get(i)) T.add(i);
                }
                if (sim.ans(T)) break;
            } else {
                List<Integer> coords = getDivinationQuery(input, pool, sim, t);
                sim.query(coords);
                state.addQuery(coords);
            }
        }
    }
    public static void main(String[] args) {
        try {
            solve();
            System.err.println(now()+"!Complete");
        } catch (AppException e) {
            System.err.println(now()+"!Abort");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static class AppException extends RuntimeException {
    }
    static double log(double v) {
        return (v==0) ? -999 : Math.log(v);
    }
    static String now() {
        return String.format("%04d:", System.currentTimeMillis()-startTime);
    }
    static long startTime = System.currentTimeMillis();
    static Random rand = new Random(0);
}
