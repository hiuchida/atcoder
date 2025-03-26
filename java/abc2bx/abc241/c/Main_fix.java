import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static int n;
	static Maze mz;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.nextLine();
		mz=new Maze(n, n);
		mz.initmap();
		mz.loadmap(sc);
		mz.printmap();
		for (int y=1; y<=n; y++) {
			for (int x=1; x<=n; x++) {
				check(y, x, 1, 0);
				check(y, x, 0, 1);
				check(y, x, 1, 1);
				check(y, x, -1, 1);
			}
		}
		System.out.println("No");
	}
	static void check(int y, int x, int dy, int dx) {
		int cnt=0;
		for (int d=0; d<=5; d++) {
			int yy=y+d*dy;
			int xx=x+d*dx;
			if (1<=yy && yy<=n && xx<=n) {
				if (mz.map[yy][xx]<0) cnt++;
			} else {
				cnt=0;
				break;
			}
		}
		if (cnt>=4) {
			System.out.println("Yes");
			System.exit(0);
		}
	}
	static class Maze {
		int h;
		int w;
		int[][] map; //map of Maze
		Maze(int h, int w) {
			this.h = h;
			this.w = w;
			this.map = new int[h + 2][w + 2];
		}
		void initmap() {
			for (int y = 0; y < h + 2; y++) {
				for (int x = 0; x < w + 2; x++) {
					map[y][x] = -1;
				}
			}
		}
		void loadmap(Scanner sc) {
			for (int y = 1; y <= h; y++) {
				String s = sc.nextLine();
				for (int x = 1; x <= w; x++) {
					char ch = s.charAt(x - 1);
					if (ch == '.') {
						map[y][x] = 0;
					} else if (ch == 'S') {
						map[y][x] = 28;
					} else if (ch == 'G') {
						map[y][x] = 16;
					}
				}
			}
		}
		void resetmap() {
			for (int y = 0; y < h + 2; y++) {
				for (int x = 0; x < w + 2; x++) {
					if (map[y][x] > 0) map[y][x] = 0;
				}
			}
		}
		void printmap() {
			final String tbl = "#.123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			if (DEBUG) {
				for (int y = 0; y < h + 2; y++) {
					for (int x = 0; x < w + 2; x++) {
						int i=map[y][x]+1;
						while (i>=tbl.length()) i-=61;
						if (i<tbl.length()) System.out.print(tbl.charAt(i));
						else System.out.print('@');
					}
					System.out.println();
				}
				System.out.println();
			}
		}
	}
}
/*
8
........
........
.#.##.#.
........
........
........
........
........

6
######
######
######
######
######
######

10
..........
#..##.....
..........
..........
....#.....
....#.....
.#...#..#.
..........
..........
..........
*/
/*
6
...#..
..#...
.#....
#.....
......
......
*/
