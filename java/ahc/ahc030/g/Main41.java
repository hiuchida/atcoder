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
        int[][] amount; // 各マスの埋蔵量
        int[] querySums; // 各過去クエリにおける埋蔵量合計
        double logLikelihood = 0;
        long hash;

        Configuration(Point[] tls, List<QueryRecord> history) {
            this.topLefts = tls.clone();
            this.amount = new int[N][N];
            this.querySums = new int[history.size()];
            this.hash = 0;
            for (int i = 0; i < M; i++) {
                this.hash ^= zobristTable[i][tls[i].r][tls[i].c];
                for (Point p : oilShapes.get(i)) {
                    amount[tls[i].r + p.r][tls[i].c + p.c]++;
                }
            }
            // 初期状態の尤度計算
            for (int j = 0; j < history.size(); j++) {
                QueryRecord qr = history.get(j);
                for (Point p : qr.cells) querySums[j] += amount[p.r][p.c];
                logLikelihood += Math.log(calcProbability(qr.k, querySums[j], qr.result));
            }
        }

        // 近傍移動：油田を移動させて尤度とハッシュを差分更新
        void moveTo(int oilId, Point newTL, List<QueryRecord> history) {
            Point oldTL = topLefts[oilId];
            hash ^= zobristTable[oilId][oldTL.r][oldTL.c];
            hash ^= zobristTable[oilId][newTL.r][newTL.c];
            
            // 埋蔵量グリッドとクエリ合計の更新 (Source 18: move_toの最適化)
            for (Point p : oilShapes.get(oilId)) {
                int oldR = oldTL.r + p.r, oldC = oldTL.c + p.c;
                int newR = newTL.r + p.r, newC = newTL.c + p.c;
                amount[oldR][oldC]--;
                amount[newR][newC]++;
            }

            logLikelihood = 0;
            topLefts[oilId] = newTL;
            for (int j = 0; j < history.size(); j++) {
                int s = 0;
                // ここも本来は差分更新可能だが、簡略化のため再計算
                for (Point p : history.get(j).cells) s += amount[p.r][p.c];
                querySums[j] = s;
                logLikelihood += Math.log(calcProbability(history.get(j).k, s, history.get(j).result));
            }
        }

        Configuration copy(List<QueryRecord> history) {
            Configuration res = new Configuration(this.topLefts, history);
            res.logLikelihood = this.logLikelihood;
            res.hash = this.hash;
            return res;
        }

        @Override
        public int compareTo(Configuration other) {
            return Double.compare(other.logLikelihood, this.logLikelihood);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;
        N = sc.nextInt(); M = sc.nextInt(); EPS = sc.nextDouble();

        for (int i = 0; i < M; i++) {
            int d = sc.nextInt();
            List<Point> shape = new ArrayList<>();
            for (int j = 0; j < d; j++) shape.add(new Point(sc.nextInt(), sc.nextInt()));
            oilShapes.add(shape);
        }

        zobristTable = new long[M][N][N];
        for (int i = 0; i < M; i++)
            for (int r = 0; r < N; r++)
                for (int c = 0; c < N; c++) zobristTable[i][r][c] = rand.nextLong();

        List<QueryRecord> history = new ArrayList<>();
        List<Configuration> pool = new ArrayList<>();
        Configuration current = generateInitialConfig(history);

        while (true) {
            // 1. 焼きなましによるプールの更新 (Source 17, 18)
            pool = performAnnealing(current, history);
            
            // 2. 事後確率の計算と回答
            double maxLogL = Double.NEGATIVE_INFINITY;
            for (Configuration conf : pool) maxLogL = Math.max(maxLogL, conf.logLikelihood);
            double sumExp = 0;
            for (Configuration conf : pool) sumExp += Math.exp(conf.logLikelihood - maxLogL);

            int bestIdx = -1; double maxProb = 0;
            for (int i = 0; i < pool.size(); i++) {
                double pr = Math.exp(pool.get(i).logLikelihood - maxLogL) / sumExp;
                if (pr > maxProb) { maxProb = pr; bestIdx = i; }
            }

            if (bestIdx != -1 && maxProb > 0.8) {
                Configuration best = pool.get(bestIdx);
                System.out.print("a " + M);
                for (Point p : best.topLefts) System.out.print(" " + p.r + " " + p.c);
                System.out.println(); System.out.flush();
                if (sc.nextInt() == 1) break;
                else {
                    pool.remove(bestIdx);
                    // 失敗した配置は二度と選ばないようハッシュ等で管理するのも有効
                    continue;
                }
            }

            // 3. 占いクエリ (ここではランダム占い)
            List<Point> queryCells = new ArrayList<>();
            for (int r = 0; r < N; r++) 
                for (int c = 0; c < N; c++) if (rand.nextBoolean()) queryCells.add(new Point(r, c));
            
            System.out.print("q " + queryCells.size());
            for (Point p : queryCells) System.out.print(" " + p.r + " " + p.c);
            System.out.println(); System.out.flush();
            int resVal = sc.nextInt();
            history.add(new QueryRecord(queryCells, queryCells.size(), resVal));

            // 現在の最良状態を更新
            current = pool.get(0).copy(history);
        }
    }

    // 焼きなまし法本体 (Source 18)
    static List<Configuration> performAnnealing(Configuration start, List<QueryRecord> history) {
        Configuration current = start.copy(history);
        List<Configuration> pool = new ArrayList<>();
        Set<Long> seen = new HashSet<>();
        
        double tempStart = 2.0, tempEnd = 1.0; // Source 18の冷却スケジュール
        int iterations = 10000;

        for (int i = 0; i < iterations; i++) {
            double temp = tempStart + (tempEnd - tempStart) * (i / (double)iterations);
            
            int oilId = rand.nextInt(M);
            Point oldTL = current.topLefts[oilId];
            Point newTL = getRandomValidPoint(oilId);

            double oldL = current.logLikelihood;
            current.moveTo(oilId, newTL, history);
            double newL = current.logLikelihood;

            // メトロポリス判定
            if (newL > oldL || rand.nextDouble() < Math.exp((newL - oldL) / temp)) {
                // 採用。途中の良い状態をプールに保存 (Source 17)
                if (seen.add(current.hash)) {
                    pool.add(current.copy(history));
                }
            } else {
                // 却下。元に戻す
                current.moveTo(oilId, oldTL, history);
            }
        }
        Collections.sort(pool);
        return pool.subList(0, Math.min(pool.size(), 1000));
    }

    static Point getRandomValidPoint(int oilId) {
        int maxH = 0, maxW = 0;
        for (Point p : oilShapes.get(oilId)) {
            maxH = Math.max(maxH, p.r); maxW = Math.max(maxW, p.c);
        }
        return new Point(rand.nextInt(N - maxH), rand.nextInt(N - maxW));
    }

    static Configuration generateInitialConfig(List<QueryRecord> history) {
        Point[] tls = new Point[M];
        for (int i = 0; i < M; i++) tls[i] = getRandomValidPoint(i);
        return new Configuration(tls, history);
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
