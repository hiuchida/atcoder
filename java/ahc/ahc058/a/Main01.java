import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		Answer ans=new Answer();
		for (int t=0; t<T; t++) {
			ans.ai[t]=-1;
		}
		ans.print();
	}
	class Answer {
		int[] ai;
		int[] aj;
		Answer() {
			ai=new int[T];
			aj=new int[T];
		}
		void print() {
			for (int t=0; t<T; t++) {
				if (ai[t]<0) {
					System.out.println(-1);
				} else {
					System.out.println(ai[t]+" "+aj[t]);
				}
			}
		}
	}
	int N;
	int L;
	int T;
	int K;
	int[] aa;
	long[][] aal;
	void init(Scanner sc) {
		N=sc.nextInt();
		L=sc.nextInt();
		T=sc.nextInt();
		K=sc.nextInt();
		aa=new int[N];
		for (int i=0; i<N; i++) {
			int a=sc.nextInt();
			aa[i]=a;
		}
//		System.out.println(Arrays.toString(aa));
		aal=new long[L][N];
		for (int i=0; i<L; i++) {
			for (int j=0; j<N; j++) {
				long c=sc.nextLong();
				aal[i][j]=c;
			}
		}
//		for (int i=0; i<L; i++) {
//			System.out.println(Arrays.toString(aal[i]));
//		}
	}
	static Random rand=new Random(42);
	static long start;
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
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



*/
