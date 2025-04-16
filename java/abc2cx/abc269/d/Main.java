import java.util.*;
public class Main {
	static final int[] DY = { -1,-1, 0, 0, 1, 1, }; //DL,DR,L,R,UL,UR
	static final int[] DX = { -1, 0,-1, 1, 0, 1, }; //DL,DR,L,R,UL,UR
	static int n;
	static int max=1000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		Point[] ary=new Point[n];
		TreeSet<Point> set=new TreeSet<>();
		for (int i=0; i<n; i++) {
			ary[i]=new Point(sc.nextInt()+max, sc.nextInt()+max);
			set.add(ary[i]);
		}
//		System.out.println(Arrays.toString(ary));
		UnionFind uf=new UnionFind(2*max+1, 2*max+1);
		for (int i=0; i<n; i++) {
			int x1=ary[i].x;
			int y1=ary[i].y;
			for (int d=0; d<DY.length; d++) {
				int x=x1+DX[d];
				int y=y1+DY[d];
				if (set.contains(new Point(x, y))) {
//					if (-n<=x && x<=n && -n<=y && y<=n) {
						uf.merge(x1, y1, x, y);
//					}
				}
			}
		}
//		System.out.println(uf);
		long ans=0;
		for (int i=0; i<n; i++) {
			int x=ary[i].x;
			int y=ary[i].y;
//			x+=n;
//			y+=n;
			int u=x+y*uf.w;
			if (u==uf.root(x, y)) ans++;
		}
		System.out.println(ans);
	}
	static class UnionFind { //UnionFind_2d20250416
		int h;
		int w;
		int[] uf;
		public UnionFind(int h, int w) {
			this.h = h;
			this.w = w;
			int n = h*w;
			this.uf = new int[n];
			for (int i=0; i<n; i++) uf[i] = -1;
		}
		public int root(int x1, int y1) {
			int u=x1+y1*w;
			return root(u);
		}
		public int root(int v) {
			if (uf[v] < 0) return v;
			uf[v] = root(uf[v]);
			return uf[v];
		}
		public void merge(int x1, int y1, int x2, int y2) {
			int u=x1+y1*w;
			int v=x2+y2*w;
			merge(u, v);
		}
		public void merge(int u, int v) {
			u = root(u);
			v = root(v);
			if (u == v) return;
			int uw = uf[u];
			int vw = uf[v];
			if (uw <= vw) {
				uf[u] += uf[v];
				uf[v] = u;
			} else if (uw > vw) {
				uf[v] += uf[u];
				uf[u] = v;
			}
		}
		public boolean same(int x1, int y1, int x2, int y2) {
			int u=x1+y1*w;
			int v=x2+y2*w;
			return same(u, v);
		}
		public boolean same(int u, int v) {
			return root(u) == root(v);
		}
		public int size(int x1, int y1) {
			int u=x1+y1*w;
			return size(u);
		}
		public int size(int v) {
			v = root(v);
			return -uf[v];
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int y=0; y<h; y++) {
				List<Integer> list = new ArrayList<>();
				for (int x=0; x<w; x++) {
					list.add(uf[y*w+x]);
				}
				if (sb.length() > 0) sb.append(System.getProperty("line.separator"));
				sb.append(list);
			}
			return sb.toString();
		}
	}
	static class Point implements Comparable<Point> {
		final int x;
		final int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.x, o2.x);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.y, o2.y);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(Point that) {
			int cmp = Integer.compare(this.x, that.x);
			if (cmp != 0) return cmp;
			return Integer.compare(this.y, that.y);
		}
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.x == that.x && this.y == that.y;
		}
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}
}
/*
6
-1 -1
0 1
0 2
1 0
1 2
2 0

4
5 0
4 1
-3 -4
-2 -5

5
2 1
2 -1
1 0
3 1
1 -1
*/
