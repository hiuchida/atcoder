import java.util.*;
public class Main {
	public static void main(String[] args){
		long st=System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		String s = sc.next();
		List<Point> l1 = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				int j=i;
				for (; j < s.length(); j++) {
					if (s.charAt(j) == '0') break;
				}
				Point p=new Point(i, j-1);
				l1.add(p);
				i=j-1;
			} else {
			}
		}
		int k2s = l1.get(k-2).st;
		int k2e = l1.get(k-2).ed;
		int k1s = l1.get(k-1).st;
		int k1e = l1.get(k-1).ed;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < k2e+1; i++) {
			sb.append(s.charAt(i));
		}
		for (int i = 0; i < k1e-k1s+1; i++) {
			sb.append('1');
		}
		for (int i = 0; i < k1s-k2e-1; i++) {
			sb.append('0');
		}
		for (int i = k1e+1; i < s.length(); i++) {
			sb.append(s.charAt(i));
		}
		System.out.println(sb.toString());
		long ed=System.currentTimeMillis();
//		System.out.println(ed-st);
	}
	static class Point implements Comparable<Point> {
		final int st;
		final int ed;
		Point(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
		@Override
		public int compareTo(Main.Point that) {
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
