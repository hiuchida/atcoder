import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	static int h;
	static int w;
	static int n;
	static int[] ans;
	static Maze mz;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		h = sc.nextInt();
		w = sc.nextInt();
		n=Math.min(h, w);
		ans=new int[n];
		sc.nextLine();
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap(sc);
		for (int y = 1; y <= h; y++) {
			for (int x = 1; x <= w; x++) {
				if (mz.map[y][x]<0) {
					find(y, x);
				}
			}
		}
//		mz.printmap();
		for (int i=0; i<n; i++) {
			System.out.print(ans[i]+" ");
		}
		System.out.println();
	}
	static void find(int y, int x) {
		int d;
		for (d=0; true; d++) {
			if (mz.map[y+d][x+d]>=0) break;
			mz.map[y+d][x+d]=d+1;
		}
		x+=d-1;
		for (int i=0; i<d; i++) {
			mz.map[y+i][x-i]=i+1;
		}
		d/=2;
		ans[d-1]++;
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
5 9
#.#.#...#
.#...#.#.
#.#...#..
.....#.#.
....#...#

3 3
...
...
...

3 16
#.#.....#.#..#.#
.#.......#....#.
#.#.....#.#..#.#

15 20
#.#..#.............#
.#....#....#.#....#.
#.#....#....#....#..
........#..#.#..#...
#.....#..#.....#....
.#...#....#...#..#.#
..#.#......#.#....#.
...#........#....#.#
..#.#......#.#......
.#...#....#...#.....
#.....#..#.....#....
........#.......#...
#.#....#....#.#..#..
.#....#......#....#.
#.#..#......#.#....#
*/
