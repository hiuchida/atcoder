import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	static int n;
	static int k;
	static int t;
	static boolean[][] av;
	static boolean[][] ah;
	static Point[] ap;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		k=sc.nextInt();
		t=sc.nextInt();
		sc.nextLine();
		av=new boolean[n][n-1];
		for (int y=0; y<n; y++) {
			String s=sc.next();
			for (int x=0; x<n-1; x++) {
				char ch=s.charAt(x);
				if (ch=='1') av[y][x]=true;
			}
		}
		ah=new boolean[n-1][n];
		for (int y=0; y<n-1; y++) {
			String s=sc.next();
			for (int x=0; x<n; x++) {
				char ch=s.charAt(x);
				if (ch=='1') ah[y][x]=true;
			}
		}
		ap=new Point[k];
		for (int i=0; i<k; i++) {
			int y=sc.nextInt();
			int x=sc.nextInt();
			ap[i]=new Point(y, x);
		}
		int c=n*n;
		int q=1;
		int[][] as=new int[n][n];
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				as[y][x]=y*n+x;
			}
		}
		List<Rule> lr=new ArrayList<>();
		Point p0=ap[0];
		Point p1=ap[1];
		for (int i=0; i<t; i++) {
			char d='S';
			Point pn=null;
			if (p0.y>p1.y && canMove(p0, 'U')) {
				d='U';
				pn=new Point(p0.y-1, p0.x);
			} else if (p0.y<p1.y && canMove(p0, 'D')) {
				d='D';
				pn=new Point(p0.y+1, p0.x);
			} else if (p0.x>p1.x && canMove(p0, 'L')) {
				d='L';
				pn=new Point(p0.y, p0.x-1);
			} else if (p0.x<p1.x && canMove(p0, 'R')) {
				d='R';
				pn=new Point(p0.y, p0.x+1);
			}
			if (d=='S') break;
			lr.add(new Rule(as[p0.y][p0.x], 0, as[p0.y][p0.x], 0, d));
			p0=pn;
		}
		System.out.println(c+" "+q+" "+lr.size());
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (x>0) System.out.print(" ");
				System.out.print(as[y][x]);
			}
			System.out.println();
		}
		for (Rule r : lr) {
			System.out.println(r);
		}
	}
	static boolean canMove(Point p, char d) {
		switch (d) {
		case 'U':
			if (p.y==0) return false;
			return !ah[p.y-1][p.x];
		case 'D':
			if (p.y==n-1) return false;
			return !ah[p.y][p.x];
		case 'L':
			if (p.x==0) return false;
			return !av[p.y][p.x-1];
		case 'R':
			if (p.x==n-1) return false;
			return !av[p.y][p.x];
		}
		return false;
	}
	static class Rule {
		int c;
		int q;
		int a;
		int s;
		char d;
		Rule(int c, int q, int a, int s, char d) {
			this.c=c;
			this.q=q;
			this.a=a;
			this.s=s;
			this.d=d;
		}
		@Override
		public String toString() {
			return c+" "+q+" "+a+" "+s+" "+d;
		}
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.y, o2.y);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.x, o2.x);
					return cmp;
				}
			};
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
			return "(" + y + "," + x + ")";
		}
	}
}
/*



*/
