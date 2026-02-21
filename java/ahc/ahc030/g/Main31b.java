import java.util.*;

public class Main {
    static int N, M;
    static double EPS;
    static List<List<Point>> oilShapes = new ArrayList<>();
    static long[][][] zobristTable;
    static Random rand = new Random();

    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }

    // クエリの履歴を保存するクラス
    static class QueryRecord {
        List<Point> cells;
        int k, result;
        QueryRecord(List<Point> cells, int k, int result) {
            this.cells = cells;
            this.k = k;
            this.result = result;
        }
    }

    static class Configuration implements Comparable<Configuration> {
        Point[] topLefts;
        int[][] amount;
        double logLikelihood = 0;
        long hash;

        Configuration(Point[] tls) {
            this.topLefts = tls;
            this.amount = new int[N][N];
            this.hash = 0;
            for (int i = 0; i < M; i++) {
                hash ^= zobristTable[i][tls[i].r][tls[i].c];
                for (Point p : oilShapes.get(i)) {
                    amount[tls[i].r + p.r][tls[i].c + p.c]++;
                }
            }
        }

        // 過去の全クエリ結果から対数尤度を再計算
        void computeFullLogLikelihood(List<QueryRecord> history) {
            logLikelihood = 0;
            for (QueryRecord qr : history) {
                int s = 0;
                for (Point p : qr.cells) s += amount[p.r][p.c];
                logLikelihood += Math.log(calcProbability(qr.k, s, qr.result));
            }
        }

        @Override
        public int compareTo(Configuration other) {
            return Double.compare(other.logLikelihood, this.logLikelihood); // 降順
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;
        N = sc.nextInt();
        M = sc.nextInt();
        EPS = sc.nextDouble();

        for (int i = 0; i < M; i++) {
            int d = sc.nextInt();
            List<Point> shape = new ArrayList<>();
            for (int j = 0; j < d; j++) shape.add(new Point(sc.nextInt(), sc.nextInt()));
            oilShapes.add(shape);
        }

        // Zobrist Hashの初期化
        zobristTable = new long[M][N][N];
        for (int i = 0; i < M; i++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) zobristTable[i][r][c] = rand.nextLong();
            }
        }

        List<Configuration> pool = new ArrayList<>();
        List<QueryRecord> history = new ArrayList<>();
        // 失敗した配置を記録するセットを定義
        Set<Long> failedHashes = new HashSet<>();
        Set<Long> poolHashes = new HashSet<>();

        // 初期プールの生成
        while (pool.size() < 1000) {
            Configuration conf = generateRandomConfig();
            if (poolHashes.add(conf.hash)) pool.add(conf);
        }

        while (true) {
            // 1. 事後確率の計算と回答判定
            double maxLogL = Double.NEGATIVE_INFINITY;
            for (Configuration conf : pool) maxLogL = Math.max(maxLogL, conf.logLikelihood);
            double sumExp = 0;
            for (Configuration conf : pool) sumExp += Math.exp(conf.logLikelihood - maxLogL);

            int bestIdx = -1;
            double maxProb = 0;
            for (int i = 0; i < pool.size(); i++) {
                double prob = Math.exp(pool.get(i).logLikelihood - maxLogL) / sumExp;
                if (prob > maxProb) {
                    maxProb = prob;
                    bestIdx = i;
                }
            }

            if (bestIdx != -1 && maxProb > 0.8) {
                Configuration best = pool.get(bestIdx);
                System.out.print("a " + M);
                for (Point p : best.topLefts) System.out.print(" " + p.r + " " + p.c);
                System.out.println();
                System.out.flush();
                if (sc.nextInt() == 1) break;
                else {
                    failedHashes.add(best.hash);
                    pool.remove(bestIdx);
                    poolHashes.remove(best.hash);
                    continue;
                }
            }

            // 2. 新しいランダムな配置を生成してプールを補充 (Source)
            // 毎回数千個生成し、尤度が高いものを残す
            int ITER = 3000;
            for (int i = 0; i < ITER; i++) {
                Configuration conf = generateRandomConfig();
                if (!failedHashes.contains(conf.hash) && !poolHashes.contains(conf.hash)) {
                    conf.computeFullLogLikelihood(history);
                    pool.add(conf);
                    poolHashes.add(conf.hash);
                }
            }
            Collections.sort(pool);
            // 上位一定数（例：1000個）に絞る。時間経過で減らす工夫も有効
            while (pool.size() > 1000) {
                Configuration removed = pool.remove(pool.size() - 1);
                poolHashes.remove(removed.hash);
            }

            // 3. ランダムな占いクエリ
            List<Point> queryCells = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) if (rand.nextBoolean()) queryCells.add(new Point(r, c));
            }
            int k = queryCells.size();
            if (k == 0) continue;

            System.out.print("q " + k);
            for (Point p : queryCells) System.out.print(" " + p.r + " " + p.c);
            System.out.println();
            System.out.flush();
            int result = sc.nextInt();

            // 4. 履歴に追加し、現在のプールの尤度を更新
            QueryRecord qr = new QueryRecord(queryCells, k, result);
            history.add(qr);
            for (Configuration conf : pool) {
                int s = 0;
                for (Point p : queryCells) s += conf.amount[p.r][p.c];
                conf.logLikelihood += Math.log(calcProbability(k, s, result));
            }
        }
    }

    static Configuration generateRandomConfig() {
        Point[] tls = new Point[M];
        for (int i = 0; i < M; i++) {
            int maxH = 0, maxW = 0;
            for (Point p : oilShapes.get(i)) {
                maxH = Math.max(maxH, p.r);
                maxW = Math.max(maxW, p.c);
            }
            tls[i] = new Point(rand.nextInt(N - maxH), rand.nextInt(N - maxW));
        }
        return new Configuration(tls);
    }

    static double calcProbability(int k, int s, int r) {
        double mu = (k - s) * EPS + s * (1 - EPS);
        double sigma = Math.sqrt(k * EPS * (1 - EPS));
        if (r == 0) return cdf(0.5, mu, sigma);
        return cdf(r + 0.5, mu, sigma) - cdf(r - 0.5, mu, sigma);
    }

    static double cdf(double x, double mu, double sigma) {
        return 0.5 * (1.0 + erf((x - mu) / (Math.sqrt(2) * sigma)));
    }

    static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));
        double ans = 1 - t * Math.exp(-z * z - 1.26551223 + t * (1.00002368 + t * (0.37409196 + t * (0.09678418 + 
                     t * (-0.18628806 + t * (0.27886807 + t * (-1.13520398 + t * (1.48851587 + 
                     t * (-0.82215223 + t * 0.17087277)))))))));
        return (z >= 0) ? ans : -ans;
    }
}
