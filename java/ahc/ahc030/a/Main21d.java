import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	void print(int y, int x) {
		System.out.println("q 1 "+y+" "+x);
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
	void print(Candidate c) {
		boolean[][] flag=new boolean[N][N];
		for (int m=0; m<M; m++) {
			int y=c.ap[m].y;
			int x=c.ap[m].x;
			Oil oil=ao[m];
			for (Point pt : oil.ap) {
				int y2=y+pt.y;
				int x2=x+pt.x;
				flag[y2][x2]=true;
			}
		}
//		for (int y=0; y<N; y++) {
//			for (int x=0; x<N; x++) {
//				System.err.print(flag[y][x] ? "o" : ".");
//			}
//			System.err.println();
//		}
		List<String> list=new ArrayList<>();
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (flag[y][x]) list.add(y+" "+x);
			}
		}
//		System.err.println(list);
		System.out.print("a "+list.size());
		for (String s : list) {
			System.out.print(" "+s);
		}
		System.out.println();
		System.out.flush();
	}
	class Candidate {
		int id;
		Point[] ap=new Point[2];
		double lnLikelihood;
		double likelihood;
		int[][] cntOil;
		Candidate(Point pt0, Point pt1) {
			ap[0]=pt0;
			ap[1]=pt1;
			init();
		}
		private void init() {
			cntOil=new int[N][N];
			for (int m=0; m<M; m++) {
				int y=ap[m].y;
				int x=ap[m].x;
				Oil oil=ao[m];
				for (Point pt : oil.ap) {
					cntOil[y+pt.y][x+pt.x]++;
				}
			}
		}
		int getOilAt(int y, int x) {
			return cntOil[y][x];
		}
		boolean hasOilAt(int y, int x) {
			return cntOil[y][x]>0;
		}
		@Override
		public String toString() {
			return "Candidate [id=" + id + ", likelihood=" + likelihood + "]";
//			return "Candidate [id=" + id + ", ap=" + Arrays.toString(ap) + "]";
		}
	}
	class Island {
		int[][] cntOil;
		int cnt;
		Island() {
			cntOil=new int[N][N];
			for (int y=0; y<N; y++) {
				Arrays.fill(cntOil[y], -1);
			}
		}
		int get(int y, int x) {
			return cntOil[y][x];
		}
		boolean isUnknown(int y, int x) {
			return cntOil[y][x]<0;
		}
		void set(int y, int x, int rc) {
			cntOil[y][x]=rc;
			cnt++;
		}
	}
	class Query {
		List<Integer> coords=new ArrayList<>();
		int result;
		Query() {
			while (coords.size()==0) {
				for (int i=0; i<N*N; i++) {
					if (rand.nextBoolean())
						coords.add(i);
				}
			}
		}
		Query(int n) {
			List<Integer> list=new ArrayList<>();
			for (int i=0; i<N*N; i++) {
				list.add(i);
			}
			Collections.shuffle(list);
			for (int i=0; i<n; i++) {
				coords.add(list.get(i));
			}
			Collections.sort(coords);
		}
		void print() {
			StringBuilder sb = new StringBuilder();
			sb.append("q " + coords.size());
			for (int pos : coords) {
				sb.append(" "+(pos/N)+" "+(pos%N));
			}
			System.out.println(sb.toString());
			System.out.flush();
		}
		@Override
		public String toString() {
//			return "Query [coords=" + coords + ", result=" + result + "]";
			return "Query ["+"k="+coords.size()+", result="+result+"]";
		}
	}
	class Answer {
		List<String> list;
		int result;
		Answer() {
			list=new ArrayList<>();
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					if (I.get(y, x)>0) list.add(y+" "+x);
				}
			}
		}
		Answer(Candidate c) {
			boolean[][] flag=new boolean[N][N];
			for (int m=0; m<M; m++) {
				int y=c.ap[m].y;
				int x=c.ap[m].x;
				Oil oil=ao[m];
				for (Point pt : oil.ap) {
					int y2=y+pt.y;
					int x2=x+pt.x;
					flag[y2][x2]=true;
				}
			}
//			for (int y=0; y<N; y++) {
//				for (int x=0; x<N; x++) {
//					System.err.print(flag[y][x] ? "o" : ".");
//				}
//				System.err.println();
//			}
			list=new ArrayList<>();
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					if (flag[y][x]) list.add(y+" "+x);
				}
			}
