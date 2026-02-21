import java.util.*;

public class Main {
    static int N, M;
    static double EPS;
    static List<List<Point>> oilShapes = new ArrayList<>();
    
    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }

    // 配置の状態を管理するクラス
    static class Configuration {
        Point[] topLefts; // 各油田の左上座標
        int[][] amount;   // グリッド上の埋蔵量

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

        // 1. 全ての配置候補（プール）を生成
        // 注意：Mが大きい場合はこの方法は計算量的に破綻します
        List<Configuration> pool = generateAllConfigurations();
        double[] logLikelihoods = new double[pool.size()];
        boolean[] isPossible = new boolean[pool.size()];
        Arrays.fill(isPossible, true);

        Random rand = new Random();

        while (true) {
            // 最も尤度が高いものを選択
            int bestIdx = -1;
            double maxLogL = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < pool.size(); i++) {
                if (isPossible[i] && logLikelihoods[i] > maxLogL) {
                    maxLogL = logLikelihoods[i];
                    bestIdx = i;
                }
            }

            // 自信がある程度高ければ回答（ここでは簡易的にループ回数や尤度差で判断）
            // サンプルコードの挙動に合わせ、一定回数占った後に回答する構成にします
            if (maxLogL > -1.0) { // 閾値は調整が必要
                System.out.print("a " + M);
                for (Point p : pool.get(bestIdx).topLefts) {
                    System.out.print(" " + p.r + " " + p.c);
                }
                System.out.println();
                if (sc.nextInt() == 1) break; // 正解なら終了
                else {
                    isPossible[bestIdx] = false; // 外れた配置は除外
                    continue;
                }
            }

            // 2. ランダムな占いを実行
            int queryR = rand.nextInt(N);
            int queryC = rand.nextInt(N);
            System.out.println("q 1 " + queryR + " " + queryC);
            int result = sc.nextInt();

            // 3. 各配置候補の対数尤度を更新
            for (int i = 0; i < pool.size(); i++) {
                if (!isPossible[i]) continue;
                int s = pool.get(i).amount[queryR][queryC];
                logLikelihoods[i] += Math.log(calcProbability(1, s, result));
            }
        }
    }

    // 正規分布の累積分布関数(CDF)を用いた確率計算
    static double calcProbability(int k, int s, int r) {
        double mu = (k - s) * EPS + s * (1 - EPS);
        double sigmaSq = k * EPS * (1 - EPS);
        double sigma = Math.sqrt(sigmaSq);

        if (r == 0) {
            return cdf(0.5, mu, sigma);
        } else {
            return cdf(r + 0.5, mu, sigma) - cdf(r - 0.5, mu, sigma);
        }
    }

    // CDFの計算（誤差関数erfを使用）
    static double cdf(double x, double mu, double sigma) {
        return 0.5 * (1.0 + erf((x - mu) / (Math.sqrt(2) * sigma)));
    }

    // Java標準にはerfがないため、近似式を実装
    static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));
        double ans = 1 - t * Math.exp(-z * z - 1.26551223 +
                t * (1.00002368 +
                t * (0.37409196 +
                t * (0.09678418 +
                t * (-0.18628806 +
                t * (0.27886807 +
                t * (-1.13520398 +
                t * (1.48851587 +
                t * (-0.82215223 +
                t * 0.17087277)))))))));
        if (z >= 0) return ans;
        else return -ans;
    }

    // 全ての配置パターンを再帰的に生成するメソッド
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
        
        // 油田がグリッドからはみ出さない範囲を計算
        int maxH = 0, maxW = 0;
        for (Point p : oilShapes.get(oilIdx)) {
            maxH = Math.max(maxH, p.r);
            maxW = Math.max(maxW, p.c);
        }

        for (int r = 0; r < N - maxH; r++) {
            for (int c = 0; c < N - maxW; c++) {
                currentTLs[oilIdx] = new Point(r, c);
                generateRecursive(oilIdx + 1, currentTLs, res);
            }
        }
    }
}
