import java.util.*;

public class Main {
    static int N, M;
    static double EPS;
    static List<List<Point>> oilShapes = new ArrayList<>();
    
    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }

    static class Configuration {
        Point[] topLefts;
        int[][] amount;
        double logLikelihood = 0;
        boolean isPossible = true;

        Configuration(Point[] tls) {
            this.topLefts = tls;
            this.amount = new int[N][N];
            for (int i = 0; i < M; i++) {
                for (Point p : oilShapes.get(i)) {
                    amount[tls[i].r + p.r][tls[i].c + p.c]++;
                }
            }
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
            for (int j = 0; j < d; j++) {
                shape.add(new Point(sc.nextInt(), sc.nextInt()));
            }
            oilShapes.add(shape);
        }

        // 全ての配置候補（プール）を生成
        List<Configuration> pool = generateAllConfigurations();
        Random rand = new Random();

        while (true) {
            // 1. 対数尤度を事後確率に正規化し、最も確実な候補を探す
            double maxLogL = Double.NEGATIVE_INFINITY;
            for (Configuration conf : pool) {
                if (conf.isPossible) maxLogL = Math.max(maxLogL, conf.logLikelihood);
            }

            double sumExp = 0;
            for (Configuration conf : pool) {
                if (conf.isPossible) sumExp += Math.exp(conf.logLikelihood - maxLogL);
            }

            int bestIdx = -1;
            double maxProb = 0;
            for (int i = 0; i < pool.size(); i++) {
                Configuration conf = pool.get(i);
                if (conf.isPossible) {
                    double prob = Math.exp(conf.logLikelihood - maxLogL) / sumExp;
                    if (prob > maxProb) {
                        maxProb = prob;
                        bestIdx = i;
                    }
                }
            }

            // 2. 自信（確率）が0.8を超えたら回答
            if (bestIdx != -1 && maxProb > 0.8) {
                Configuration best = pool.get(bestIdx);
                System.out.print("a " + M);
                for (Point p : best.topLefts) System.out.print(" " + p.r + " " + p.c);
                System.out.println();
                System.out.flush();
                if (sc.nextInt() == 1) break; // 正解
                else {
                    best.isPossible = false; // 外れた配置を除外
                    continue;
                }
            }

            // 3. ランダムな占い (各マスを0.5の確率で選択)
            List<Point> queryCells = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (rand.nextBoolean()) queryCells.add(new Point(r, c));
                }
            }
            int k = queryCells.size();
            if (k == 0) continue;

            System.out.print("q " + k);
            for (Point p : queryCells) System.out.print(" " + p.r + " " + p.c);
            System.out.println();
            System.out.flush();
            int result = sc.nextInt();

            // 4. クエリ結果に基づき対数尤度を更新
            // 各配置候補における埋蔵量合計Sごとに、占い結果が得られる対数確率を事前計算
            int maxPossibleS = M * (N * N); // 理論上の最大埋蔵量
            double[] ln_pr_if_s = new double[maxPossibleS + 1];
            for (int s = 0; s <= maxPossibleS; s++) {
                ln_pr_if_s[s] = Math.log(calcProbability(k, s, result));
            }

            for (Configuration conf : pool) {
                if (!conf.isPossible) continue;
                int currentS = 0;
                for (Point p : queryCells) currentS += conf.amount[p.r][p.c];
                conf.logLikelihood += ln_pr_if_s[currentS];
            }
        }
    }

    // 誤差関数を用いた確率計算
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

    static List<Configuration> generateAllConfigurations() {
        List<Configuration> res = new ArrayList<>();
        generateRecursive(0, new Point[M], res);
        return res;
    }

    static void generateRecursive(int oilIdx, Point[] currentTLs, List<Configuration> res) {
        if (oilIdx == M) {
            res.add(new Configuration(currentTLs.clone()));
            return;
        }
        int maxH = 0, maxW = 0;
        for (Point p : oilShapes.get(oilIdx)) {
            maxH = Math.max(maxH, p.r);
            maxW = Math.max(maxW, p.c);
        }
        for (int r = 0; r <= N - 1 - maxH; r++) {
            for (int c = 0; c <= N - 1 - maxW; c++) {
                currentTLs[oilIdx] = new Point(r, c);
                generateRecursive(oilIdx + 1, currentTLs, res);
            }
        }
    }
}
