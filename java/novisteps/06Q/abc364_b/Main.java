import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int sy = sc.nextInt();
		int sx = sc.nextInt();
		sc.nextLine();
		Maze mz;
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap(sc);
//		mz.printmap();
		String s = sc.nextLine();
		for (int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			int dx, dy;
			switch (ch) {
			case 'U':
				dy=-1;
				dx=0;
				if (mz.map[sy+dy][sx+dx] == 0) {
					sy+=dy;
					sx+=dx;
				}
				break;
			case 'D':
				dy=1;
				dx=0;
				if (mz.map[sy+dy][sx+dx] == 0) {
					sy+=dy;
					sx+=dx;
				}
				break;
			case 'R':
				dy=0;
				dx=1;
				if (mz.map[sy+dy][sx+dx] == 0) {
					sy+=dy;
					sx+=dx;
				}
				break;
			case 'L':
				dy=0;
				dx=-1;
				if (mz.map[sy+dy][sx+dx] == 0) {
					sy+=dy;
					sx+=dx;
				}
				break;
			}
		}
		System.out.println(sy + " " + sx);
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
/*
2 3
2 1
.#.
...
ULDRU

4 4
4 2
....
.#..
...#
....
DUUUURULRD

6 6
1 1
.#####
######
######
######
######
######
RURLDLULLRULRDL
*/
