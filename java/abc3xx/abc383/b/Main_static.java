import java.util.*;
public class Main {
	static int h;
	static int w;
	static int d;
	static boolean[][] map;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		h = sc.nextInt();
		w = sc.nextInt();
		d = sc.nextInt();
		sc.nextLine();
		map = new boolean[h + 2][w + 2];
		for (int y = 0; y < h + 2; y++) {
			for (int x = 0; x < w + 2; x++) {
				map[y][x] = true;
			}
		}
		for (int y = 1; y <= h; y++) {
			String s = sc.nextLine();
			for (int x = 1; x <= w; x++) {
				if (s.charAt(x - 1) == '.') {
					map[y][x] = false;
				}
			}
		}
//		for (int y = 0; y < h + 2; y++) {
//			for (int x = 0; x < w + 2; x++) {
//				System.out.print(map[y][x] ? '#':'.');
//			}
//			System.out.println();
//		}
		int ans = 0;
		for (int y = 1; y <= h; y++) {
			for (int x = 1; x <= w; x++) {
				if (map[y][x]) continue;
				Set<Integer> set = new TreeSet<>();
				fill(set, y, x);
//				System.out.println(y + " " + x + " " + set);
				for (int u = 1; u <= h; u++) {
					for (int v = 1; v <= w; v++) {
						if (y == u && x == v) continue;
						if (map[u][v]) continue;
						Set<Integer> set2 = new TreeSet<>(set);
						fill(set2, u, v);
						ans = Math.max(ans, set2.size());
					}
				}
			}
		}
		System.out.println(ans);
	}

	static void fill(Set<Integer> set, int y, int x) {
		for (int dy = -d; dy <= d; dy++) {
			int y1 = y + dy;
			if (y1 < 1 || y1 > h) continue;
			for (int dx = -d; dx <= d; dx++) {
				if (Math.abs(dy) + Math.abs(dx) > d) continue;
				int x1 = x + dx;
				if (x1 < 1 || x1 > w) continue;
				if (!map[y1][x1]) {
					set.add(y1 * 1000 + x1);
				}
			}
		}
	}
}
