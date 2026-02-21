import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	class Candidate implements Comparable<Candidate> {
		int id;
		long hash;
		Point[] ap=new Point[M];
		double lnLikelihood;
		double likelihood;
		int[][] cntOil;
		Candidate(Point pt0, Point pt1) {
			ap[0]=pt0;
			ap[1]=pt1;
			init();
		}
		Candidate(Point[] ap0) {
			ap=new Point[M];
			for (int m=0; m<M; m++) {
				ap[m]=ap0[m];
			}
			init();
		}
		Candidate(Candidate that) {
			ap=new Point[M];
			for (int m=0; m<M; m++) {
				ap[m]=that.ap[m];
			}
//			init();
		}
		void init() {
			hash=0;
			cntOil=new int[N][N];
			for (int m=0; m<M; m++) {
				int y=ap[m].y;
				int x=ap[m].x;
				hash^=Z.get(m, y, x);
				Oil oil=O[m];
				for (Point pt : oil.ap) {
					cntOil[y+pt.y][x+pt.x]++;
				}
			}
		}
		void recalc(List<Query> history) {
			for (Query q : history) {
				int k=q.coords.size();
				double sigma=Math.sqrt(k*E*(1.0-E));
				CU.update(this, q, sigma);
			}
		}
		int getOilAt(int y, int x) {
			return cntOil[y][x];
		}
		boolean hasOilAt(int y, int x) {
			return cntOil[y][x]>0;
		}
		@Override
		public int compareTo(Candidate that) {
			return -1*Double.compare(this.likelihood, that.likelihood);
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
	class Dig {
		int y;
		int x;
		int result;
		Dig(int y, int x) {
			this.y=y;
			this.x=x;
		}
		double print(Scanner sc) {
			System.out.println("q 1 "+y+" "+x);
			System.out.flush();
			result=sc.nextInt();
			I.set(y, x, result);
			return 1;
		}
		@Override
		public String toString() {
			return "Dig [y="+y+", x="+x+", result="+result+"]";
		}
	}
	class Query {
		long hash;
		List<Integer> coords=new ArrayList<>();
		int result;
		Query() {
			while (coords.size()==0) {
				for (int i=0; i<N*N; i++) {
					if (rand.nextBoolean())
						coords.add(i);
				}
			}
			init();
		}
		Query(Point[] ap) {
			for (Point pt : ap) {
				coords.add(pt.y*N+pt.x);
			}
			init();
		}
		Query(double[][] aad) {
//			double cond=0.9;
			while (true) {
				for (int i=0; i<N*N; i++) {
					if (aad[i/N][i%N]>0.9) {
						if (rand.nextDouble()<0.9) coords.add(i);
					} else if (aad[i/N][i%N]>0.7) {
						if (rand.nextDouble()<0.6) coords.add(i);
					} else if (aad[i/N][i%N]>0.5) {
						if (rand.nextDouble()<0.3) coords.add(i);
					} else {
						if (rand.nextDouble()<0.1) coords.add(i);
					}
				}
				if (coords.size()>0) break;
//				cond-=0.1;
			}
			init();
		}
		void init() {
			hash=0;
			for (int pos : coords) {
				hash^=Z.get(0, pos/N, pos%N);
			}
		}
		double print(Scanner sc) {
			StringBuilder sb = new StringBuilder();
			sb.append("q " + coords.size());
			for (int pos : coords) {
				sb.append(" "+(pos/N)+" "+(pos%N));
			}
			System.out.println(sb.toString());
			System.out.flush();
			result=sc.nextInt();
			return 1/Math.sqrt(coords.size());
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
				Oil oil=O[m];
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
		double print(Scanner sc) {
			System.out.print("a "+list.size());
			for (String s : list) {
				System.out.print(" "+s);
			}
			System.out.println();
			System.out.flush();
			result=sc.nextInt();
			if (result==0) return 1;
			else return 0;
		}
		@Override
		public String toString() {
//			return "Answer [list=" + list + ", result=" + result + "]";
			return "Answer ["+"d="+list.size()+", result="+result+"]";
		}
	}
	void solve3x(Scanner sc) {
		List<Candidate> lc=CU.generate(1000);
		solvexx(sc, lc, true);
	}
	void solve2x(Scanner sc) {
		List<Candidate> lc=CU.generate();
		solvexx(sc, lc, false);
	}
	void solvexx(Scanner sc, List<Candidate> lc, boolean bRemake) {
//		System.err.println("lc.size="+lc.size());
		double cost=0;
		List<Query> history=new ArrayList<>();
		boolean bEntropy=true;
		for (int t=0; t<T; t++) {
			if (bRemake && (lc.size()==0 || t>=T/2)) {
				long elap=System.currentTimeMillis()-start;
				if (!RELEASE) System.err.printf("%3d(%d):%3d ", (t+1), elap, lc.size());
				if (I.cnt==N*N) {
					Answer a=new Answer();
					cost+=a.print(sc);
					if (!RELEASE) System.err.println(a);
					if (a.result==1) break;
				}
				boolean bMiss=true;
				for (int i=0; i<N*N; i++) {
					int y=i/N;
					int x=i%N;
					if (I.isUnknown(y, x)) {
						Dig d=new Dig(y, x);
						cost+=d.print(sc);
						if (!RELEASE) System.err.println(d);
						lc=CU.dropout(lc, y, x, d.result);
						bMiss=false;
						break;
					}
				}
				if (bMiss) {
					if (!RELEASE) System.err.println("SKIP");
				}
				continue;
			}
			CU.normalize(lc);
			Candidate best=CU.best(lc);
			double maxl=best.likelihood;
			List<Candidate> high=CU.high(lc);
			long elap=System.currentTimeMillis()-start;
			if (!RELEASE) System.err.printf("%3d(%d):%3d %04d maxl=%.4f high=%d ", (t+1), elap, lc.size(), best.id, maxl, high.size());
//			System.err.println();
			if (lc.size()<10000 && bEntropy) {
				bEntropy=false;
//				double[][] aad2=CU.calculateEntropyMap(lc);
//				double maxe2=best(aad2);
//				double sume2=sum(aad2);
//				double rate2=sume2/N/N;
//				System.err.printf("calculateEntropyMap maxe=%.4f sume=%.4f rate=%.1f", maxe2, sume2, rate2);
//				System.err.println();
//				debug(aad2);
			}
			double[][] aad=CU.calculateBinaryEntropyMap(lc);
			List<PointDouble> sorte=CU.sort(aad);
			double maxe=sorte.get(0).val;
			double sume=CU.sum(aad);
			double rate=sume/N/N;
//			System.err.printf("%3d:%04d maxl=%.4f ", (t+1), best.id, maxl);
//			System.err.println();
			if (!RELEASE) System.err.printf("calculateBinaryEntropyMap maxe=%s %.4f sume=%.4f rate=%.2f ", sorte.get(0).pt, maxe, sume, rate);
//			System.err.println();
//			debug(aad);
//			if (high.size()==1) {
//				if (!RELEASE) System.err.printf("%s ", Arrays.toString(high.get(0).ap));
//				Answer a=new Answer(best);
//				cost+=a.print(sc);
//				if (!RELEASE) System.err.println(a);
//				if (a.result==1) break;
//				best.lnLikelihood=-1e100;
//				if (bRemake) CU.regenerate(lc, history);
//			} else if (high.size()==2) {
			if (high.size()==2) {
				if (!RELEASE) System.err.printf("%s %s ", Arrays.toString(high.get(0).ap), Arrays.toString(high.get(1).ap));
				int y=0;
				int x=0;
				for (int i=0; i<high.size(); i++) {
					for (int m=0; m<high.get(i).ap.length; m++) {
						int by=high.get(i).ap[m].y;
						int bx=high.get(i).ap[m].x;
						for (int j=0; j<O[m].ap.length; j++) {
							y=by+O[m].ap[j].y;
							x=bx+O[m].ap[j].x;
							if (I.isUnknown(y, x)) break;
						}
						if (I.isUnknown(y, x)) break;
					}
					if (I.isUnknown(y, x)) break;
				}
				if (I.isUnknown(y, x)) {
					Dig d=new Dig(y, x);
					cost+=d.print(sc);
					if (!RELEASE) System.err.println(d);
					lc=CU.dropout(lc, y, x, d.result);
					CU.normalize(lc);
					if (bRemake) CU.regenerate(lc, history);
				} else {
					Answer a=new Answer(best);
					cost+=a.print(sc);
					if (!RELEASE) System.err.println(a);
					if (a.result==1) break;
					best.lnLikelihood=-1e100;
					if (bRemake) CU.regenerate(lc, history);
				}
			} else if (I.cnt==N*N) {
				Answer a=new Answer();
				cost+=a.print(sc);
				if (!RELEASE) System.err.println(a);
				if (a.result==1) break;
			} else if (maxl>0.8 || rate<0.1) {
				Answer a=new Answer(best);
				cost+=a.print(sc);
				if (!RELEASE) System.err.println(a);
				if (a.result==1) break;
				best.lnLikelihood=-1e100;
				if (bRemake) CU.regenerate(lc, history);
			} else if (lc.size()>10000 || maxe>1.00) {
				int y=0;
				int x=0;
				for (PointDouble pd : sorte) {
					y=pd.pt.y;
					x=pd.pt.x;
					if (I.isUnknown(y, x)) break;
				}
				Dig d=new Dig(y, x);
				cost+=d.print(sc);
				if (!RELEASE) System.err.println(d);
				lc=CU.dropout(lc, y, x, d.result);
			} else {
				Query q=new Query(aad);
//				Query q=new Query();
				cost+=q.print(sc);
				if (!RELEASE) System.err.println(q);
				CU.update(lc, q);
			}
		}
		long end=System.currentTimeMillis();
		long score=Math.round(1e6*cost);
		System.err.println("--- Result ---");
		System.err.println("elaps    : " + (end-start));
		System.err.println("iteration: " + iteration);
		System.err.println("score    : " + score);
	}
	void solve14c_append(List<Point> order, int[][] map, int st) {
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (map[y][x]==st) order.add(new Point(y, x));
			}
		}
	}
	void solve14c(Scanner sc) {
		double cost=0;
		int t=0;
		int[][] map=new int[N][N];
		int b=3;
		for (int y=0; y+b-1<N; y+=b) {
			for (int x=0; x+b-1<N; x+=b) {
				long elap=System.currentTimeMillis()-start;
				if (!RELEASE) System.err.printf("%3d(%d): ", (t+1), elap);
				Point[] ap=new Point[b*b];
				for (int d=0; d<b*b; d++) {
					ap[d]=new Point(y+d/b, x+d%b);
				}
				Query q=new Query(ap);
				cost+=q.print(sc);
				if (!RELEASE) System.err.println(q);
				for (Point pt : ap) {
					int st=(q.result==0) ? 1:2;
					map[pt.y][pt.x]=st;
				}
				t++;
			}
		}
//		for (int y=0; y<N; y++) {
//			System.err.println(Arrays.toString(map[y]));
//		}
		List<Point> order=new ArrayList<>();
		solve14c_append(order, map, 2);
		solve14c_append(order, map, 0);
		solve14c_append(order, map, 1);
//		Collections.shuffle(order);
//		System.err.println(order);
		int cnt=0;
		int idx=0;
		for (; t<T; t++) {
			long elap=System.currentTimeMillis()-start;
			if (!RELEASE) System.err.printf("%3d(%d): ", (t+1), elap);
			Point pt=order.get(idx);
			idx++;
			int y=pt.y;
			int x=pt.x;
			Dig d=new Dig(y, x);
			cost+=d.print(sc);
			if (!RELEASE) System.err.println(d);
			cnt+=d.result;
			if (cnt==V) {
				t++;
				break;
			}
		}
		{
			long elap=System.currentTimeMillis()-start;
			if (!RELEASE) System.err.printf("%3d(%d): ", (t+1), elap);
			Answer a=new Answer();
			cost+=a.print(sc);
			if (!RELEASE) System.err.println(a);
			if (a.result==0) cost=1000;
		}
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
		double cost=0;
		int cnt=0;
		int idx=0;
		int t=0;
		for (t=0; t<T; t++) {
			long elap=System.currentTimeMillis()-start;
			if (!RELEASE) System.err.printf("%3d(%d): ", (t+1), elap);
			int y=idx/N;
			int x=idx%N;
			idx++;
			Dig d=new Dig(y, x);
			cost+=d.print(sc);
			if (!RELEASE) System.err.println(d);
			cnt+=d.result;
			if (cnt==V) {
				t++;
				break;
			}
		}
		{
			long elap=System.currentTimeMillis()-start;
			if (!RELEASE) System.err.printf("%3d(%d): ", (t+1), elap);
			Answer a=new Answer();
			cost+=a.print(sc);
			if (!RELEASE) System.err.println(a);
			if (a.result==0) cost=1000;
		}
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
		if (M==3) solve3x(sc);
		else if (M==2) solve2x(sc);
//		if (M==2) solve2x(sc);
		else solve14c(sc);
//		else solve12(sc);
//		solve2x(sc);
//		solve14c(sc);
//		solve12(sc);
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
				Point pt=P.get(y, x);
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
	class PointDouble implements Comparable<PointDouble> {
		Point pt;
	    double val;
	    PointDouble(Point pt, double val) {
	    	this.pt=pt;
	    	this.val=val;
	    }
	    PointDouble(int y, int x, double val) {
	    	this(P.get(y, x), val);
	    }
		@Override
		public int compareTo(PointDouble that) {
			return -1*Double.compare(this.val, that.val);
		}
	}
	class Zhash {
		long[][][] tbl;
		Zhash() {
			tbl=new long[M][N][N];
			for (int m=0; m<M; m++) {
				for (int y=0; y<N; y++) {
					for (int x=0; x<N; x++) {
						tbl[m][y][x]=rand.nextLong();
					}
				}
			}
		}
		long get(int m, int y, int x) {
			return tbl[m][y][x];
		}
	}
	class Points {
		int n;
		Point[][] ary;
		Points(int n) {
			this.n=n;
			this.ary=new Point[n][n];
		}
		Point get(int y, int x) {
			if (ary[y][x]==null) ary[y][x]=new Point(y, x);
			return ary[y][x];
		}
	}
	class QueryUtil {
		Set<Long> set=new HashSet<>();
		boolean check(Query q) {
			if (set.contains(q.hash)) {
//				System.err.println("check conflict hash="+q.hash);
				return false;
			}
			set.add(q.hash);
			return true;
		}
	}
	class CandidateUtil {
		Set<Long> set=new HashSet<>();
		boolean check(Candidate c) {
			if (set.contains(c.hash)) {
//				System.err.println("check conflict hash="+c.hash);
				return false;
			}
			set.add(c.hash);
			return true;
		}
		List<Candidate> generate() {
			List<Candidate> lc=new ArrayList<>();
			for (int y0=0; y0<N-O[0].maxy; y0++) {
				for (int x0=0; x0<N-O[0].maxx; x0++) {
					for (int y1=0; y1<N-O[1].maxy; y1++) {
						for (int x1=0; x1<N-O[1].maxx; x1++) {
							Point pt0=P.get(y0, x0);
							Point pt1=P.get(y1, x1);
							Candidate c=new Candidate(pt0, pt1);
							if (check(c)) {
								lc.add(c);
							}
						}
					}
				}
			}
			for (int i=0; i<lc.size(); i++) {
				lc.get(i).id=ID.get();
			}
			return lc;
		}
		int mincount(int cnt) {
			int sum=1;
			for (int m=0; m<M; m++) {
				int y=N-O[m].maxy;
				int x=N-O[m].maxx;
				int v=y*x;
				sum*=v;
				if (sum>cnt) break;
			}
			return Math.min(cnt, sum);
		}
		List<Candidate> generate(int cnt) {
			cnt=mincount(cnt);
			List<Candidate> lc=new ArrayList<>();
			while (lc.size()<cnt) {
				Point[] ap=new Point[M];
				for (int m=0; m<M; m++) {
					int y=rand.nextInt(N-O[m].maxy);
					int x=rand.nextInt(N-O[m].maxx);
					Point pt=new Point(y, x);
					ap[m]=pt;
				}
				Candidate c=new Candidate(ap);
				if (check(c)) {
					lc.add(c);
				}
			}
			for (int i=0; i<lc.size(); i++) {
				lc.get(i).id=ID.get();
			}
			return lc;
		}
		void regenerate(List<Candidate> lc, List<Query> history) {
			regenerate31(lc, history);
//			regenerate32(lc, history);
		}
		void regenerate31(List<Candidate> lc, List<Query> history) {
			int last=lc.size();
			Collections.sort(lc);
			List<Candidate> add=new ArrayList<>();
			for (int i=0; i<last/M/4; i++) {
				Candidate best=lc.get(i);
				for (int m=0; m<M; m++) {
					int maxy=O[m].maxy;
					int maxx=O[m].maxx;
					int y=best.ap[m].y;
					int x=best.ap[m].x;
					if (y>0) {
						Candidate c=new Candidate(best);
						c.ap[m]=new Point(y-1, x);
						c.init();
						if (check(c)) {
							c.recalc(history);
							c.id=ID.get();
							add.add(c);
						}
					}
					if (y<N-maxy-1) {
						Candidate c=new Candidate(best);
						c.ap[m]=new Point(y+1, x);
						c.init();
						if (check(c)) {
							c.recalc(history);
							c.id=ID.get();
							add.add(c);
						}
					}
					if (x>0) {
						Candidate c=new Candidate(best);
						c.ap[m]=new Point(y, x-1);
						c.init();
						if (check(c)) {
							c.recalc(history);
							c.id=ID.get();
							add.add(c);
						}
					}
					if (x<N-maxx-1) {
						Candidate c=new Candidate(best);
						c.ap[m]=new Point(y, x+1);
						c.init();
						if (check(c)) {
							c.recalc(history);
							c.id=ID.get();
							add.add(c);
						}
					}
				}
			}
			int del=last-last/M/4;
			del=Math.min(del, add.size()-10);
			for (int i=0; i<del; i++) {
				lc.remove(last-i-1);
			}
//			System.err.println(last+" "+(last-last/M/4)+" "+add.size());
			lc.addAll(add);
//			for (Candidate c : lc) {
//				c.lnLikelihood=0;
//			}
//			System.err.println("regenerate:"+last+" "+(last-last/M/4)+" "+add.size());
		}
		void regenerate32(List<Candidate> lc, List<Query> history) {
			//likelihoodは正規化されている前提
			Collections.sort(lc);
			TreeMap<Double, Candidate> tree=new TreeMap<>();
			double key=0;
			for (Candidate c : lc) {
				key+=c.likelihood;
				tree.put(key, c);
			}
			List<Candidate> lchoise=new ArrayList<>();
			for (int i=0; i<lc.size()/10; i++) {
				lchoise.add(tree.ceilingEntry(rand.nextDouble()).getValue());
			}
//			System.err.println(lchoise);
			int last=lc.size();
			int mid=last/M/4;
			List<Candidate> add=new ArrayList<>();
			double lnLikelihood=lc.get(mid).lnLikelihood;
			for (int i=0; i<mid; i++) {
				Candidate best=lc.get(i);
				for (int m=0; m<M; m++) {
					int maxy=O[m].maxy;
					int maxx=O[m].maxx;
					int y=best.ap[m].y;
					int x=best.ap[m].x;
					for (int d=0; d<DY.length; d++) {
						int ny=y+DY[d];
						int nx=x+DX[d];
						if (ny<0 || nx<0) continue;
						if (ny>=N-maxy || nx>=N-maxx) continue;
						Candidate c=new Candidate(best);
						c.ap[m]=P.get(ny, nx);
						c.init();
						if (check(c)) {
							c.recalc(history);
							if (lnLikelihood<=c.lnLikelihood) {
								c.id=ID.get();
								add.add(c);
							}
						}
					}
				}
			}
			for (Candidate best : lchoise) {
				boolean bDone=false;
				int retry=0;
				while (!bDone && retry<100) {
					retry++;
					int m=rand.nextInt(M);
					int maxy=O[m].maxy;
					int maxx=O[m].maxx;
					int y=best.ap[m].y;
					int x=best.ap[m].x;
					int ny=rand.nextInt(N-maxy);
					int nx=rand.nextInt(N-maxx);
					if (ny<0 || nx<0) continue;
					if (ny>=N-maxy || nx>=N-maxx) continue;
					Candidate c=new Candidate(best);
					c.ap[m]=P.get(ny, nx);
					c.init();
					if (check(c)) {
						c.recalc(history);
						if (lnLikelihood<=c.lnLikelihood) {
							c.id=ID.get();
							add.add(c);
							bDone=true;
						}
					}
				}
			}
			int del=last-mid;
			del=Math.min(del, add.size());
			for (int i=0; i<del; i++) {
				lc.remove(last-i-1);
			}
			lc.addAll(add);
//			System.err.println("regenerate:old="+last+" del="+del+" add="+add.size()+" new="+lc.size());
//			for (Candidate c : lc) {
//				c.lnLikelihood=0;
//			}
//			System.err.println("regenerate:"+last+" "+(last-last/M/4)+" "+add.size());
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
		double ess(List<Candidate> lc) {
			//Effective Sample Size: 有効サンプルサイズ
			//likelihoodは正規化されている前提
			double sum=0;
			for (Candidate c : lc) {
				sum+=c.likelihood*c.likelihood;
			}
			return 1.0/sum;
		}
		List<Candidate> dropout(List<Candidate> lc, int y, int x, int rc) {
			List<Candidate> list=new ArrayList<>();
			for (Candidate c : lc) {
				if (c.getOilAt(y, x)==rc) list.add(c);
			}
//			System.err.printf("dropout %d->%d ", lc.size(), list.size());
			return list;
		}
		double[][] calculateBinaryEntropyMap(List<Candidate> lc) {
//			long st=System.currentTimeMillis();
		    double[][] entropyMap=new double[N][N];
		    double totalWeight=0;
		    // 1. 各マスの「油がある期待値」を計算
		    double[][] probMap=new double[N][N];
		    for (Candidate c : lc) {
		        double weight=Math.exp(c.lnLikelihood); // 対数尤度を実数に戻す
		        totalWeight+=weight;
				for (int y=0; y<N; y++) {
					for (int x=0; x<N; x++) {
		                if (c.hasOilAt(y, x)) {
		                    probMap[y][x]+=weight;
		                }
		            }
		        }
		    }
		    // 2. エントロピーに変換
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
		            double p=probMap[y][x]/totalWeight;
		            entropyMap[y][x]=calculateBinaryEntropy(p);
		        }
		    }
//			long ep=System.currentTimeMillis()-st;
//			System.err.println("calculateBinaryEntropyMap "+ep);
		    return entropyMap;
		}
		double calculateBinaryEntropy(double p) {
		    if (p<=0 || p>=1) return 0; // 確信している状態
		    return -(p*Math.log(p)+(1-p)*Math.log(1-p))/Math.log(2);
		}
		double[][] calculateEntropyMap(List<Candidate> lc) {
//			long st=System.currentTimeMillis();
		    double[][] entropyMap=new double[N][N];
		    for (int y=0; y<N; y++) {
		    	for (int x=0; x<N; x++) {
		    		entropyMap[y][x]+=calculateEntropy(lc, y, x);
		    	}
		    }
//			long ep=System.currentTimeMillis()-st;
//			System.err.println("calculateEntropyMap "+ep);
		    return entropyMap;
		}
		double calculateEntropy(List<Candidate> lc, int y, int x) {
			int[] cnt=new int[M+1];
			int total=0;
		    for (Candidate c : lc) {
//		    	if (c.bDrop) continue;
		        int v=c.getOilAt(y, x);
		        cnt[v]++;
		        total++;
		    }
		    double entropy = 0;
		    for (int i=0; i<=M; i++) {
		        double p=(double)cnt[i]/total;
		        entropy+=calculateEntropy(p);
		    }
		    return entropy;
		}
		double[][] calculateEntropyMap4(List<Candidate> lc) {
		    double[][] entropyMap=new double[N][N];
		    for (int y=0; y<N-1; y++) {
		    	for (int x=0; x<N-1; x++) {
		    		entropyMap[y][x]+=calculateEntropy4(lc, y, x);
		    	}
		    }
		    return entropyMap;
		}
		double calculateEntropy4(List<Candidate> lc, int y, int x) {
			int[] cnt=new int[4*M+1];
			int total=0;
		    for (Candidate c : lc) {
//		    	if (c.bDrop) continue;
		        int v1=c.getOilAt(y, x);
		        int v2=c.getOilAt(y, x+1);
		        int v3=c.getOilAt(y+1, x);
		        int v4=c.getOilAt(y+1, x+1);
		        int v=v1+v2+v3+v4;
		        cnt[v]++;
		        total++;
		    }
		    double entropy = 0;
		    for (int i=0; i<=4*M; i++) {
		        double p=(double)cnt[i]/total;
		        entropy+=calculateEntropy(p);
		    }
		    return entropy;
		}
		double calculateEntropy(double p) {
		    if (p<=0 || p>=1) return 0; // 確信している状態
	        return -(p*Math.log(p)/Math.log(2));
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
		List<PointDouble> sort(double[][] aad) {
			List<PointDouble> list=new ArrayList<>();
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					list.add(new PointDouble(y, x, aad[y][x]));
				}
			}
			Collections.sort(list);
			return list;
		}
		double best(double[][] aad) {
			double maxv=aad[0][0];
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					maxv=Math.max(maxv, aad[y][x]);
				}
			}
			return maxv;
		}
		double sum(double[][] aad) {
			double sum=0;
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					sum+=aad[y][x];
				}
			}
			return sum;
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
		void update(Candidate c, Query q, double sigma) {
			int k=q.coords.size();
			LLC.update(k, q.result);
			int S=0;
			for (int pos : q.coords) {
				S+=c.getOilAt(pos/N, pos%N);
			}
//			c.lnLikelihood+=calcLnLikelihood(k, q.result, S, sigma);
			c.lnLikelihood+=LLC.get(k, q.result, S);
		}
		void update(List<Candidate> lc, Query q) {
			int k=q.coords.size();
			LLC.update(k, q.result);
//			double sigma=Math.sqrt(k*E*(1.0-E));
			for (Candidate c : lc) {
				// この候補における真の埋蔵量Sを計算
				int S=0;
//				int[][] count=new int[N][N];
//				for (int m=0; m<O.length; m++) {
//					int y=c.ap[m].y;
//					int x=c.ap[m].x;
//					Oil oil=O[m];
//					for (Point pt : oil.ap) {
//						count[y+pt.y][x+pt.x]++;
//					}
//				}
				for (int pos : q.coords) {
//					S+=count[pos/N][pos%N];
					S+=c.getOilAt(pos/N, pos%N);
				}
				// 対数尤度を加算
//				c.lnLikelihood+=calcLnLikelihood(k, q.result, S, sigma);
				c.lnLikelihood+=LLC.get(k, q.result, S);
			}
		}
		void debug(double[][] aad) {
			for (int x=0; x<N; x++) {
				System.err.printf("[%2d] ", x);
			}
			System.err.println();
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					System.err.printf("%.2f ", aad[y][x]);
				}
