import java.util.*;
public class Main {
	static class Point implements Comparable<Point> {
		int y;
		int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public int compareTo(Main.Point that) {
			int cmp = Integer.compare(this.y, that.y);
			if (cmp != 0) return cmp;
			return Integer.compare(this.x, that.x);
		}
		@Override
		public String toString() {
			return "(" + y + ", " + x + ")";
//			return "Point [y=" + y + ", x=" + x + "]";
		}
	}
	static int h;
	static int w;
	static int k;
	static boolean[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		h = sc.nextInt();
		w = sc.nextInt();
		k = sc.nextInt();
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
		long ans = 0;
		for (int y = 1; y <= h; y++) {
			for (int x = 1; x <= w; x++) {
				if (map[y][x]) continue;
				ans += srch(y, x);
			}
		}
		System.out.println(ans);
	}
	static long srch(int y, int x) {
		long ans = 0;
		TreeSet<Point> set = new TreeSet<>();
		ans += dfs(y,x,k,set);
		return ans;
	}
	static long dfs(int y, int x, int k, TreeSet<Point> set) {
		long ans = 0;
		if (map[y][x]) return ans;
		Point p = new Point(y, x);
		if (set.contains(p)) return ans;
		if (k == 0) {
//			System.out.println(p + " " + set);
			return 1;
		}
		set.add(p);
		TreeSet<Point> set1 = new TreeSet<>(set);
		ans += dfs(y-1,x,k-1,set1);
		TreeSet<Point> set2 = new TreeSet<>(set);
		ans += dfs(y+1,x,k-1,set2);
		TreeSet<Point> set3 = new TreeSet<>(set);
		ans += dfs(y,x-1,k-1,set3);
		TreeSet<Point> set4 = new TreeSet<>(set);
		ans += dfs(y,x+1,k-1,set4);
		return ans;
	}
}
