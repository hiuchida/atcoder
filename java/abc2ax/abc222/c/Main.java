import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		String[] ary=new String[2*n+1];
		Point[] ap=new Point[2*n];
		for (int i=1; i<=2*n; i++) {
			String s = sc.next();
			ary[i]=s;
			ap[i-1]=new Point(0, i);
		}
//		System.out.println(Arrays.toString(ap));
		for (int j=0; j<m; j++) {
			for (int i=0; i<2*n; i+=2) {
				Point p1=ap[i];
				Point p2=ap[i+1];
				char ch1=ary[p1.ed].charAt(j);
				char ch2=ary[p2.ed].charAt(j);
				if (iswin1(ch1, ch2)) p1.st++;
				if (iswin1(ch2, ch1)) p2.st++;
			}
			Arrays.sort(ap);
//			System.out.println(Arrays.toString(ap));
		}
		for (int i=0; i<2*n; i++) {
			System.out.println(ap[i].ed);
		}
	}
	static boolean iswin1(char ch1, char ch2) {
		switch (ch1) {
		case 'G':
			if (ch2=='C') return true;
			return false;
		case 'C':
			if (ch2=='P') return true;
			return false;
		case 'P':
			if (ch2=='G') return true;
			return false;
		}
		return false;
	}
	static class Point implements Comparable<Point> {
		int st;
		final int ed;
		Point(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
		@Override
		public int compareTo(Main.Point that) {
			int cmp = -Integer.compare(this.st, that.st);
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
2 3
GCP
PPP
CCC
PPC

2 2
GC
PG
CG
PP
*/
