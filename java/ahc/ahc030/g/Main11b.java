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

        // 全ての配置候補を生成（Mが小さい場合を想定）
        List<Configuration> pool = generateAllConfigurations();
        double[] logLikelihoods = new double[pool.size()];
        boolean[] isPossible = new boolean[pool.size()];
        Arrays.fill(isPossible, true);

        Random rand = new Random();

        while (true) {
            int bestIdx = -1;
            double maxLogL = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < pool.size(); i++) {
                if (isPossible[i] && logLikelihoods[i] > maxLogL) {
                    maxLogL = logLikelihoods[i];
                    bestIdx = i;
                }
            }

            // 尤度が十分に高まったと判断したら回答（暫定的な閾値）
            if (maxLogL > -2.0) { 
                System.out.print("a " + M);
                for (Point p : pool.get(bestIdx).topLefts) {
                    System.out.print(" " + p.r + " " + p.c);
                }
                System.out.println();
                System.out.flush();
                if (sc.nextInt() == 1) break;
                else {
                    isPossible[bestIdx] = false;
                    continue;
                }
            }

            // 【修正点】0.5の確率で各マスを選択し、kマス占う
            List<Point> queryCells = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (rand.nextBoolean()) { // 0.5の確率
                        queryCells.add(new Point(r, c));
                    }
                }
            }

            int k = queryCells.size();
            if (k == 0) continue; // 1マスも選ばれなかった場合は再試行

            System.out.print("q " + k);
            for (Point p : queryCells) {
                System.out.print(" " + p.r + " " + p.c);
            }
            System.out.println();
            System.out.flush();
            int result = sc.nextInt();

            // 各配置候補の対数尤度を更新
            for (int i = 0; i < pool.size(); i++) {
                if (!isPossible[i]) continue;
                
                // 仮定した配置における、占ったマスの埋蔵量合計 s を計算
                int s = 0;
                for (Point p : queryCells) {
                    s += pool.get(i).amount[p.r][p.c];
                }
                
                // P(result | s, k) の対数を加算
                logLikelihoods[i] += Math.log(calcProbability(k, s, result));
            }
        }
    }

    static double calcProbability(int k, int s, int r) {
        // ソース の定義に基づく平均と分散
        double mu = (k - s) * EPS + s * (1 - EPS);
        double sigmaSq = k * EPS * (1 - EPS);
        double sigma = Math.sqrt(sigmaSq);

        if (r == 0) {
            return cdf(0.5, mu, sigma); // r=0のケース
        } else {
            return cdf(r + 0.5, mu, sigma) - cdf(r - 0.5, mu, sigma);
        }
    }

    static double cdf(double x, double mu, double sigma) {
        return 0.5 * (1.0 + erf((x - mu) / (Math.sqrt(2) * sigma)));
    }

    static double erf(double z) {
        // 数値計算による誤差関数の近似
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));
        double ans = 1 - t * Math.exp(-z * z - 1.26551223 +
                t * (1.00002368 + t * (0.37409196 + t * (0.09678418 + 
                t * (-0.18628806 + t * (0.27886807 + t * (-1.13520398 + 
                t * (1.48851587 + t * (-0.82215223 + t * 0.17087277)))))))));
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
