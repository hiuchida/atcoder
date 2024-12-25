import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int d = sc.nextInt();
		sc.nextLine();
		int[][] map = new int[h + 2][w + 2];
		Set<Integer> set = new TreeSet<>();
		for (int y = 0; y < h + 2; y++) {
			for (int x = 0; x < w + 2; x++) {
				map[y][x] = -1;
			}
		}
		for (int y = 1; y <= h; y++) {
			String s = sc.nextLine();
			for (int x = 1; x <= w; x++) {
				if (s.charAt(x - 1) == '.') {
					map[y][x] = 0;
				} else if (s.charAt(x - 1) == 'H') {
					map[y][x] = 1;
					set.add(y * 10000 + x);
				}
			}
		}
		for (int i : set) {
			int y = i / 10000;
			int x = i % 10000;
			dfs(map, h, w, y, x, d);
		}
		int ans = 0;
		for (int y = 0; y < h + 2; y++) {
			for (int x = 0; x < w + 2; x++) {
				if (map[y][x] == 1) ans++;
			}
		}
		System.out.println(ans);
	}

	static void dfs(int[][] map, int h, int w, int y, int x, int d) {
		if (map[y][x] == 0) {
			map[y][x] = 1;
		}
		if (d == 0) return;
		d--;
		if (map[y-1][x] >= 0) dfs(map, h, w, y-1, x, d);
		if (map[y+1][x] >= 0) dfs(map, h, w, y+1, x, d);
		if (map[y][x-1] >= 0) dfs(map, h, w, y, x-1, d);
		if (map[y][x+1] >= 0) dfs(map, h, w, y, x+1, d);
	}
}
