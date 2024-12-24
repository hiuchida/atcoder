import java.util.*;
public class Main {
	static Map<Long, TreeSet<Long>> mapx = new HashMap<>();
	static Map<Long, TreeSet<Long>> mapy = new HashMap<>();
	static void add(long x, long y) {
		TreeSet<Long> sx = mapx.get(x);
		if (sx == null) {
			sx = new TreeSet<>();
			mapx.put(x, sx);
		}
		sx.add(y);
		TreeSet<Long> sy = mapy.get(y);
		if (sy == null) {
			sy = new TreeSet<>();
			mapy.put(y, sy);
		}
		sy.add(x);
	}
	static int chkx(long x1, long x2, long y) {
		int ans = 0;
		if (x1 > x2) {
			long tmp = x1;
			x1 = x2;
			x2 = tmp;
		}
		TreeSet<Long> sy = mapy.get(y);
		if (sy != null) {
			while (true) {
				Long v = sy.ceiling(x1);
//				System.out.println("x=" + x1 + " " + x2 + " y=" + y + " v=" + v);
				if (v == null) break;
				if (v > x2) break;
				sy.remove(v);
				TreeSet<Long> sx = mapx.get(v);
				if (sx != null) {
					sx.remove(y);
				}
				ans++;
			}
		}
		return ans;
	}
	static int chky(long x, long y1, long y2) {
		int ans = 0;
		if (y1 > y2) {
			long tmp = y1;
			y1 = y2;
			y2 = tmp;
		}
		TreeSet<Long> sx = mapx.get(x);
		if (sx != null) {
			while (true) {
				Long v = sx.ceiling(y1);
//				System.out.println("y=" + y1 + " " + y2 + " x=" + x + " v=" + v);
				if (v == null) break;
				if (v > y2) break;
				sx.remove(v);
				TreeSet<Long> sy = mapy.get(v);
				if (sy != null) {
					sy.remove(x);
				}
				ans++;
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		long x = sc.nextInt();
		long y = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int xx = sc.nextInt();
			int yy = sc.nextInt();
			add(xx, yy);
		}
//		System.out.println(mapx);
//		System.out.println(mapy);
		int ans = 0;
		for (int i = 0; i < m; i++) {
			String d = sc.next();
			int c = sc.nextInt();
			long x2, y2;
			switch (d) {
			case "U":
				y2 = y + c;
				ans += chky(x, y, y2);
				y = y2;
				break;
			case "D":
				y2 = y - c;
				ans += chky(x, y, y2);
				y = y2;
				break;
			case "R":
				x2 = x + c;
				ans += chkx(x, x2, y);
				x = x2;
				break;
			case "L":
				x2 = x - c;
				ans += chkx(x, x2, y);
				x = x2;
				break;
			}
		}
		System.out.println(x + " " + y + " " + ans);
	}
}