//				for (int j=0; j<N; j++) {
//					System.err.print(flag[i][j] ? "o":".");
//				}
				String s=String.format("%2d", y);
				System.err.println(":"+s);
			}
		}
	}
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
		@Override
		public String toString() {
			return map.keySet().toString();
		}
	}
	class Sequence {
		int last=1;
		int get() {
			return last++;
		}
	}
	Sequence ID;
	LnLikelihoodCache LLC;
	CandidateUtil CU;
	QueryUtil QU;
	int N;
	Points P;
	int M;
	Zhash Z;
	double E;
	Oil[] O;
	int V;
	Island I;
	int T;
	void init(Scanner sc) {
		ID=new Sequence();
		LLC=new LnLikelihoodCache();
		CU=new CandidateUtil();
		QU=new QueryUtil();
		N=sc.nextInt();
		P=new Points(N);
		M=sc.nextInt();
		Z=new Zhash();
		E=sc.nextDouble();
		O=new Oil[M];
		V=0;
		for (int i=0; i<M; i++) {
			int d=sc.nextInt();
			O[i]=new Oil(sc, i, d);
			V+=d;
		}
		I=new Island();
		T=2*N*N;
//		System.err.println("N="+N+",M="+M+",E="+E);
//		System.err.println("ao="+Arrays.toString(ao));
//		System.err.println("V="+V);
//		System.err.println("T="+T);
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
	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	static final String DS = "UDLR";
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