//			System.err.println(list);
		}
		void print() {
			System.out.print("a "+list.size());
			for (String s : list) {
				System.out.print(" "+s);
			}
			System.out.println();
			System.out.flush();
		}
		@Override
		public String toString() {
//			return "Answer [list=" + list + ", result=" + result + "]";
			return "Answer ["+"d="+list.size()+", result="+result+"]";
		}
	}
	List<Candidate> generate() {
		List<Candidate> lc=new ArrayList<>();
		for (int y0=0; y0<N-ao[0].maxy; y0++) {
			for (int x0=0; x0<N-ao[0].maxx; x0++) {
				for (int y1=0; y1<N-ao[1].maxy; y1++) {
					for (int x1=0; x1<N-ao[1].maxx; x1++) {
						Point pt0=new Point(y0, x0);
						Point pt1=new Point(y1, x1);
						Candidate c=new Candidate(pt0, pt1);
						lc.add(c);
					}
				}
			}
		}
		for (int i=0; i<lc.size(); i++) {
			lc.get(i).id=i+1;
		}
		return lc;
	}
	void normalize(List<Candidate> lc) {
		double maxLnL = lc.get(0).lnLikelihood;
		for (Candidate c : lc) {
			if (c.lnLikelihood > maxLnL) {
				maxLnL = c.lnLikelihood;
			}
		}
		for (Candidate c : lc) {
			c.likelihood=Math.exp(c.lnLikelihood-maxLnL);
		}
		double total=0;
		for (Candidate c : lc) {
			total+=c.likelihood;
		}
		for (Candidate c : lc) {
			c.likelihood/=total;
		}
	}
	List<Candidate> downsizing(List<Candidate> lc) {
		if (lc.size()<10000) return lc;
		List<Candidate> list=new ArrayList<>();
		Candidate best=best(lc);
		double filter=best.likelihood/100.0;
//		double filter=Math.min(0.01, 1.0/(lc.size()+10));
		for (Candidate c : lc) {
			if (c.likelihood>=filter) list.add(c);
		}
		return list;
	}
	List<Candidate> dropout(List<Candidate> lc, int y, int x, int rc) {
		List<Candidate> list=new ArrayList<>();
		for (Candidate c : lc) {
			if (c.getOilAt(y, x)==rc) list.add(c);
		}
		return list;
	}
	Candidate best(List<Candidate> lc) {
		Candidate best=lc.get(0);
		double maxv=best.likelihood;
		for (Candidate c : lc) {
			if (maxv<c.likelihood) {
				maxv=c.likelihood;
				best=c;
			}
		}
		return best;
	}
	List<Candidate> high(List<Candidate> lc) {
		Candidate best=best(lc);
		double filter=best.likelihood*0.99;
		List<Candidate> list=new ArrayList<>();
		for (Candidate c : lc) {
			if (filter<=c.likelihood) {
				list.add(c);
			}
		}
		return list;
	}
	LnLikelihoodCache llcache=new LnLikelihoodCache();
	class LnLikelihoodCache {
		Map<Integer,double[]> map=new TreeMap<>();
		private int key(int k, int res) {
			int key=k*10000+res;
			return key;
		}
		void update(int k, int res) {
			int key=key(k, res);
			if (map.containsKey(key)) {
//				System.err.println("llcache hit k="+k+" res="+res);
				return;
			}
			double[] ad=new double[V+1];
			double sigma=Math.sqrt(k*E*(1.0-E));
			for (int S=0; S<=V; S++) {
				double v=calcLnLikelihood(k, res, S, sigma);
				ad[S]=v;
			}
			map.put(key, ad);
		}
		double get(int k, int res, int S) {
			int key=key(k, res);
			double[] ad=map.get(key);
			return ad[S];
		}
		@Override
		public String toString() {
			return map.keySet().toString();
		}
	}
	double calcLnLikelihood(int k, int res, int S, double sigma) {
		// 尤度 q(k, S, res) の計算
		double mu = (k - S) * E + S * (1.0 - E);
		double prob;
		if (res == 0) {
			prob = normalCDF(mu, sigma, 0.5);
		} else {
			prob = normalCDF(mu, sigma, res + 0.5) - normalCDF(mu, sigma, res - 0.5);
		}
		return Math.log(Math.max(prob, 1e-18));
	}
	void update(List<Candidate> lc, Query q) {
		int k=q.coords.size();
		llcache.update(k, q.result);
//		long st=System.currentTimeMillis();
//		double sigma=Math.sqrt(k*E*(1.0-E));
		for (Candidate c : lc) {
			// この候補における真の埋蔵量Sを計算
			int S=0;
//			int[][] count=new int[N][N];
//			for (int m=0; m<ao.length; m++) {
//				int y=c.ap[m].y;
//				int x=c.ap[m].x;
//				Oil oil=ao[m];
//				for (Point pt : oil.ap) {
//					count[y+pt.y][x+pt.x]++;
//				}
//			}
			for (int pos : q.coords) {
//				S+=count[pos/N][pos%N];
				S+=c.getOilAt(pos/N, pos%N);
			}
			// 対数尤度を加算
//			c.lnLikelihood+=calcLnLikelihood(k, q.result, S, sigma);
			c.lnLikelihood+=llcache.get(k, q.result, S);
		}
//		long ep=System.currentTimeMillis()-st;
//		System.err.println("update "+ep);
	}
	void solve2x(Scanner sc) {
		int T=2*N*N;
//		System.err.println("T="+T);
		double cost=0;
		List<Candidate> lc=generate();
		for (int t=0; t<T; t++) {
			normalize(lc);
//			lc=downsizing(lc);
//			System.err.println((t+1)+":"+lc);
			Candidate best=best(lc);
			double maxl=best.likelihood;
			List<Candidate> high=high(lc);
			long elap=System.currentTimeMillis()-start;
			if (!RELEASE) System.err.printf("%3d(%d):%3d %04d %.4f high=%d ", (t+1), elap, lc.size(), best.id, maxl, high.size());
//			if (!RELEASE) System.err.printf("%3d:%3d %04d %.4f %6.1f ", (t+1), lc.size(), best.id, maxl, ess);
			if (high.size()==2) {
				if (!RELEASE) System.err.printf("%s %s ", Arrays.toString(high.get(0).ap), Arrays.toString(high.get(1).ap));
				int y=0;
				int x=0;
				for (int m=0; m<high.size(); m++) {
					for (int d=0; d<high.get(m).ap.length; d++) {
						int by=high.get(m).ap[d].y;
						int bx=high.get(m).ap[d].x;
						for (int i=0; i<ao[m].ap.length; i++) {
							y=by+ao[m].ap[i].y;
							x=bx+ao[m].ap[i].x;
							if (I.isUnknown(y, x)) break;
						}
						if (I.isUnknown(y, x)) break;
					}
					if (I.isUnknown(y, x)) break;
				}
				if (I.isUnknown(y, x)) {
					print(y, x);
					int rc=sc.nextInt();
					if (!RELEASE) System.err.println("Dig [y="+y+", x="+x+", result="+rc+"]");
					cost++;
					I.set(y, x, rc);
					lc=dropout(lc, y, x, rc);
				} else {
					Answer a=new Answer(best);
					a.print();
					int rc=sc.nextInt();
					a.result=rc;
					if (!RELEASE) System.err.println(a);
					if (rc==1) break;
					cost++;
					best.lnLikelihood=-1e100;
				}
			} else if (I.cnt==N*N) {
				Answer a=new Answer();
				a.print();
				int rc=sc.nextInt();
				a.result=rc;
				if (!RELEASE) System.err.println(a);
				if (rc==1) break;
				cost++;
			} else if (lc.size()>10000) {
				int y=0;
				int x=0;
				while (true) {
					y=rand.nextInt(N);
					x=rand.nextInt(N);
					if (I.isUnknown(y, x)) break;
				}
				print(y, x);
				int rc=sc.nextInt();
				if (!RELEASE) System.err.println("Dig [y="+y+", x="+x+", result="+rc+"]");
				cost++;
				I.set(y, x, rc);
				lc=dropout(lc, y, x, rc);
			} else if (maxl>0.8) {
				Answer a=new Answer(best);
				a.print();
				int rc=sc.nextInt();
				a.result=rc;
				if (!RELEASE) System.err.println(a);
				if (rc==1) break;
				cost++;
				best.lnLikelihood=-1e100;
			} else {
				Query q=new Query();
//				Query q=new Query(N*N/2);
				q.print();
				int rc=sc.nextInt();
				q.result=rc;
				if (!RELEASE) System.err.println(q);
				update(lc, q);
				cost+=1.0/Math.sqrt(q.coords.size());
			}
		}
		long end=System.currentTimeMillis();
		long score=Math.round(1e6*cost);
		System.err.println("--- Result ---");
		System.err.println("elaps    : " + (end-start));
		System.err.println("iteration: " + iteration);
		System.err.println("score    : " + score);
	}
	void print(Point[] ap) {
		System.out.print("q "+ap.length);
		for (Point pt : ap) {
			System.out.print(" "+pt.y+" "+pt.x);
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
	void solve14c(Scanner sc) {
		int T=2*N*N;
		int t=0;
		double cost=0;
//		System.err.println("T="+T);
		int[][] map=new int[N][N];
		int b=3;
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
	void solve12(Scanner sc) {
		int T=2*N*N;
//		System.err.println("T="+T);
		double cost=0;
		boolean[][] flag=new boolean[N][N];
		int cnt=0;
//		for (int t=0; t<T; t++) {
		{
			int t=0;
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					print(y, x);
					int rc=sc.nextInt();
//					System.err.println((t+1)+":"+y+","+x+",rc="+rc);
					if (rc>0) flag[y][x]=true;
					cnt+=rc;
					cost+=1;
					t++;
					if (t>=T) break;
					if (cnt==V) break;
				}
				if (cnt==V) break;
			}
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
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		if (M==2) solve2x(sc);
		else solve14c(sc);
//		else solve12(sc);
	}
	class Oil {
		int id;
		Point[] ap;
		int maxy;
		int maxx;
		Oil(Scanner sc, int id, int n) {
			this.id=id;
			this.ap=new Point[n];
			for (int i=0; i<n; i++) {
				int y=sc.nextInt();
				int x=sc.nextInt();
				Point pt=new Point(y, x);
				ap[i]=pt;
				maxy=Math.max(maxy, pt.y);
				maxx=Math.max(maxx, pt.x);
			}
		}
		@Override
		public String toString() {
			return "Oil [id=" + id + ", maxy=" + maxy + ", maxx=" + maxx + ", ap=" + Arrays.toString(ap) + "]";
		}
	}
	int N;
	int M;
	double E;
	Oil[] ao;
	int V;
	Island I;
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
		I=new Island();
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
	// 誤差関数 (erf) の近似　※Java22以降はMath.erf()
	static double erf(double x) {
		// 精度: 最大誤差は約 1.5e-7
		double sign = (x < 0) ? -1 : 1;
		x = Math.abs(x);
		// 定数
		double p = 0.3275911;
		double a1 = 0.254829592;
		double a2 = -0.284496736;
		double a3 = 1.421413741;
		double a4 = -1.453152027;
		double a5 = 1.061405429;
		double t = 1.0 / (1.0 + p * x);
		double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);
		return sign * y;
	}
	// 累積分布関数 (CDF)
	static double normalCDF(double mean, double stdDev, double x) {
		return 0.5 * (1.0 + erf((x - mean) / (Math.sqrt(2.0) * stdDev)));
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
