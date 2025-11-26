import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		sc.nextLine();
		Maze mz;
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap(sc);
//		mz.printmap();
		for (int y=1; y<=h; y++) {
			for (int x=1; x<=w; x++) {
				int v=mz.map[y][x];
				if (v>0) {
					for (int dy=-v; dy<=v; dy++) {
						for (int dx=-v; dx<=v; dx++) {
							if (calcL1(0, 0, dx, dy)<=v) {
								int ny=y+dy;
								int nx=x+dx;
								if (mz.checkrange(ny, nx)) {
									if (mz.map[ny][nx]<0) {
										mz.map[ny][nx]=0;
									}
								}
							}
						}
					}
				}
			}
		}
		for (int y=1; y<=h; y++) {
			for (int x=1; x<=w; x++) {
				if (mz.map[y][x]<0) System.out.print('#');
				else System.out.print('.');
			}
			System.out.println();
		}
	}
	static int calcL1(int x1, int y1, int x2, int y2) { //x1,y1からx2,y2までのマンハッタン距離
		int dx=x1-x2;
		int dy=y1-y2;
		return Math.abs(dx)+Math.abs(dy);
	}
	static class Maze { //Maze250408
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
					} else if (ch >= '1' && ch <= '9') {
						map[y][x] = ch-'0';
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
		boolean checkrange(int y, int x) {
			return 1<=y && y<=h && 1<=x && x<=w;
		}
		boolean check(int y, int x, int s) {
			if (checkrange(y, x)) {
				if (map[y][x]<0) return false;
				if (map[y][x]==0) return true;
				if (map[y][x]<=s) return false;
				return true;
			}
			return false;
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
4 4
.1.#
###.
.#2.
#.##

2 5
..#.#
###.#

2 3
11#
###

4 6
#.#3#.
###.#.
##.###
#1..#.
*/
