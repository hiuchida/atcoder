import java.util.*;

public class Main {
    static int N, M;
    static double EPS;
    static List<List<Point>> oilShapes = new ArrayList<>();
    
    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return r == p.r && c == p.c;
        }
        @Override
        public int hashCode() { return r * 31 + c; }
    }

    static class Configuration {
        Point[] topLefts;
        int[][] amount;
        double logLikelihood = 0;
        double prob = 0; // 事後確率
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

        List<Configuration> pool = generateAllConfigurations();
        Random rand = new Random();

        while (true) {
            // 1. 事後確率の計算（正規化）
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
                    conf.prob = Math.exp(conf.logLikelihood - maxLogL) / sumExp;
                    if (conf.prob > maxProb) {
                        maxProb = conf.prob;
                        bestIdx = i;
                    }
                } else {
                    conf.prob = 0;
                }
            }

            // 2. 確信度が閾値を超えたら回答
            if (bestIdx != -1 && maxProb > 0.8) {
                Configuration best = pool.get(bestIdx);
                System.out.print("a " + M);
                for (Point p : best.topLefts) System.out.print(" " + p.r + " " + p.c);
                System.out.println();
                System.out.flush();
                if (sc.nextInt() == 1) break;
                else {
                    best.isPossible = false;
                    best.logLikelihood = -1e18;
                    continue;
                }
            }

            // 3. 山登り法による相互情報量の最大化クエリの探索
            Set<Point> querySet = findBestQuery(pool);
            
            // クエリの実行
            int k = querySet.size();
            System.out.print("q " + k);
            for (Point p : querySet) System.out.print(" " + p.r + " " + p.c);
            System.out.println();
            System.out.flush();
            int result = sc.nextInt();

            // 4. 対数尤度の更新
            for (Configuration conf : pool) {
                if (!conf.isPossible) continue;
                int s = 0;
                for (Point p : querySet) s += conf.amount[p.r][p.c];
                conf.logLikelihood += Math.log(calcProbability(k, s, result));
            }
        }
    }

    // 山登り法で相互情報量 / コスト が最大のクエリを探す
    static Set<Point> findBestQuery(List<Configuration> pool) {
        Set<Point> currentQuery = new HashSet<>();
        // 初期状態：適当な1マス（例：中央付近）
        currentQuery.add(new Point(N/2, N/2));
        double currentEval = evalQuery(currentQuery, pool);

        for (int iter = 0; iter < 3; iter++) { // 3回繰り返す
            boolean improved = false;
            List<Point> allPoints = new ArrayList<>();
            for(int r=0; r<N; r++) for(int c=0; c<N; c++) allPoints.add(new Point(r, c));
            Collections.shuffle(allPoints);

            for (Point p : allPoints) {
                if (currentQuery.contains(p)) {
                    if (currentQuery.size() <= 1) continue;
                    currentQuery.remove(p);
                    double nextEval = evalQuery(currentQuery, pool);
                    if (nextEval > currentEval) {
                        currentEval = nextEval;
                        improved = true;
                    } else {
                        currentQuery.add(p);
                    }
                } else {
                    currentQuery.add(p);
                    double nextEval = evalQuery(currentQuery, pool);
                    if (nextEval > currentEval) {
                        currentEval = nextEval;
                        improved = true;
                    } else {
                        currentQuery.remove(p);
                    }
                }
            }
            if (!improved) break;
        }
        return currentQuery;
    }

    // 評価関数: 相互情報量 I(X;R) / cost
    static double evalQuery(Set<Point> query, List<Configuration> pool) {
        int k = query.size();
        double cost = 1.0 / Math.sqrt(k);
        
        // 各配置におけるこのクエリの埋蔵量合計 s を計算
        int[] s_of_conf = new int[pool.size()];
        for (int i = 0; i < pool.size(); i++) {
            if (!pool.get(i).isPossible) continue;
            for (Point p : query) s_of_conf[i] += pool.get(i).amount[p.r][p.c];
        }

        double mutualInformation = 0;
        // 占い結果 r の起こりうる範囲を走査（簡略化のため μ の前後一定範囲）
        for (int r = 0; r <= M * k; r++) {
            double probR = 0; // P(r) = Σ P(r|x)P(x)
            for (int i = 0; i < pool.size(); i++) {
                if (pool.get(i).isPossible) {
                    probR += calcProbability(k, s_of_conf[i], r) * pool.get(i).prob;
                }
            }
            if (probR < 1e-9) continue;

            for (int i = 0; i < pool.size(); i++) {
                if (pool.get(i).isPossible) {
                    double p_r_given_x = calcProbability(k, s_of_conf[i], r);
                    if (p_r_given_x > 1e-9) {
                        // MI = ΣΣ P(r|x)P(x) log2(P(r|x)/P(r))
                        mutualInformation += p_r_given_x * pool.get(i).prob * Math.log(p_r_given_x / probR) / Math.log(2);
                    }
                }
            }
        }
        return mutualInformation / cost;
    }

    static double calcProbability(int k, int s, int r) {
        double mu = (k - s) * EPS + s * (1 - EPS);
        double sigma = Math.sqrt(k * EPS * (1 - EPS));
        if (sigma < 1e-9) return (r == Math.round(mu)) ? 1.0 : 0.0;
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
