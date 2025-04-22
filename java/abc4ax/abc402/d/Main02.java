import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[] aa=new int[m];
		int[] ab=new int[m];
		Point[] ap=new Point[m];
		Set<Point> set=new HashSet<>();
		for (int i=0; i<m; i++) {
			aa[i]=sc.nextInt();
			ab[i]=sc.nextInt();
			Point p=new Point(aa[i], ab[i]);
			ap[i]=p;
			set.add(p);
		}
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(ab));
//		System.out.println(Arrays.toString(ap));
//		System.out.println(set);
		long cnt=0;
		for (int i=0; i<m; i++) {
			if (!set.contains(ap[i])) continue;
			set.remove(ap[i]);
			for (int j=i+1; j<m; j++) {
				if (check(n, ap[i], ap[j])) {
					cnt++;
					set.remove(ap[j]);
//					System.out.println(cnt+" "+ap[i]+" "+ap[j]);
				}
			}
		}
		long ans=(long)m*(m-1)/2;
		ans-=cnt;
		System.out.println(ans);
	}
	static boolean check(int n, Point p1, Point p2) {
		int i=p1.st;
		int j=p1.ed;
		int k=p2.st;
		int l=p2.ed;
		if (i==k || i==l || j==k || j==l) return false;
		if (i>k) k+=n;
		if (j<l) j+=n;
		int d1=(k-i)%n;
		int d2=(j-l)%n;
//		System.out.println(p1+" "+p2+" "+d1+" "+d2);
		if (d1==d2) return true;
//		if (d2!=n) d2=n-d2;
//		System.out.println(p1+" "+p2+" "+d1+" "+d2);
//		if (d1==d2) return true;
		if (i>l) l+=n;
		if (j<k) j+=n;
		int d3=(l-i)%n;
		int d4=(j-k)%n;
//		System.out.println(p1+" "+p2+" "+d3+" "+d4);
		if (d3==d4) return true;
//		if (d4!=n) d4=n-d4;
//		System.out.println(p1+" "+p2+" "+d3+" "+d4);
//		if (d3==d4) return true;
		return false;
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
}
/*
8 3
1 5
1 8
2 4

5 10
2 5
1 5
1 2
2 4
2 3
1 3
1 4
3 5
3 4
4 5
*/
