import java.util.*;
public class Main {
	static int y;
	static int x;
	static Maze mz;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		y = sc.nextInt();
		x = sc.nextInt();
		sc.nextLine();
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap(sc);
//		mz.printmap();
		int ans = 0;
		ans += move(0, 0);
		String cmd = sc.next();
		for (int i=0; i<cmd.length(); i++) {
			char ch = cmd.charAt(i);
			switch (ch) {
			case 'L':
				ans += move(0, -1);
				break;
			case 'R':
				ans += move(0, 1);
				break;
			case 'U':
				ans += move(-1, 0);
				break;
			case 'D':
				ans += move(1, 0);
				break;
			}
		}
//		mz.printmap();
		System.out.println(y + " " + x + " " + ans);
	}
	static int move(int dy, int dx) {
		int ans = 0;
		if (mz.map[y+dy][x+dx] == 1) {
			ans++;
			mz.map[y+dy][x+dx] = 0;
		}
		if (mz.map[y+dy][x+dx] == 0) {
			y += dy;
			x += dx;
		}
		return ans;
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
					if (ch == '.')
						map[y][x] = 0;
					else if (ch == '@')
						map[y][x] = 1;
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
5 5 3 4
#####
#...#
#.@.#
#..@#
#####
LLLDRUU

6 13 4 6
#############
#@@@@@@@@@@@#
#@@@@@@@@@@@#
#@@@@.@@@@@@#
#@@@@@@@@@@@#
#############
UURUURLRLUUDDURDURRR

12 35 7 10
###################################
#.................................#
#..........@......................#
#......@................@.........#
#.............##............@.....#
#...##........##....##............#
#...##........##....##.......##...#
#....##......##......##....##.....#
#....##......##......##..##.......#
#.....#######.........###.........#
#.................................#
###################################
LRURRRUUDDULUDUUDLRLRDRRLULRRUDLDRU
*/
