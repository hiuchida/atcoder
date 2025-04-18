import java.util.*;
public class Main {
	//Knight Move
	static final int[] DY = { 2, 1,-1,-2,-2,-1, 1, 2}; //1,2,4,5,7,8,10,11
	static final int[] DX = { 1, 2, 2, 1,-1,-2,-2,-1}; //1,2,4,5,7,8,10,11
	static int n;
	static Set<Point> set = new TreeSet<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			add(a, b);
			for (int d=0; d<DY.length; d++) {
				add(a+DY[d], b+DX[d]);
			}
		}
		long ans = (long)n * n - set.size();
		System.out.println(ans);
	}
	static void add(int y, int x) {
		if (y < 1 || y > n || x < 1 || x > n) return;
		set.add(new Point(y, x));
	}

	static class Point implements Comparable<Point> {
		int y;
		int x;
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
		public String toString() {
			return "(" + y + "," + x + ")";
		}
	}
}
/*
8 6
1 4
2 1
3 8
4 5
5 2
8 3

1000000000 1
1 1

20 10
1 4
7 11
7 15
8 10
11 6
12 5
13 1
15 2
20 10
20 15
*/
