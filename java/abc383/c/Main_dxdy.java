import java.util.*;
public class Main {
	static int[] dy = { -1,1, 0,0 }; //UDLR
	static int[] dx = {  0,0,-1,1 }; //UDLR
	static int h;
	static int w;
	static int d;
	static int[][] map;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		h = sc.nextInt();
		w = sc.nextInt();
		d = sc.nextInt();
		sc.nextLine();
		map = new int[h + 2][w + 2];
		Deque<Info> que = new ArrayDeque<>();
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
					map[y][x] = 0;
					que.offer(new Info(y, x, d));
				}
			}
		}
		while (que.size() > 0) {
			Info info = que.poll();
			bfs(que, info);
		}
		int ans = 0;
		for (int y = 0; y < h + 2; y++) {
			for (int x = 0; x < w + 2; x++) {
				if (map[y][x] > 0) ans++;
			}
		}
		System.out.println(ans);
	}

	static void bfs(Deque<Info> que, Info info) {
		int y = info.y;
		int x = info.x;
		int d = info.d;
		if (map[y][x] == 0) {
//			System.out.println(info);
			map[y][x] = 1;
		}
		if (d == 0) return;
		d--;
		for (int i=0; i<dy.length; i++) {
			if (map[y+dy[i]][x+dx[i]] == 0) que.offer(new Info(y+dy[i], x+dx[i], d));
		}
	}

	static class Info {
		int y;
		int x;
		int d;
		
		Info(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
		
		public String toString() {
			return "" + y + " " + x + " " + d;
		}
	}
}
