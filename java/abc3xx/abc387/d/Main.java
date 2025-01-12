import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	static void DEBUG(Object x) {
		if (DEBUG) System.out.println(x);
	}
	static void DEBUG(long x) {
		if (DEBUG) DEBUG(""+x);
    }
	static void DEBUG(int[] x) {
		if (DEBUG) DEBUG(Arrays.toString(x));
    }
	static int[] dy = { -1,1, 0,0 }; //UDLR
	static int[] dx = {  0,0,-1,1 }; //UDLR
	static int h;
	static int w;
	static int[][] map;
	static int[][] map2;
	static Point st, st2, ed;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		h = sc.nextInt();
		w = sc.nextInt();
		sc.nextLine();
		map = new int[h + 2][w + 2];
		map2 = new int[h + 2][w + 2];
		for (int y = 0; y < h + 2; y++) {
			for (int x = 0; x < w + 2; x++) {
				map[y][x] = -1;
				map2[y][x] = -1;
			}
		}
		for (int y = 1; y <= h; y++) {
			String s = sc.nextLine();
			for (int x = 1; x <= w; x++) {
				char ch = s.charAt(x - 1);
				if (ch == '.') {
					map[y][x] = 0;
					map2[y][x] = 0;
				} else if (ch == 'S') {
					map[y][x] = 1;
					map2[y][x] = 1;
					st = new Point(y, x, "UD");
					st2 = new Point(y, x, "LR");
				} else if (ch == 'G') {
					map[y][x] = 0;
					map2[y][x] = 0;
					ed = new Point(y, x, "");
				}
			}
		}
		printmap();
//		System.out.println(st);
//		System.out.println(ed);
		long ans = Integer.MAX_VALUE;
		Deque<Point> que = new ArrayDeque<>();
		Set<Point> set = new TreeSet<>();
		que.offer(st);
		while (que.size() > 0) {
			Point p = que.poll();
//			DEBUG(p);
			set.add(p);
			if (p.equals(ed)) {
				ans = Math.min(ans, map[p.y][p.x]-1);
			} else if (ans > map[p.y][p.x]-1) {
				if (p.s.equals("LR")) {
					if (map[p.y-1][p.x] == 0 || map[p.y-1][p.x] > map[p.y][p.x] + 1) {
						Point p2 = new Point(p.y-1, p.x, "UD");
						map[p2.y][p2.x] = map[p.y][p.x] + 1;
						que.offer(p2);
					}
					if (map[p.y+1][p.x] == 0 || map[p.y+1][p.x] > map[p.y][p.x] + 1) {
						Point p2 = new Point(p.y+1, p.x, "UD");
						map[p2.y][p2.x] = map[p.y][p.x] + 1;
						que.offer(p2);
					}
				} else {
					if (map[p.y][p.x-1] == 0 || map[p.y][p.x-1] > map[p.y][p.x] + 1) {
						Point p2 = new Point(p.y, p.x-1, "LR");
						map[p2.y][p2.x] = map[p.y][p.x] + 1;
						que.offer(p2);
					}
					if (map[p.y][p.x+1] == 0 || map[p.y][p.x+1] > map[p.y][p.x] + 1) {
						Point p2 = new Point(p.y, p.x+1, "LR");
						map[p2.y][p2.x] = map[p.y][p.x] + 1;
						que.offer(p2);
					}
				}
			}
		}
		printmap();
		map = map2;
		que.offer(st2);
		while (que.size() > 0) {
			Point p = que.poll();
//			DEBUG(p);
			set.add(p);
			if (p.equals(ed)) {
				ans = Math.min(ans, map[p.y][p.x]-1);
			} else if (ans > map[p.y][p.x]-1) {
				if (p.s.equals("LR")) {
					if (map[p.y-1][p.x] == 0 || map[p.y-1][p.x] > map[p.y][p.x] + 1) {
						Point p2 = new Point(p.y-1, p.x, "UD");
						map[p2.y][p2.x] = map[p.y][p.x] + 1;
						que.offer(p2);
					}
					if (map[p.y+1][p.x] == 0 || map[p.y+1][p.x] > map[p.y][p.x] + 1) {
						Point p2 = new Point(p.y+1, p.x, "UD");
						map[p2.y][p2.x] = map[p.y][p.x] + 1;
						que.offer(p2);
					}
				} else {
					if (map[p.y][p.x-1] == 0 || map[p.y][p.x-1] > map[p.y][p.x] + 1) {
						Point p2 = new Point(p.y, p.x-1, "LR");
						map[p2.y][p2.x] = map[p.y][p.x] + 1;
						que.offer(p2);
					}
					if (map[p.y][p.x+1] == 0 || map[p.y][p.x+1] > map[p.y][p.x] + 1) {
						Point p2 = new Point(p.y, p.x+1, "LR");
						map[p2.y][p2.x] = map[p.y][p.x] + 1;
						que.offer(p2);
					}
				}
			}
		}
		printmap();
		if (ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	static void printmap() {
//		for (int y = 0; y < h + 2; y++) {
//			for (int x = 0; x < w + 2; x++) {
//				System.out.print("#.123456789".charAt(map[y][x]+1));
//			}
//			System.out.println();
//		}
	}

	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		final String s;
		Point(int y, int x, String s) {
			this.y = y;
			this.x = x;
			this.s = s;
		}
		@Override
		public int compareTo(Point that) {
			int cmp = Integer.compare(this.y, that.y);
			if (cmp != 0) return cmp;
			return Integer.compare(this.x, that.x);
		}
		@Override
		public int hashCode() {
			return Objects.hash(y, x);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.y == that.y && this.x == that.x;
		}
		@Override
		public String toString() {
			return "(" + y + "," + x + ":" + s + ")";
		}
	}
}
