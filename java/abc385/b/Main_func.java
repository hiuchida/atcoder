import java.util.*;
public class Main {
	static int y;
	static int x;
	static int[][] map;

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		y = sc.nextInt();
		x = sc.nextInt();
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
		System.out.println(y + " " + x + " " + ans);
	}
	static int move(int dy, int dx) {
		int ans = 0;
		if (map[y+dy][x+dx] == 1) {
			ans++;
			map[y+dy][x+dx] = 0;
		}
		if (map[y+dy][x+dx] == 0) {
			y += dy;
			x += dx;
		}
		return ans;
	}
}
