import java.util.*;
public class Main {
	static int[] DXX= { 1, 1,-1,-1, 0, 0, 0, 0, };
	static int[] DXY= { 0, 0, 0, 0, 1, 1,-1,-1, };
	static int[] DYX= { 0, 0, 0, 0, 1,-1, 1,-1, };
	static int[] DYY= { 1,-1, 1,-1, 0, 0, 0, 0, };
	static Set<Point> set;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		boolean bok=init(n, m);
		if (!bok) {
			System.out.println(-1);
			System.exit(0);
		}
		int[][] ary=new int[n][n];
		for (int i=0; i<n; i++) {
			Arrays.fill(ary[i], Integer.MAX_VALUE);
		}
		Deque<Point> que=new ArrayDeque<>();
		ary[0][0]=0;
		que.offer(new Point(0, 0));
		while (que.size()>0) {
			Point p=que.poll();
			int now=ary[p.x][p.y];
			for (Point pd : set) {
//				System.out.println(p+" "+pd);
				for (int d=0; d<DXX.length; d++) {
					int x=p.x+pd.x*DXX[d]+pd.y*DXY[d];
					int y=p.y+pd.x*DYX[d]+pd.y*DYY[d];
//					System.out.println(p+" "+x+" "+y);
					if (0<=x && x<n && 0<=y && y<n) {
						int v=ary[x][y];
						if (now<v) {
							ary[x][y]=now+1;
							que.offer(new Point(x, y));
						}
					}
				}
			}
		}
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				System.out.print(ary[i][j]+" ");
			}
			System.out.println();
		}
	}
	static boolean init(int n, int m) {
		boolean bok=false;
		int min=Math.min(n*n, m);
		set=new TreeSet<>();
		for (int i=0; i*i<=m; i++) {
			for (int j=i+1; j*j<=m; j++) {
				int x=i*i+j*j;
				if (x==m) {
					set.add(new Point(i, j));
					if (i==1 || j==1) bok=true;
				}
			}
		}
//		System.out.println(set);
		return bok;
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
3 1

10 5
*/
/*
400 1000000
*/
