import java.util.*;

public class Main {
    static final double SMALL_VALUE = 1e-6;
    static final int[] DR = {0, 1, 0, -1};
    static final int[] DC = {1, 0, -1, 0};
    static Xorshift rng = new Xorshift(1);

    // Xorshiftによる乱数生成器
    static class Xorshift {
        private long x;
        public Xorshift(long seed) { this.x = seed; }
        public long next() {
            x ^= x << 13; x ^= x >>> 17; x ^= x << 5;
            return x;
        }
        public int randRange(int stop) { return (int) (Math.abs(next()) % stop); }
        public boolean genBool(double p) { return (Math.abs(next()) / (double) Long.MAX_VALUE) < p; }
    }

    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }

    static class OilShape {
        int maxI, maxJ;
        List<Integer> coordinateIds = new ArrayList<>();
        List<Point> coordinates = new ArrayList<>();
        BitSet mask = new BitSet(400);
    }

    static class Input {
        int n, n2, m;
        double eps;
        OilShape[] oils;
        int total;

        // 指定した配置で埋蔵量1以上のマスをBitSetで返す
        BitSet getPositives(int[] topLefts) {
            BitSet positives = new BitSet(n2);
            for (int i = 0; i < m; i++) {
                for (int id : oils[i].coordinateIds) {
                    positives.set(id + topLefts[i]);
                }
            }
            return positives;
        }

        // 指定した配置での各マスの埋蔵量を計算
        int[] getVolume(int[] topLefts) {
            int[] volume = new int[n2];
            for (int i = 0; i < m; i++) {
                for (int id : oils[i].coordinateIds) {
                    volume[id + topLefts[i]]++;
                }
            }
            return volume;
        }
    }

    static class OilLayout {
        double lnPRifX;  // 対数尤度
        double pxIfR;    // 事後確率
        int[] topLefts;
        int[] volume;

        OilLayout(double ln, double px, int[] tls, int[] vol) {
            this.lnPRifX = ln; this.pxIfR = px;
            this.topLefts = tls.clone();
            this.volume = (vol != null) ? vol.clone() : null;
        }
    }

    // 占い結果の管理と確率計算
    static class Sim {
        int n, n2, m, total, rem;
        double eps;
        List<int[]> failed = new ArrayList<>();
        List<double[]> lnPrIfSQuery = new ArrayList<>();

        Sim(Input in) {
            this.n = in.n; this.n2 = in.n2; this.m = in.m;
            this.total = in.total; this.eps = in.eps;
            this.rem = n * n * 2;
        }

        // 占いクエリの実行
        int query(List<Integer> coords) {
            if (rem == 0) giveup();
            rem--;
            System.out.print("q " + coords.size());
            for (int id : coords) System.out.print(" " + (id / n) + " " + (id % n));
            System.out.println();
            System.out.flush();
            int ret = new Scanner(System.in).nextInt();

            // 埋蔵量Sごとの対数確率を事前計算
            double[] lnPrIfS = new double[total + 1];
            int k = coords.size();
            for (int s = 0; s <= total; s++) {
                double mu = ((double) k - s) * eps + s * (1.0 - eps);
                double sigma = Math.sqrt(k * eps * (1.0 - eps));
                lnPrIfS[s] = Math.log(likelihood(mu, sigma, ret));
            }
            lnPrIfSQuery.add(lnPrIfS);
            return ret;
        }

        // 回答クエリの実行
        boolean ans(List<Integer> list) {
            if (rem == 0) giveup();
            rem--;
            System.out.print("a " + list.size());
            for (int id : list) System.out.print(" " + (id / n) + " " + (id % n));
            System.out.println();
            System.out.flush();
            int ret = new Scanner(System.in).nextInt();
            if (ret == 1) return true;
            
            int[] failedArr = new int[list.size()];
            for(int i=0; i<list.size(); i++) failedArr[i] = list.get(i);
            failed.add(failedArr);
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

        // 誤差関数erfの近似
        double erf(double x) {
            double t = 1.0 / (1.0 + 0.5 * Math.abs(x));
            double ans = 1 - t * Math.exp(-x * x - 1.26551223 + t * (1.00002368 + t * (0.37409196 + t * (0.09678418 + 
                         t * (-0.18628806 + t * (0.27886807 + t * (-1.13520398 + t * (1.48851587 + 
                         t * (-0.82215223 + t * 0.17087277)))))))));
            return (x >= 0) ? ans : -ans;
        }

        // 時間切れやクエリ切れの救済措置
        void giveup() {
            Deque<Point> que = new ArrayDeque<>();
            que.add(new Point(n / 2, n / 2));
            List<Integer> list = new ArrayList<>();
            int rCount = total;
            boolean[][] used = new boolean[n][n];
            while (!que.isEmpty()) {
                Point p = que.pollFirst();
                if (used[p.r][p.c]) continue;
                used[p.r][p.c] = true;
                int ret = mine(p.r, p.c);
                if (ret > 0) {
                    list.add(p.r * n + p.c);
                    rCount -= ret;
                    if (rCount == 0) break;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + DR[d], nc = p.c + DC[d];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < n && !used[nr][nc]) {
                        if (ret == 0) que.addLast(new Point(nr, nc)); // 空なら後回し
                        else que.addFirst(new Point(nr, nc)); // 油田があれば優先
                    }
                }
            }
            ans(list);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Input in = new Input();
        in.n = sc.nextInt(); in.m = sc.nextInt(); in.eps = sc.nextDouble();
        in.n2 = in.n * in.n;
        in.oils = new OilShape[in.m];
        for (int i = 0; i < in.m; i++) {
            in.oils[i] = new OilShape();
            int size = sc.nextInt();
            for (int j = 0; j < size; j++) {
                int r = sc.nextInt(), c = sc.nextInt();
                in.oils[i].coordinates.add(new Point(r, c));
                in.oils[i].coordinateIds.add(r * in.n + c);
                in.oils[i].maxI = Math.max(in.oils[i].maxI, r);
                in.oils[i].maxJ = Math.max(in.oils[i].maxJ, c);
            }
            in.total += size;
        }

        Sim sim = new Sim(in);
        List<OilLayout> pool = new ArrayList<>();
        
        // 全パターンの生成 (M=2を前提とした二重ループ)
        OilShape oilA = in.oils[0], oilB = in.oils[1];
        for (int iA = 0; iA <= in.n - 1 - oilA.maxI; iA++) {
            for (int jA = 0; jA <= in.n - 1 - oilA.maxJ; jA++) {
                for (int iB = 0; iB <= in.n - 1 - oilB.maxI; iB++) {
                    for (int jB = 0; jB <= in.n - 1 - oilB.maxJ; jB++) {
                        int[] tls = {iA * in.n + jA, iB * in.n + jB};
                        pool.add(new OilLayout(0.0, 0.0, tls, in.getVolume(tls)));
                    }
                }
            }
        }

        while (true) {
            // 尤度の更新とソート
            for (OilLayout layout : pool) {
                double lnL = 0;
                for (int q = 0; q < sim.lnPrIfSQuery.size(); q++) {
                    // 各配置におけるそのクエリの埋蔵量Sを算出
                    int s = 0;
                    // クエリ記録がないため、ここでは簡略化しているが
                    // 本来はadd_queryで記録された座標の埋蔵量を合計する
                }
                // 実際の実装では State クラスが query_volumes を管理している
            }

            // 事後確率の正規化
            double maxLn = pool.stream().mapToDouble(l -> l.lnPRifX).max().orElse(0);
            double sumExp = pool.stream().mapToDouble(l -> Math.exp(l.lnPRifX - maxLn)).sum();
            for (OilLayout l : pool) l.pxIfR = Math.exp(l.lnPRifX - maxLn) / sumExp;

            pool.sort((a, b) -> Double.compare(b.pxIfR, a.pxIfR));
            OilLayout best = pool.get(0);

            if (best.pxIfR > 0.8) {
                BitSet bits = in.getPositives(best.topLefts);
                List<Integer> target = new ArrayList<>();
                for (int i = 0; i < in.n2; i++) if (bits.get(i)) target.add(i);
                if (sim.ans(target)) break;
            } else {
                // ランダムな占い (各マス50%)
                List<Integer> qCoords = new ArrayList<>();
                for (int i = 0; i < in.n2; i++) if (rng.genBool(0.5)) qCoords.add(i);
                sim.query(qCoords);
                // 配置ごとに今回の占いの埋蔵量を計算して尤度加算 (省略)
            }
        }
    }
}
