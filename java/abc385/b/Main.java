import java.util.*;
public class Main {
	static int[][] map;

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int y = sc.nextInt();
		int x = sc.nextInt();
		map = new int[h+2][w+2];
		for (int p=0; p<h+1; p++) {
			for (int q=0; q<w+1; q++) {
				map[p][q] = -1;
			}
		}
		for (int p=1; p<=h; p++) {
			String s = sc.next();
			for (int q=1; q<=w; q++) {
				char ch = s.charAt(q-1);
				if (ch == '.')
					map[p][q] = 0;
				else if (ch == '@')
					map[p][q] = 1;
			}
		}
		int ans = 0;
		if (map[y][x] == 1) {
			ans++;
			map[y][x] = 0;
		}
		String cmd = sc.next();
		for (int i=0; i<cmd.length(); i++) {
			char ch = cmd.charAt(i);
			switch (ch) {
			case 'L':
				if (map[y][x-1] == 1) {
					ans++;
					map[y][x-1] = 0;
				}
				if (map[y][x-1] == 0) {
					x--;
				}
				break;
			case 'R':
				if (map[y][x+1] == 1) {
					ans++;
					map[y][x+1] = 0;
				}
				if (map[y][x+1] == 0) {
					x++;
				}
				break;
			case 'U':
				if (map[y-1][x] == 1) {
					ans++;
					map[y-1][x] = 0;
				}
				if (map[y-1][x] == 0) {
					y--;
				}
				break;
			case 'D':
				if (map[y+1][x] == 1) {
					ans++;
					map[y+1][x] = 0;
				}
				if (map[y+1][x] == 0) {
					y++;
				}
				break;
			}
		}
		System.out.println(y + " " + x + " " + ans);
	}
}
