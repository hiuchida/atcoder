import java.util.*;
public class Main {
	static class Pair {
		long x;
		long y;
		Pair(long x, long y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}
	}
	static class LineX {
		long x1;
		long x2;
		long y;
		LineX(long x1, long x2, long y) {
			if (x1 < x2) {
				this.x1 = x1;
				this.x2 = x2;
			} else {
				this.x1 = x2;
				this.x2 = x1;
			}
			this.y = y;
		}
		@Override
		public String toString() {
			return "LineX [x1=" + x1 + ", x2=" + x2 + ", y=" + y + "]";
		}
	}
	static class LineY {
		long x;
		long y1;
		long y2;
		LineY(long x, long y1, long y2) {
			this.x = x;
			if (y1 < y2) {
				this.y1 = y1;
				this.y2 = y2;
			} else {
				this.y1 = y2;
				this.y2 = y1;
			}
		}
		@Override
		public String toString() {
			return "LineY [x=" + x + ", y1=" + y1 + ", y2=" + y2 + "]";
		}
	}
	static Map<Long, List<LineX>> mapx = new HashMap<>();
	static Map<Long, List<LineY>> mapy = new HashMap<>();
	static void addx(long x1, long x2, long y) {
		List<LineX> list = mapx.get(y);
		if (list == null) {
			list = new ArrayList<>();
			mapx.put(y, list);
		}
		list.add(new LineX(x1, x2, y));
	}
	static void addy(long x, long y1, long y2) {
		List<LineY> list = mapy.get(x);
		if (list == null) {
			list = new ArrayList<>();
			mapy.put(x, list);
		}
		list.add(new LineY(x, y1, y2));
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		long x = sc.nextInt();
		long y = sc.nextInt();
		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int xx = sc.nextInt();
			int yy = sc.nextInt();
			list.add(new Pair(xx, yy));
		}
		for (int i = 0; i < m; i++) {
			String d = sc.next();
			int c = sc.nextInt();
			long x2, y2;
			switch (d) {
			case "U":
				y2 = y + c;
				addy(x, y, y2);
				y = y2;
				break;
			case "D":
				y2 = y - c;
				addy(x, y, y2);
				y = y2;
				break;
			case "R":
				x2 = x + c;
				addx(x, x2, y);
				x = x2;
				break;
			case "L":
				x2 = x - c;
				addx(x, x2, y);
				x = x2;
				break;
			}
		}
		int ans = 0;
		for (Pair p : list) {
			boolean bSkip = false;
			List<LineX> listx = mapx.get(p.y);
			if (listx != null) {
				for (LineX lx : listx) {
//					System.out.println(p.x + " " + p.y + " " + ans);
					if (lx.x1 <= p.x && p.x <= lx.x2) {
						ans++;
						bSkip = true;
						break;
					}
				}
			}
			if (bSkip)
				continue;
			List<LineY> listy = mapy.get(p.x);
			if (listy != null) {
				for (LineY ly : listy) {
//					System.out.println(p.x + " " + p.y + " " + ans);
					if (ly.y1 <= p.y && p.y <= ly.y2) {
						ans++;
						break;
					}
				}
			}
		}
		System.out.println(x + " " + y + " " + ans);
	}
}
