import java.util.*;
public class Main {
	static TreeSet<Point> set = new TreeSet<>();
	static TreeMap<Integer, TreeSet<Integer>> mapx = new TreeMap<>();
	static TreeMap<Integer, TreeSet<Integer>> mapy = new TreeMap<>();
	static TreeSet<Integer> getx(int x) {
		TreeSet<Integer> l = mapx.get(x);
		if (l == null) {
			l = new TreeSet<>();
			mapx.put(x, l);
		}
		return l;
	}
	static TreeSet<Integer> gety(int y) {
		TreeSet<Integer> l = mapy.get(y);
		if (l == null) {
			l = new TreeSet<>();
			mapy.put(y, l);
		}
		return l;
	}
	static void add(Point p) {
		set.add(p);
		TreeSet<Integer> ly = getx(p.x);
		ly.remove(p.y);
		TreeSet<Integer> lx = gety(p.y);
		lx.remove(p.x);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		for (int y=1; y<=h; y++) {
			for (int x=1; x<=w; x++) {
				getx(x).add(y);
				gety(y).add(x);
			}			
		}
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
//			System.out.println("s=" + set);
//			System.out.println("x=" + mapx);
//			System.out.println("y=" + mapy);
			int r = sc.nextInt();
			int c = sc.nextInt();
			Point p = new Point(r, c);
			if (!set.contains(p)) {
				add(p);
			} else {
				TreeSet<Integer> ly = getx(p.x);
				Integer tp = ly.lower(p.y);
				if (tp != null) {
					Point p2 = new Point(tp, p.x);
					add(p2);
				}
				Integer bm = ly.higher(p.y);
				if (bm != null) {
					Point p2 = new Point(bm, p.x);
					add(p2);
				}
				TreeSet<Integer> lx = gety(p.y);
				Integer lt = lx.lower(p.x);
				if (lt != null) {
					Point p2 = new Point(p.y, lt);
					add(p2);
				}
				Integer rt = lx.higher(p.x);
				if (rt != null) {
					Point p2 = new Point(p.y, rt);
					add(p2);
				}
			}
		}
//		System.out.println("s=" + set);
//		System.out.println("x=" + mapx);
//		System.out.println("y=" + mapy);
		long ans = h*w;
		ans -= set.size();
		System.out.println(ans);
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public int compareTo(Main.Point that) {
			int cmp = Integer.compare(this.y, that.y);
			if (cmp != 0) return cmp;
			return Integer.compare(this.x, that.x);
		}
		@Override
		public int hashCode() {
			return Objects.hash(y, x);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.y == that.y && this.x == that.x;
		}
		@Override
		public String toString() {
			return "(" + y + ", " + x + ")";
		}
	}
}
