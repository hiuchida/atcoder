import java.util.*;
public class Main {
	static long[][] map;

	static class Info implements Comparable<Info>{
		int y;
		int x;
		long pw;
		Info(int y, int x) {
			this.y = y;
			this.x = x;
			this.pw = map[y][x];
			if (pw == 0) {
				pw = Long.MAX_VALUE;
			}
		}
		public String toString() {
			return "(" + y + " " + x + ")";
		}
		@Override
		public int compareTo(Main.Info that) {
			return -Long.compare(this.pw, that.pw);
		}
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		map = new long[h+2][w+2];
		long xx = sc.nextLong();
		int p = sc.nextInt();
		int q = sc.nextInt();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				map[y+1][x+1] = sc.nextLong();
			}
		}
		long pw = map[p][q];
		map[p][q] = 0;
		List<Info> que = new ArrayList<>();
//		Deque<Info> que = new ArrayDeque<>();
		if (p > 1) que.add(new Info(p-1,q));
		if (p < h) que.add(new Info(p+1,q));
		if (q > 1) que.add(new Info(p,q-1));
		if (q < w) que.add(new Info(p,q+1));
		boolean bUpdate = false;
		int cnt = 0;
		do {
			Collections.sort(que);
			bUpdate = false;
			List<Info> que2 = new ArrayList<>();
//			Deque<Info> que2 = new ArrayDeque<>();
			while (que.size() > 0) {
				Info info = que.remove(que.size() - 1);
				int y = info.y;
				int x = info.x;
				long s = map[y][x];
//				System.out.println(pw + " " + info + " " + s);
				if (s > 0 && s < (double)pw / xx) {
					pw += s;
					map[y][x] = 0;
					if (y > 1 && map[y-1][x] > 0) que2.add(new Info(y-1,x));
					if (y < h && map[y+1][x] > 0) que2.add(new Info(y+1,x));
					if (x > 1 && map[y][x-1] > 0) que2.add(new Info(y,x-1));
					if (x < w && map[y][x+1] > 0) que2.add(new Info(y,x+1));
					bUpdate = true;
				} else {
					que2.add(info);
				}
			}
//			que = new ArrayDeque<>(que2);
			que = new ArrayList<>(que2);
		} while (bUpdate);
		System.out.println(pw);
	}
}
