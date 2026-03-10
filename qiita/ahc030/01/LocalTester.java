import java.util.*;
import java.io.*;

public class LocalTester {
    static class Point implements Comparable<Point> {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
        @Override
        public int compareTo(Point o) {
            return this.r != o.r ? Integer.compare(this.r, o.r) : Integer.compare(this.c, o.c);
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            return r == p.r && c == p.c;
        }
        @Override
        public int hashCode() { return Objects.hash(r, c); }
    }

    static class Input {
        int n, m;
        double eps;
        List<List<Point>> ts = new ArrayList<>(); // 各油田の形状
        List<Point> ps = new ArrayList<>();       // 各油田の配置座標(非公開情報)
        int[][] ans;                               // 真の埋蔵量マップ(非公開情報)
        double[] es;                               // 占い用誤差乱数(非公開情報)
        int totalReservesCount;                    // 埋蔵量>0のマス数

        static Input parse(Scanner sc) {
            Input input = new Input();
            input.n = sc.nextInt();
            input.m = sc.nextInt();
            input.eps = sc.nextDouble();
            for (int i = 0; i < input.m; i++) {
                int d = sc.nextInt();
                List<Point> shape = new ArrayList<>();
                for (int j = 0; j < d; j++) shape.add(new Point(sc.nextInt(), sc.nextInt()));
                input.ts.add(shape);
            }
            // テスタ用隠し情報
            for (int i = 0; i < input.m; i++) input.ps.add(new Point(sc.nextInt(), sc.nextInt()));
            input.ans = new int[input.n][input.n];
            for (int i = 0; i < input.n; i++) {
                for (int j = 0; j < input.n; j++) {
                    input.ans[i][j] = sc.nextInt();
                    if (input.ans[i][j] > 0) input.totalReservesCount++;
                }
            }
            input.es = new double[input.n * input.n * 2];
            for (int i = 0; i < input.es.length; i++) input.es[i] = sc.nextDouble();
            return input;
        }
    }

    static class Sim {
        Input input;
        double cost = 0;
        int queryCount = 0;
        boolean finished = false;

        Sim(Input input) { this.input = input; }

        // 掘る操作 (Mining)
        int mining(int r, int c) {
            cost += 1.0;
            return input.ans[r][c];
        }

        // 占う操作 (Survey)
        int survey(List<Point> s) {
            cost += 1.0 / Math.sqrt(s.size());
            int vS = 0;
            for (Point p : s) vS += input.ans[p.r][p.c];
            double k = s.size();
            double mu = (k - vS) * input.eps + vS * (1.0 - input.eps);
            double sigma = Math.sqrt(k * input.eps * (1.0 - input.eps));
            int res = (int) Math.max(0, Math.round(mu + input.es[queryCount] * sigma));
            queryCount++;
            return res;
        }

        // 推測する操作 (Ans)
        int answer(List<Point> s) {
            Collections.sort(s);
            if (s.size() == input.totalReservesCount) {
                boolean match = true;
                for (Point p : s) {
                    if (input.ans[p.r][p.c] == 0) { match = false; break; }
                }
                if (match) {
                    finished = true;
                    return 1;
                }
            }
            cost += 1.0;
            return 0;
        }

        long getAbsoluteScore() {
            double c = finished ? cost : 1000.0;
            return Math.round(1e6 * Math.max(c, 1.0 / input.n));
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: java LocalTester <input_file> <command...>");
            return;
        }

        // 1. 入力の読み込み
        Input input;
        try (Scanner sc = new Scanner(new File(args))) {
            input = Input.parse(sc);
        }

        // 2. 解答プログラムの起動
        ProcessBuilder pb = new ProcessBuilder(Arrays.copyOfRange(args, 1, args.length));
        Process p = pb.start();

        try (
            PrintWriter out = new PrintWriter(p.getOutputStream(), true);
            Scanner in = new Scanner(p.getInputStream());
            BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()))
        ) {
            // エラー出力を別スレッドで流す
            new Thread(() -> {
                try {
                    String line;
                    while ((line = err.readLine()) != null) System.err.println(line);
                } catch (IOException e) {}
            }).start();

            // 3. 事前情報の提供
            out.printf("%d %d %.2f\n", input.n, input.m, input.eps);
            for (List<Point> shape : input.ts) {
                out.print(shape.size());
                for (Point pt : shape) out.printf(" %d %d", pt.r, pt.c);
                out.println();
            }

            // 4. クエリループ
            Sim sim = new Sim(input);
            for (int q = 0; q < 2 * input.n * input.n; q++) {
                if (!in.hasNext()) break;
                String type = in.next();
                int k = in.nextInt();
                List<Point> coords = new ArrayList<>();
                for (int i = 0; i < k; i++) coords.add(new Point(in.nextInt(), in.nextInt()));

                int response;
                if (type.equals("q")) {
                    if (k == 1) response = sim.mining(coords.get(0).r, coords.get(0).c);
                    else response = sim.survey(coords);
                    out.println(response);
                } else if (type.equals("a")) {
                    response = sim.answer(coords);
                    out.println(response);
                    if (response == 1) break;
                }
            }

            p.waitFor();
            System.err.println("Score = " + sim.getAbsoluteScore());

        } finally {
            p.destroy();
        }
    }
}
