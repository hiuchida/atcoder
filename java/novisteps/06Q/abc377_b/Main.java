import java.util.*;
public class Main {
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[9][9];
		Set<Point> set = new TreeSet<>();
		for (int y = 1; y <= 8; y++) {
			String s = sc.nextLine();
			for (int x = 1; x <= 8; x++) {
				if (s.charAt(x - 1) == '#') {
					map[y][x] = 1;
					set.add(new Point(y, x));
				}
			}
		}
//		printmap();
		for (Point p : set) {
			for (int y = 1; y <= 8; y++) {
				if (map[y][p.x] == 0) {
					map[y][p.x] = 2;
				}
			}
			for (int x = 1; x <= 8; x++) {
				if (map[p.y][x] == 0) {
					map[p.y][x] = 2;
				}
			}
		}
//		printmap();
		int ans = 0;
		for (int y = 1; y <= 8; y++) {
			for (int x = 1; x <= 8; x++) {
				if (map[y][x] == 0) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
	static void printmap() {
		for (int y = 1; y < 9; y++) {
			for (int x = 1; x < 9; x++) {
				System.out.print(map[y][x] > 0 ? '#':'.');
			}
			System.out.println();
		}
		System.out.println();
	}

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
		}
	}
}
/*
...#....
#.......
.......#
....#...
.#......
........
........
..#.....

........
........
........
........
........
........
........
........

.#......
..#..#..
....#...
........
..#....#
........
...#....
....#...
*/
