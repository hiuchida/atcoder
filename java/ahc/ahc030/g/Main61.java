import java.util.*;

public class Main {
    static int N, M;
    static double EPS;
    static List<List<Point>> oilShapes = new ArrayList<>();
    static long[][][] zobristTable;
    static Random rand = new Random();
    static long startTime;
    static boolean isEasyCase; // 簡単なケースかどうかの判定フラグ

    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            return r == p.r && c == p.c;
        }
        @Override
        public int hashCode() { return r * 31 + c; }
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
        int[][] amount;
        int[] querySums;
        double logLikelihood = 0;
        double prob = 0;
        long hash;

        Configuration(Point[] tls, List<QueryRecord> history) {
            this.topLefts = tls.clone();
            this.amount = new int[N][N];
            this.querySums = new int[history.size()];
            this.hash = 0;
            for (int i = 0; i < M; i++) {
                hash ^= zobristTable[i][tls[i].r][tls[i].c];
                for (Point p : oilShapes.get(i)) amount[tls[i].r + p.r][tls[i].c + p.c]++;
            }
            for (int j = 0; j < history.size(); j++) {
                for (Point p : history.get(j).cells) querySums[j] += amount[p.r][p.c];
                logLikelihood += Math.log(calcProbability(history.get(j).k, querySums[j], history.get(j).result));
            }
        }

        // 差分更新を伴う移動
        void moveOil(int id, Point nextTL, List<QueryRecord> history) {
            Point oldTL = topLefts[id];
            hash ^= zobristTable[id][oldTL.r][oldTL.c];
            hash ^= zobristTable[id][nextTL.r][nextTL.c];
            for (Point p : oilShapes.get(id)) {
                amount[oldTL.r + p.r][oldTL.c + p.c]--;
                amount[nextTL.r + p.r][nextTL.c + p.c]++;
            }
            topLefts[id] = nextTL;
            logLikelihood = 0;
            for (int j = 0; j < history.size(); j++) {
                int s = 0;
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
        public int compareTo(Configuration o) { return Double.compare(o.logLikelihood, this.logLikelihood); }
    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;
        N = sc.nextInt(); M = sc.nextInt(); EPS = sc.nextDouble();

        // 簡単なケースか判定 (N^2 * M^2 * EPS < 2000)
        isEasyCase = (N * N * M * M * EPS < 2000);

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
        Configuration current = generateInitialConfig(history);

        while (true) {
            // 1. 焼きなましによるプール更新
            List<Configuration> pool = updatePoolWithSA(current, history);

            // 2. 事後確率の算出と回答判定
            double maxLogL = Double.NEGATIVE_INFINITY;
            for (Configuration c : pool) maxLogL = Math.max(maxLogL, c.logLikelihood);
            double sumExp = 0;
            for (Configuration c : pool) sumExp += Math.exp(c.logLikelihood - maxLogL);
            int bestIdx = -1; double maxProb = 0;
            for (int i = 0; i < pool.size(); i++) {
                pool.get(i).prob = Math.exp(pool.get(i).logLikelihood - maxLogL) / sumExp;
                if (pool.get(i).prob > maxProb) { maxProb = pool.get(i).prob; bestIdx = i; }
            }

            // [00_complete相当] 確信度が閾値を超えたら回答
            if (bestIdx != -1 && maxProb > 0.90) { // 延長戦用により慎重な閾値
                Configuration best = pool.get(bestIdx);
                System.out.print("a " + M);
                for (Point p : best.topLefts) System.out.print(" " + p.r + " " + p.c);
                System.out.println(); System.out.flush();
                if (sc.nextInt() == 1) break;
                else { current.logLikelihood = -1e18; continue; }
            }

            // 3. 相互情報量を最大化するクエリ探索
            Set<Point> querySet = findBestQuery(pool);
            System.out.print("q " + querySet.size());
            for (Point p : querySet) System.out.print(" " + p.r + " " + p.c);
            System.out.println(); System.out.flush();
            int resVal = sc.nextInt();
            history.add(new QueryRecord(new ArrayList<>(querySet), querySet.size(), resVal));
            current = pool.get(0).copy(history);
        }
    }

    // 簡単なケースで探索数を5倍にする焼きなまし
    static List<Configuration> updatePoolWithSA(Configuration start, List<QueryRecord> history) {
        Configuration cur = start.copy(history);
        List<Configuration> pool = new ArrayList<>();
        Set<Long> seen = new HashSet<>();
        
        // 簡単なケースなら 25000回、そうでなければ 5000回
        int iters = isEasyCase ? 25000 : 5000;
        double tStart = 2.0, tEnd = 0.5;

        for (int i = 0; i < iters; i++) {
            double temp = tStart + (tEnd - tStart) * (i / (double)iters);
            double oldL = cur.logLikelihood;
            
            int oilId = rand.nextInt(M);
            Point oldTL = cur.topLefts[oilId];
            Point nextTL;
            double r = rand.nextDouble();

            if (r < 0.3) { // 1マス移動
                int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};
                int d = rand.nextInt(4);
                nextTL = new Point(oldTL.r + dr[d], oldTL.c + dc[d]);
                if (!isValid(oilId, nextTL)) nextTL = oldTL;
            } else if (r < 0.4) { // ランダム移動
                nextTL = getRandomValidPoint(oilId);
            } else { // 油田入れ替え
                int oilId2 = rand.nextInt(M);
                Point oldTL2 = cur.topLefts[oilId2];
                cur.moveOil(oilId, oldTL2, history);
                cur.moveOil(oilId2, oldTL, history);
                nextTL = cur.topLefts[oilId]; 
            }

            if (r < 0.4) cur.moveOil(oilId, nextTL, history);
            double newL = cur.logLikelihood;

            if (newL > oldL || rand.nextDouble() < Math.exp((newL - oldL) / temp)) {
                if (seen.add(cur.hash)) pool.add(cur.copy(history));
            } else {
                cur.moveOil(oilId, oldTL, history);
            }
        }
        Collections.sort(pool);
        // 時間経過に応じて保持するプールサイズを調整
        int limit = isEasyCase ? 300 : 100; 
        return pool.subList(0, Math.min(pool.size(), limit));
    }

    // 山登りによる相互情報量最大のクエリ探索
    static Set<Point> findBestQuery(List<Configuration> pool) {
        Set<Point> bestQ = new HashSet<>();
        bestQ.add(new Point(rand.nextInt(N), rand.nextInt(N)));
        double bestEval = evalMI(bestQ, pool);
        
        // 簡単なケースなら山登りの試行回数も増やす
        int miIters = isEasyCase ? 5 : 2;
        for (int i = 0; i < miIters; i++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    Point p = new Point(r, c);
                    if (bestQ.contains(p)) {
                        bestQ.remove(p);
                        double e = evalMI(bestQ, pool);
                        if (e > bestEval) bestEval = e; else bestQ.add(p);
                    } else {
                        bestQ.add(p);
                        double e = evalMI(bestQ, pool);
                        if (e > bestEval) bestEval = e; else bestQ.remove(p);
                    }
                }
            }
        }
        return bestQ;
    }

    static double evalMI(Set<Point> q, List<Configuration> pool) {
        if (q.isEmpty()) return -1e18;
        int k = q.size();
        double cost = 1.0 / Math.sqrt(k);
        int[] sums = new int[pool.size()];
        for (int i = 0; i < pool.size(); i++)
            for (Point p : q) sums[i] += pool.get(i).amount[p.r][p.c];

        double mi = 0;
        for (int r = 0; r <= M * k; r++) {
            double pR = 0;
            for (int i = 0; i < pool.size(); i++) pR += calcProbability(k, sums[i], r) * pool.get(i).prob;
            if (pR < 1e-9) continue;
            for (int i = 0; i < pool.size(); i++) {
                double pRgX = calcProbability(k, sums[i], r);
                if (pRgX > 1e-9) mi += pRgX * pool.get(i).prob * Math.log(pRgX / pR);
            }
        }
        return mi / cost; // I(X;R)/cost を最大化
    }

    // 以下、確率計算・ユーティリティ (erf, cdf, calcProbability, etc.) は 00_complete と同様
    static double calcProbability(int k, int s, int r) {
        double mu = (k - s) * EPS + s * (1 - EPS);
        double sigma = Math.sqrt(k * EPS * (1 - EPS));
        if (sigma < 1e-9) return (r == Math.round(mu)) ? 1.0 : 0.0;
        return (r == 0) ? cdf(0.5, mu, sigma) : cdf(r + 0.5, mu, sigma) - cdf(r - 0.5, mu, sigma);
    }

    static double cdf(double x, double mu, double sigma) {
        return 0.5 * (1.0 + erf((x - mu) / (Math.sqrt(2) * sigma)));
    }

    static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));
        double ans = 1 - t * Math.exp(-z * z - 1.26551223 + t * (1.00002368 + t * (0.37409196 + t * (0.09678418 + 
                t * (-0.18628806 + t * (0.27886807 + t * (-1.13520398 + t * (1.48851587 + t * (-0.82215223 + t * 0.17087277)))))))));
        return (z >= 0) ? ans : -ans;
    }

    static boolean isValid(int id, Point p) {
        int maxH = 0, maxW = 0;
        for (Point sp : oilShapes.get(id)) { maxH = Math.max(maxH, sp.r); maxW = Math.max(maxW, sp.c); }
        return p.r >= 0 && p.c >= 0 && p.r <= N - 1 - maxH && p.c <= N - 1 - maxW;
    }

    static Point getRandomValidPoint(int id) {
        int maxH = 0, maxW = 0;
        for (Point p : oilShapes.get(id)) { maxH = Math.max(maxH, p.r); maxW = Math.max(maxW, p.c); }
        return new Point(rand.nextInt(N - maxH), rand.nextInt(N - maxW));
    }

    static Configuration generateInitialConfig(List<QueryRecord> history) {
        Point[] tls = new Point[M];
        for (int i = 0; i < M; i++) tls[i] = getRandomValidPoint(i);
        return new Configuration(tls, history);
    }
}
