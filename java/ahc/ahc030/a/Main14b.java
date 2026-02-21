import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	void print(int y, int x) {
		System.out.println("q 1 "+y+" "+x);
		System.out.flush();
	}
	void print(Point[] ap) {
		System.out.print("q "+ap.length);
		for (Point pt : ap) {
			System.out.print(" "+pt.y+" "+pt.x);
		}
		System.out.println();
		System.out.flush();
	}
	void print(List<String> list) {
		System.out.print("a "+list.size());
		for (String s : list) {
			System.out.print(" "+s);
		}
		System.out.println();
		System.out.flush();
	}
	void append(List<Point> order, int[][] map, int st) {
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (map[y][x]==st) order.add(new Point(y, x));
			}
		}
	}
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		int T=2*N*N;
		int t=0;
		double cost=0;
//		System.err.println("T="+T);
		int[][] map=new int[N][N];
		int b=2;
		for (int y=0; y+b-1<N; y+=b) {
			for (int x=0; x+b-1<N; x+=b) {
				Point[] ap=new Point[b*b];
				for (int d=0; d<b*b; d++) {
					ap[d]=new Point(y+d/b, x+d%b);
				}
				print(ap);
				t++;
				cost+=1/Math.sqrt(b*b);
				int rc=sc.nextInt();
//				System.err.println((t+1)+":"+Arrays.toString(ap)+",rc="+rc);
				for (Point pt : ap) {
					int st=(rc==0) ? 1:2;
					map[pt.y][pt.x]=st;
				}
			}
		}
//		for (int y=0; y<N; y++) {
//			System.err.println(Arrays.toString(map[y]));
//		}
		List<Point> order=new ArrayList<>();
		append(order, map, 2);
		append(order, map, 0);
		append(order, map, 1);
//		Collections.shuffle(order);
//		System.err.println(order);
		boolean[][] flag=new boolean[N][N];
		int cnt=0;
//		for (int t=0; t<T; t++) {
		for (int i=0; i<order.size(); i++) {
			Point pt=order.get(i);
			int y=pt.y;
			int x=pt.x;
			print(y, x);
			int rc=sc.nextInt();
//			System.err.println((t+1)+":"+y+","+x+",rc="+rc);
			if (rc>0) flag[y][x]=true;
			cnt+=rc;
			t++;
			cost+=1;
			if (t>=T) break;
			if (cnt==V) break;
		}
		List<String> list=new ArrayList<>();
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (flag[y][x]) list.add(y+" "+x);
			}
		}
		print(list);
		int rc=sc.nextInt();
//		while (true) {
//			if (isTimeout()) break;
//			iteration++;
//		}
		long end=System.currentTimeMillis();
		long score=Math.round(1e6*cost);
		System.err.println("--- Result ---");
		System.err.println("elaps    : " + (end-start));
		System.err.println("iteration: " + iteration);
		System.err.println("score    : " + score);
	}
	class Oil {
		int id;
		Point[] ap;
		Oil(Scanner sc, int id, int n) {
			this.id=id;
			this.ap=new Point[n];
			for (int i=0; i<n; i++) {
				int y=sc.nextInt();
				int x=sc.nextInt();
				Point pt=new Point(y, x);
				ap[i]=pt;
			}
		}
		@Override
		public String toString() {
			return "Oil [id=" + id + ", ap=" + Arrays.toString(ap) + "]";
		}
	}
	int N;
	int M;
	double E;
	Oil[] ao;
	int V;
	void init(Scanner sc) {
		N=sc.nextInt();
		M=sc.nextInt();
		E=sc.nextDouble();
		ao=new Oil[M];
		V=0;
		for (int i=0; i<M; i++) {
			int d=sc.nextInt();
			ao[i]=new Oil(sc, i, d);
			V+=d;
		}
//		System.err.println("N="+N+",M="+M+",E="+E);
//		System.err.println("ao="+Arrays.toString(ao));
//		System.err.println("V="+V);
	}
	static Random rand=new Random(42);
	static long start;
	static int iteration = 0;
	static double current_temperature;
	static final int time_limit = 1700;
	static final double start_temperature = 2e2;
	static final double end_temperature = 1e0;
	static boolean isTimeout() {
		long lap = System.currentTimeMillis();
		long elaps = lap - start;
        return elaps >= time_limit;
	}
	static void resetTemperature() {
		current_temperature = start_temperature;
	}
	static boolean isHot(double delta) {
		double l1 = Math.abs(delta);
		double ratio = Math.exp(-l1 / current_temperature);
		return rand.nextFloat() <= ratio;
	}
	static void updateTemperature(double progress) {
        current_temperature=Math.pow(start_temperature, 1.0 - progress) * Math.pow(end_temperature, progress);
	}
	static double getProgress() {
		long lap = System.currentTimeMillis();
		long elaps = lap - start;
        return (double)elaps / time_limit;
	}
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		Point(Point pt) {
			this.y=pt.y;
			this.x=pt.x;
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
		int calcL1(Point that) { //thatまでのマンハッタン距離
			int dx = this.x - that.x;
			int dy = this.y - that.y;
			return Math.abs(dx) + Math.abs(dy);
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
