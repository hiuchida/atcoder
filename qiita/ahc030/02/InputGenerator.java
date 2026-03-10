import java.util.*;
import java.io.*;

public class InputGenerator {
    static class Point {
        int i, j;
        Point(int i, int j) { this.i = i; this.j = j; }
    }

    static class Input {
        int n, m;
        double eps;
        List<List<Point>> ts = new ArrayList<>(); // 油田の形状（(0,0)基準）
        List<Point> ps = new ArrayList<>();       // 各油田の左上配置座標
        int[][] ans;                               // 真の埋蔵量マップ
        double[] es;                               // 誤差用標準正規乱数

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%d %d %.2f\n", n, m, eps));
            for (List<Point> shape : ts) {
                sb.append(shape.size());
                for (Point p : shape) sb.append(String.format(" %d %d", p.i, p.j));
                sb.append("\n");
            }
            // 以下の情報はローカルテスタ用の追加情報
            for (Point p : ps) sb.append(String.format("%d %d\n", p.i, p.j));
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) sb.append(ans[i][j]).append(j == n - 1 ? "" : " ");
                sb.append("\n");
            }
            for (double e : es) sb.append(String.format("%.10f\n", e));
            return sb.toString();
        }
    }

    public static Input gen(long seed) {
        Random rng = new Random(seed);
        Input input = new Input();

        // 1. 基本パラメータの決定
        input.n = 10 + rng.nextInt(11); // 10..=20
        input.m = 2 + rng.nextInt(Math.max(1, (input.n * input.n / 20) - 1));
        input.eps = (1 + rng.nextInt(20)) / 100.0; // 0.01..=0.20

        // 2. 油田形状の生成パラメータ
        int minAvg = input.n * input.n / 5;
        int maxAvg = input.n * input.n / 2;
        int avg = Math.max(4, (minAvg + rng.nextInt(maxAvg - minAvg + 1)) / input.m);
        int delta = rng.nextInt(avg - 3); // 0..=avg-4

        int[][] DIJ = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // 3. 各油田の形状生成
        for (int k = 0; k < input.m; k++) {
            int size = (avg - delta) + rng.nextInt(2 * delta + 1);
            List<Point> list = new ArrayList<>();
            boolean[][] used = new boolean[input.n][input.n];
            List<Point> adj = new ArrayList<>();

            // 中心から開始
            Point start = new Point(input.n / 2, input.n / 2);
            list.add(start);
            used[start.i][start.j] = true;

            for (int[] d : DIJ) {
                int ni = start.i + d[0], nj = start.j + d[1];
                if (ni >= 0 && ni < input.n && nj >= 0 && nj < input.n) {
                    adj.add(new Point(ni, nj));
                    used[ni][nj] = true;
                }
            }

            while (list.size() < size && !adj.isEmpty()) {
                int p = rng.nextInt(adj.size());
                Point picked = adj.remove(p);
                list.add(picked);

                for (int[] d : DIJ) {
                    int ni = picked.i + d[0], nj = picked.j + d[0];
                    if (ni >= 0 && ni < input.n && nj >= 0 && nj < input.n && !used[ni][nj]) {
                        adj.add(new Point(ni, nj));
                        used[ni][nj] = true;
                    }
                }
            }

            // (0,0)基準に正規化
            int minI = 100, minJ = 100;
            for (Point p : list) {
                minI = Math.min(minI, p.i);
                minJ = Math.min(minJ, p.j);
            }
            for (Point p : list) {
                p.i -= minI;
                p.j -= minJ;
            }
            // 座標順にソート
            list.sort((p1, p2) -> p1.i != p2.i ? p1.i - p2.i : p1.j - p2.j);
            input.ts.add(list);
        }

        // 4. 油田の配置
        input.ans = new int[input.n][input.n];
        for (int k = 0; k < input.m; k++) {
            List<Point> shape = input.ts.get(k);
            int maxI = 0, maxJ = 0;
            for (Point p : shape) {
                maxI = Math.max(maxI, p.i);
                maxJ = Math.max(maxJ, p.j);
            }
            int di = rng.nextInt(input.n - maxI);
            int dj = rng.nextInt(input.n - maxJ);
            input.ps.add(new Point(di, dj));

            for (Point p : shape) {
                input.ans[p.i + di][p.j + dj]++;
            }
        }

        // 5. 誤差用乱数の生成
        input.es = new double[input.n * input.n * 2];
        for (int i = 0; i < input.es.length; i++) {
            input.es[i] = rng.nextGaussian(); // 標準正規分布
        }

        return input;
    }

    public static void main(String[] args) throws IOException {
        // seeds.txtを読み込んでinディレクトリに保存する(gen.rs相当)
        String seedsFile = args.length > 0 ? args[0] : "seeds.txt";
        File dir = new File("in");
        if (!dir.exists()) dir.mkdir();

        try (Scanner sc = new Scanner(new File(seedsFile))) {
            int id = 0;
            while (sc.hasNextLong()) {
                long seed = sc.nextLong();
                Input input = gen(seed);
                String fileName = String.format("in/%04d.txt", id++);
                try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
                    pw.print(input.toString());
                }
            }
        }
    }
}
