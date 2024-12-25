import java.util.*;
public class Main {
	static class Info {
		int y;
		int x;
		Info(int y, int x) {
			this.y = y;
			this.x = x;
		}
		public String toString() {
			return "(" + y + " " + x + ")";
		}
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		long xx = sc.nextLong();
		int p = sc.nextInt();
		int q = sc.nextInt();
		long[][] map = new long[h+2][w+2];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				map[y+1][x+1] = sc.nextLong();
			}
		}
		long pw = map[p][q];
		map[p][q] = 0;
		Deque<Info> que = new ArrayDeque<>();
		if (p > 1) que.offer(new Info(p-1,q));
		if (p < h) que.offer(new Info(p+1,q));
		if (q > 1) que.offer(new Info(p,q-1));
		if (q < w) que.offer(new Info(p,q+1));
		boolean bUpdate = false;
		int cnt = 0;
		do {
			bUpdate = false;
			Deque<Info> que2 = new ArrayDeque<>();
			while (que.size() > 0) {
				Info info = que.poll();
				int y = info.y;
				int x = info.x;
				long s = map[y][x];
//				System.out.println(pw + " " + info + " " + s);
				if (s > 0 && s < (double)pw / xx) {
					pw += s;
					map[y][x] = 0;
					if (y > 1) que2.offer(new Info(y-1,x));
					if (y < h) que2.offer(new Info(y+1,x));
					if (x > 1) que2.offer(new Info(y,x-1));
					if (x < w) que2.offer(new Info(y,x+1));
					bUpdate = true;
				} else {
					que2.offer(info);
				}
			}
			que = new ArrayDeque<>(que2);
		} while (bUpdate);
		System.out.println(pw);
	}
}
