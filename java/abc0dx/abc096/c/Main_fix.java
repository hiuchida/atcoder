import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h=sc.nextInt();
		int w=sc.nextInt();
		sc.nextLine();
		Maze mz;
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap(sc);
//		mz.printmap();
		for (int y=1; y<=h; y++) {
			for (int x=1; x<=w; x++) {
				if (mz.map[y][x]<0) {
					boolean bok=false;
					for (int d=0; d<DY.length; d++) {
						if (mz.map[y+DY[d]][x+DX[d]]<0) bok=true;
					}
					if (!bok) {
						System.out.println("No");
						System.exit(0);
					}
				}
			}
		}
		System.out.println("Yes");
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
					map[y][x] = 0;
				}
			}
		}
		void loadmap(Scanner sc) {
			for (int y = 1; y <= h; y++) {
				String s = sc.nextLine();
				for (int x = 1; x <= w; x++) {
					char ch = s.charAt(x - 1);
					if (ch == '#') {
						map[y][x] = -1;
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
3 3
.#.
###
.#.

5 5
#.#.#
.#.#.
#.#.#
.#.#.
#.#.#

11 11
...#####...
.##.....##.
#..##.##..#
#..##.##..#
#.........#
#...###...#
.#########.
.#.#.#.#.#.
##.#.#.#.##
..##.#.##..
.##..#..##.
*/
