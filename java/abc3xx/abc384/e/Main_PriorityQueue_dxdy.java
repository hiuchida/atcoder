import java.util.*;
public class Main {
	static int[] dy = { -1,1, 0,0 }; //UDLR
	static int[] dx = {  0,0,-1,1 }; //UDLR
	static long[][] map;
	static PriorityQueue<Info> que = new PriorityQueue<>();
	static boolean[][] bdone;

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
			long thispw = map[this.y][this.x];
			long thatpw = map[that.y][that.x];
			if (thispw == 0) {
				thispw = Long.MAX_VALUE;
			}
			if (thatpw == 0) {
				thatpw = Long.MAX_VALUE;
			}
			int cmp = Long.compare(thispw, thatpw);
			return cmp;
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
		bdone = new boolean[h+2][w+2];
		long xx = sc.nextLong();
		int p = sc.nextInt();
		int q = sc.nextInt();
		for (int y = 1; y <= h; y++) {
			for (int x = 1; x <= w; x++) {
				map[y][x] = sc.nextLong();
			}
		}
		long pw = map[p][q];
		map[p][q] = 0;
		bdone[p][q] = true;
		for (int d=0; d<dy.length; d++) {
			bdone[p+dy[d]][q+dx[d]] = true;
			que.add(new Info(p+dy[d], q+dx[d]));
		}
		while (que.size() > 0) {
			Info info = que.poll();
			int y = info.y;
			int x = info.x;
			long s = info.pw;
//			System.out.println(pw + " " + info + " " + ((pw+xx-1)/xx) + " " + s);
			if (s > 0 && s < (pw + xx - 1) / xx) {
				pw += s;
				map[y][x] = 0;
				for (int d=0; d<dy.length; d++) {
					if (!bdone[y+dy[d]][x+dx[d]]) {
						bdone[y+dy[d]][x+dx[d]] = true;
						que.add(new Info(y+dy[d],x+dx[d]));
					}
				}
			} else {
				break;
			}
		}
		System.out.println(pw);
	}
}
