import java.util.*;
import java.io.*;

public class LocalVisualizer {
    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }

    static class Input {
        int n, m;
        double eps;
        List<List<Point>> ts = new ArrayList<>();
        List<Point> ps = new ArrayList<>();
        int[][] ans;
        double[] es;

        static Input parse(String content) {
            Scanner sc = new Scanner(content);
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
            // 隠し情報
            for (int i = 0; i < input.m; i++) input.ps.add(new Point(sc.nextInt(), sc.nextInt()));
            input.ans = new int[input.n][input.n];
            for (int i = 0; i < input.n; i++) {
                for (int j = 0; j < input.n; j++) input.ans[i][j] = sc.nextInt();
            }
            return input;
        }
    }

    static class Query {
        String type; // "q" or "a"
        List<Point> ps;
        Query(String type, List<Point> ps) { this.type = type; this.ps = ps; }
    }

    static class Sim {
        List<Query> queries = new ArrayList<>();
        int[][] mined; // 何手目に掘られたか記録
        int n;

        Sim(int n) {
            this.n = n;
            mined = new int[n][n];
            for (int i = 0; i < n; i++) Arrays.fill(mined[i], 100000);
        }
    }

    // SVG生成クラス
    static class Vis {
        static String colorScale(double val) {
            val = Math.max(0.0, Math.min(1.0, val));
            double r, g, b;
            if (val < 0.5) {
                double x = val * 2.0;
                r = 30 * (1 - x) + 144 * x;
                g = 144 * (1 - x) + 255 * x;
                b = 255 * (1 - x) + 30 * x;
            } else {
                double x = val * 2.0 - 1.0;
                r = 144 * (1 - x) + 255 * x;
                g = 255 * (1 - x) + 30 * x;
                b = 30 * (1 - x) + 70 * x;
            }
            return String.format("#%02x%02x%02x", (int)Math.round(r), (int)Math.round(g), (int)Math.round(b));
        }

        static String generateSvg(Input input, Sim sim, List<String> comments, boolean showAns) {
            int D = 600 / input.n;
            int W = D * input.n;
            int H = D * input.n;
            StringBuilder svg = new StringBuilder();
            svg.append(String.format("<svg id='vis' viewBox='-5 -5 %d %d' width='%d' height='%d' xmlns='http://www.w3.org/2000/svg' style='background-color:white'>\n", W+10, H+10, W+10, H+10));
            svg.append("<style>text {text-anchor: middle; dominant-baseline: central; font-family: sans-serif;}</style>\n");

            String[][] cellColor = new String[input.n][input.n];
            for(int i=0; i<input.n; i++) Arrays.fill(cellColor[i], "white");

            // #c コメントによる色指定の処理
            for (String line : comments) {
                if (line.startsWith("c ")) {
                    String[] ss = line.substring(2).trim().split("\\s+");
                    if (ss.length == 3) {
                        try {
                            int r = Integer.parseInt(ss[0]);
                            int c = Integer.parseInt(ss[1]);
                            cellColor[r][c] = ss[2];
                        } catch (Exception e) {}
                    }
                }
            }

            // 最後のクエリを強調表示
            if (!sim.queries.isEmpty()) {
                Query last = sim.queries.get(sim.queries.size() - 1);
                String highlight = last.type.equals("q") ? "tomato" : "skyblue";
                for (Point p : last.ps) cellColor[p.r][p.c] = highlight;
            }

            // グリッドと数値を描画
            for (int i = 0; i < input.n; i++) {
                for (int j = 0; j < input.n; j++) {
                    svg.append(String.format("<rect x='%d' y='%d' width='%d' height='%d' fill='%s' stroke='black' stroke-width='1' />\n", j*D, i*D, D, D, cellColor[i][j]));
                    if (sim.mined[i][j] < 100000) {
                        svg.append(String.format("<text x='%d' y='%d' font-size='%d' fill='black'>%d</text>\n", j*D + D/2, i*D + D/2, D/3, input.ans[i][j]));
                    } else if (showAns && input.ans[i][j] > 0) {
                        svg.append(String.format("<text x='%d' y='%d' font-size='%d' fill='darkgray'>%d</text>\n", j*D + D/2, i*D + D/2, D/3, input.ans[i][j]));
                    }
                }
            }

            // 油田の境界線（緑色）を描画
            if (showAns) {
                int[][] DIJ = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
                for (int p = 0; p < input.m; p++) {
                    Point pos = input.ps.get(p);
                    boolean[][] inside = new boolean[input.n][input.n];
                    for (Point pt : input.ts.get(p)) inside[pt.r + pos.r][pt.c + pos.c] = true;

                    for (int i = 0; i < input.n; i++) {
                        for (int j = 0; j < input.n; j++) {
                            if (!inside[i][j]) continue;
                            for (int[] d : DIJ) {
                                int ni = i + d[0], nj = j + d[1];
                                if (ni < 0 || ni >= input.n || nj < 0 || nj >= input.n || !inside[ni][nj]) {
                                    int cx = j * D + D / 2, cy = i * D + D / 2;
                                    int r = D / 2 - 3;
                                    int x1 = cx + (d[1] - d[0]) * r, y1 = cy + (d[0] + d[1]) * r;
                                    int x2 = cx + (d[1] + d[0]) * r, y2 = cy + (d[0] - d[1]) * r;
                                    svg.append(String.format("<line x1='%d' y1='%d' x2='%d' y2='%d' stroke='green' stroke-width='2' />\n", x1, y1, x2, y2));
                                }
                            }
                        }
                    }
                }
            }
            svg.append("</svg>");
            return svg.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: java LocalVisualizer <in_file> <out_file>");
            return;
        }

        Input input = Input.parse(new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(args[0]))));
        String outContent = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(args[1])));
        
        Sim sim = new Sim(input.n);
        List<String> comments = new ArrayList<>();
        Scanner sc = new Scanner(outContent);
        int step = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine().trim();
            if (line.startsWith("#")) {
                comments.add(line.substring(1).trim());
                continue;
            }
            String[] parts = line.split("\\s+");
            if (parts.length < 2) continue;
            String type = parts[0];
            int k = Integer.parseInt(parts[1]);
            List<Point> ps = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                int r = Integer.parseInt(parts[2 + i*2]);
                int c = Integer.parseInt(parts[3 + i*2]);
                ps.add(new Point(r, c));
                if (type.equals("q") && k == 1) sim.mined[r][c] = step;
            }
            sim.queries.add(new Query(type, ps));
            step++;
        }

        String svg = Vis.generateSvg(input, sim, comments, true);
        String html = "<html><body>" + svg + "</body></html>";
        java.nio.file.Files.write(java.nio.file.Paths.get("vis.html"), html.getBytes());
        System.out.println("Visualizer output saved to vis.html");
    }
}
