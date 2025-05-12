import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		int l=sc.nextInt();
		UnionFind uf1=new UnionFind(n+1);
		UnionFind uf2=new UnionFind(n+1);
		for (int i=0; i<k; i++) {
			int p=sc.nextInt();
			int q=sc.nextInt();
			uf1.merge(p, q);
		}
		for (int i=0; i<l; i++) {
			int r=sc.nextInt();
			int s=sc.nextInt();
			uf2.merge(r, s);
		}
//		System.out.println(uf1);
//		System.out.println(uf2);
		Map<Point, Integer> map=new HashMap<>();
		for (int i=1; i<=n; i++) {
			int r1=uf1.root(i);
			int r2=uf2.root(i);
			Point p=new Point(r1, r2);
			Integer v=map.get(p);
			if (v==null) v=1;
			else v++;
			map.put(p, v);
		}
//		System.out.println(map);
		for (int i=1; i<=n; i++) {
			int r1=uf1.root(i);
			int r2=uf2.root(i);
			Point p=new Point(r1, r2);
			Integer v=map.get(p);
			System.out.print(v+" ");
		}
		System.out.println();
	}
	static class Point implements Comparable<Point> {
		final int st;
		final int ed;
		Point(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.st, o2.st);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.ed, o2.ed);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(Point that) {
			int cmp = Integer.compare(this.st, that.st);
			if (cmp != 0) return cmp;
			return Integer.compare(this.ed, that.ed);
		}
		@Override
		public int hashCode() {
			return Objects.hash(st, ed);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.st == that.st && this.ed == that.ed;
		}
		@Override
		public String toString() {
			return "(" + st + "," + ed + ")";
		}
	}
	static class UnionFind { //UnionFind20250102
		int[] uf;
		public UnionFind(int n) {
			uf = new int[n];
			for (int i=0; i<n; i++) uf[i] = -1;
		}
		public int root(int v) {
			if (uf[v] < 0) return v;
			uf[v] = root(uf[v]);
			return uf[v];
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
		public boolean same(int u, int v) {
			return root(u) == root(v);
		}
		public int size(int v) {
			v = root(v);
			return -uf[v];
		}
		@Override
		public String toString() {
			return Arrays.toString(uf);
		}
	}
}
/*



*/
