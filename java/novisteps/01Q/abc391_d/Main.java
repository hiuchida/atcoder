import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int w = sc.nextInt();
		Point[] blocks=new Point[n+1];
		List<List<Point>> grids=new ArrayList<>();
		for (int i=0; i<=w; i++) {
			grids.add(new ArrayList<>());
		}
		for (int i=1; i<=n; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			Point pt=new Point(x, y);
			blocks[i]=pt;
			grids.get(x).add(pt);
		}
		int minD=Integer.MAX_VALUE;
		for (int i=1; i<=w; i++) {
			List<Point> list=grids.get(i);
			Collections.sort(list);
			for (int j=0; j<list.size(); j++) {
				list.get(j).d=j+1;
			}
			minD=Math.min(minD, list.size());
		}
//		System.out.println(Arrays.toString(blocks));
//		System.out.println(grids);
//		System.out.println(minD);
		int[] times=new int[minD+1];
		for (int i=1; i<=minD; i++) {
			int maxY=0;
			for (int j=1; j<=w; j++) {
				Point pt=grids.get(j).get(i-1);
				maxY=Math.max(maxY, pt.y);
			}
			times[i]=maxY;
		}
//		System.out.println(Arrays.toString(times));
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
			int t = sc.nextInt();
			int a = sc.nextInt();
			Point pt=blocks[a];
			if (pt.d<=minD) {
				if (t<times[pt.d]) {
					System.out.println("Yes");
				} else {
					System.out.println("No");
				}
			} else {
				System.out.println("Yes");
			}
		}
	}
	static class Point implements Comparable<Point> {
		final int x;
		final int y;
		int d;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.d = 0;
		}
		@Override
		public int compareTo(Main.Point that) {
			int cmp = Integer.compare(this.y, that.y);
			if (cmp != 0) return cmp;
			return Integer.compare(this.x, that.x);
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
			return "(" + x + "," + y + "," + d + ")";
		}
	}
}
/*
5 3
1 1
1 2
2 2
3 2
2 3
6
1 1
1 2
2 3
2 5
3 4
3 5

3 2
1 1
2 1
1 2
4
1 1
1 2
1 3
2 3
*/
/*
1 2
1 1
1
2 1

4 2
1 1
1 2
2 1
2 2
3
1 1
1 2
2 2

4 2
1 1
1 3
2 1
2 3
3
1 1
2 2
3 2

4 2
1 2
1 3
2 2
2 3
4
1 1
2 1
2 2
3 2
*/
