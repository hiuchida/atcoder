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
			int cmp = Long.compare(this.pw, that.pw);
			if (cmp != 0) {
				return cmp;
			}
			return Integer.compare(this.y * 1000 + this.x, that.y * 1000 + that.x);
		}
		public boolean equals(Object o) {
			Info that = (Info)o;
			return this.y == that.y && this.x == that.x;
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
		TreeSet<Info> que = new TreeSet<>();
		que.add(new Info(p-1,q));
		que.add(new Info(p+1,q));
		que.add(new Info(p,q-1));
		que.add(new Info(p,q+1));
		while (que.size() > 0) {
			Info info = que.first();
			int y = info.y;
			int x = info.x;
			long s = info.pw;
//			System.out.println(pw + " " + info + " " + ((pw+xx-1)/xx) + " " + s);
			if (s > 0 && s < (pw + xx - 1) / xx) {
				pw += s;
				map[y][x] = 0;
				que.remove(info);
				que.add(new Info(y-1,x));
				que.add(new Info(y+1,x));
				que.add(new Info(y,x-1));
				que.add(new Info(y,x+1));
			} else {
				break;
			}
		}
		System.out.println(pw);
	}
}