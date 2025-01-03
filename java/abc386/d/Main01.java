import java.util.*;
public class Main {
	static TreeMap<Integer, List<Point>> mapbx = new TreeMap<>();
	static TreeMap<Integer, List<Point>> mapby = new TreeMap<>();
	static TreeMap<Integer, List<Point>> mapwx = new TreeMap<>();
	static TreeMap<Integer, List<Point>> mapwy = new TreeMap<>();
	static List<Point> getbx(int x) {
		List<Point> list = mapbx.get(x);
		if (list == null) {
			list = new ArrayList<Point>();
			mapbx.put(x, list);
		}
		return list;
	}
	static List<Point> getby(int y) {
		List<Point> list = mapby.get(y);
		if (list == null) {
			list = new ArrayList<Point>();
			mapby.put(y, list);
		}
		return list;
	}
	static List<Point> getwx(int x) {
		List<Point> list = mapwx.get(x);
		if (list == null) {
			list = new ArrayList<Point>();
			mapwx.put(x, list);
		}
		return list;
	}
	static List<Point> getwy(int y) {
		List<Point> list = mapwy.get(y);
		if (list == null) {
			list = new ArrayList<Point>();
			mapwy.put(y, list);
		}
		return list;
	}
	static void addb(int x, int y) {
		Point p = new Point(x, y);
		List<Point> list;
		list = getbx(x);
		list.add(p);
		list = getby(y);
		list.add(p);
	}
	static void addw(int x, int y) {
		Point p = new Point(x, y);
		List<Point> list;
		list = getwx(x);
		list.add(p);
		list = getwy(y);
		list.add(p);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		for (int i=0; i<m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			String c = sc.next();
			if (c.equals("B")) addb(x, y);
			else addw(x, y);
		}
//		System.out.println(mapbx);
//		System.out.println(mapby);
//		System.out.println(mapwx);
//		System.out.println(mapwy);
		for (int wx : mapwx.keySet()) {
			List<Point> listx = mapwx.get(wx);
			NavigableMap<Integer, List<Point>> map = mapbx.tailMap(wx, true);
			for (Point pw : listx) {
				for (int bx : map.keySet()) {
					List<Point> list = map.get(bx);
					for (Point pb : list) {
						if (pw.y < pb.y) ng();
					}
				}
			}
		}
		for (int wy : mapwy.keySet()) {
			List<Point> listy = mapwy.get(wy);
			NavigableMap<Integer, List<Point>> map = mapby.tailMap(wy, true);
			for (Point pw : listy) {
				for (int by : map.keySet()) {
					List<Point> list = map.get(by);
					for (Point pb : list) {
						if (pw.x < pb.x) ng();
					}
				}
			}
		}
		ok();
	}
	static void ok() {
		System.out.println("Yes");
		System.exit(0);
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public int compareTo(Point that) {
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
