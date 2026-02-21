import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = false;
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
	int lastid=1;
	int seq() {
		return lastid++;
	}
	Set<Long> sethash=new HashSet<>();
	boolean check(Candidate c) {
		if (sethash.contains(c.hash)) {
//			System.err.println("check conflict hash="+c.hash);
			return false;
		}
		sethash.add(c.hash);
		return true;
	}
	class Candidate implements Comparable<Candidate> {
		boolean bDrop;
		int id;
		Point[] ap;
		long hash;
		double lnLikelihood;
		double likelihood;
		int[][] cntOil;
		Candidate(Point pt0, Point pt1) {
			ap=new Point[2];
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
				hash^=zhash[m][y][x];
				Oil oil=ao[m];
				for (Point pt : oil.ap) {
					cntOil[y+pt.y][x+pt.x]++;
				}
			}
		}
		void recalc(List<Query> history) {
			for (Query q : history) {
				int k=q.coords.size();
				double sigma=Math.sqrt(k*E*(1.0-E));
				update(this, q, sigma);
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
	Set<Long> sethash2=new HashSet<>();
	boolean checkq(Query q) {
		if (sethash2.contains(q.hash)) {
//			System.err.println("check conflict hash2="+q.hash);
			return false;
		}
		sethash2.add(q.hash);
		return true;
	}
	class Query {
		List<Integer> coords=new ArrayList<>();
		long hash;
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
		Query(double[][] aad) {
			double cond=0.9;
			while (true) {
				for (int i=0; i<N*N; i++) {
					if (aad[i/N][i%N]<cond) {
						if (rand.nextDouble()<0.25) coords.add(i);
					} else {
						if (rand.nextDouble()<0.75) coords.add(i);
					}
				}
				if (coords.size()>0) break;
				cond-=0.1;
			}
			init();
		}
		void init() {
			hash=0;
			for (int v : coords) {
				hash^=zhash[0][v/N][v%N];
			}
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
			lc.get(i).id=seq();
		}
		return lc;
	}
	List<Candidate> generate(int cnt) {
		List<Candidate> lc=new ArrayList<>();
		while (lc.size()<cnt) {
			Point[] ap=new Point[M];
			for (int m=0; m<M; m++) {
				int y=rand.nextInt(N-ao[m].maxy);
				int x=rand.nextInt(N-ao[m].maxx);
				Point pt=P.get(y, x);
				ap[m]=pt;
			}
			Candidate c=new Candidate(ap);
			if (check(c)) {
				lc.add(c);
			}
		}
		for (int i=0; i<lc.size(); i++) {
			lc.get(i).id=seq();
		}
		return lc;
	}
	void regenerate(List<Candidate> lc, List<Query> history) {
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
//		System.err.println(lchoise);
		int last=lc.size();
		int mid=last/M/4;
		List<Candidate> add=new ArrayList<>();
		double lnLikelihood=lc.get(mid).lnLikelihood;
		for (int i=0; i<mid; i++) {
			Candidate best=lc.get(i);
			for (int m=0; m<M; m++) {
				int maxy=ao[m].maxy;
				int maxx=ao[m].maxx;
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
							c.id=seq();
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
				int maxy=ao[m].maxy;
				int maxx=ao[m].maxx;
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
						c.id=seq();
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
//		System.err.println("regenerate:old="+last+" del="+del+" add="+add.size()+" new="+lc.size());
//		for (Candidate c : lc) {
//			c.lnLikelihood=0;
//		}
//		System.err.println("regenerate:"+last+" "+(last-last/M/4)+" "+add.size());
	}
	void normalize(List<Candidate> lc) {
		//対数尤度から尤度の変換、尤度の正規化
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
	double[][] calculateBinaryEntropyMap(List<Candidate> lc) {
	    double[][] entropyMap=new double[N][N];
	    double totalWeight=0;
	    // 1. 各マスの「油がある期待値」を計算
	    double[][] probMap=new double[N][N];
	    for (Candidate c : lc) {
	    	double weight=c.likelihood;
	        totalWeight+=weight;
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
	                if (c.hasOilAt(y, x)) {
	                    probMap[y][x]+=weight;
	                }
	            }
	        }
	    }
//	    System.err.println("totalWeight="+totalWeight);
//	    debug(probMap);
	    // 2. エントロピーに変換
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
	            double p=probMap[y][x]/totalWeight;
	            entropyMap[y][x]=calculateBinaryEntropy(p);
	        }
	    }
	    return entropyMap;
	}
	double calculateBinaryEntropy(double p) {
	    if (p<=0 || p>=1) return 0; // 確信している状態
	    return -(p*Math.log(p)+(1-p)*Math.log(1-p))/Math.log(2);
	}
	double[][] calculateEntropyMap(List<Candidate> lc) {
	    double[][] entropyMap=new double[N][N];
	    for (int y=0; y<N; y++) {
	    	for (int x=0; x<N; x++) {
	    		entropyMap[y][x]+=calculateEntropy(lc, y, x);
	    	}
	    }
	    return entropyMap;
	}
	double calculateEntropy(List<Candidate> lc, int y, int x) {
		int[] cnt=new int[M+1];
		int total=0;
	    for (Candidate c : lc) {
	    	if (c.bDrop) continue;
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
	void update(Candidate c, Query q, double sigma) {
		// この候補における真の埋蔵量Sを計算
		int k=q.coords.size();
		int S=0;
		for (int pos : q.coords) {
			S+=c.getOilAt(pos/N, pos%N);
		}
		c.lnLikelihood+=calcLnLikelihood(k, q.result, S, sigma);
	}
	void update(List<Candidate> lc, Query q) {
		int k=q.coords.size();
		double sigma=Math.sqrt(k*E*(1.0-E));
		for (Candidate c : lc) {
			update(c, q, sigma);
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
//			for (int j=0; j<N; j++) {
//				System.err.print(flag[i][j] ? "o":".");
//			}
			String s=String.format("%2d", y);
			System.err.println(":"+s);
		}
	}
	void solve3x(Scanner sc) {
		int T=2*N*N;
//		System.err.println("T="+T);
		double cost=0;
		List<Candidate> lc=generate(1000);
		List<Query> history=new ArrayList<>();
		for (int t=0; t<T; t++) {
			normalize(lc);
//			System.err.println(lc);
			double ess=ess(lc);
			Candidate best=best(lc);
			double maxl=best.likelihood;
			if (!RELEASE) System.err.printf("%3d:%3d best=%04d maxl=%.4f ess=%.1f ", (t+1), lc.size(), best.id, maxl, ess);
			System.err.println();
			double[][] aad=calculateBinaryEntropyMap(lc);
			double maxe=best(aad);
			double sume=sum(aad);
			double rate=sume/N/N;
			System.err.printf("calculateBinaryEntropyMap maxe=%.4f sume=%.4f rate=%.1f", maxe, sume, rate);
			System.err.println();
			debug(aad);
			double[][] aad2=calculateEntropyMap(lc);
			double maxe2=best(aad2);
			double sume2=sum(aad2);
			double rate2=sume2/N/N;
			System.err.printf("calculateEntropyMap maxe=%.4f sume=%.4f rate=%.1f", maxe2, sume2, rate2);
			System.err.println();
			debug(aad2);
			if (maxl>0.8) {
				Answer a=new Answer(best);
				a.print();
				int rc=sc.nextInt();
				a.result=rc;
				if (!RELEASE) System.err.println(a);
				if (rc==1) break;
				cost++;
				best.lnLikelihood=-1e100;
				regenerate(lc, history);
			} else {
				Query q=new Query(aad);
				while (!checkq(q)) {
					q=new Query(aad);
				}
//				Query q=new Query();
				q.print();
				int rc=sc.nextInt();
				q.result=rc;
				history.add(q);
				if (!RELEASE) System.err.println(q);
				update(lc, q);
				cost+=1.0/Math.sqrt(q.coords.size());
//				if (ess<lc.size()/10) {
				if ((t+1)%10==0) {
					regenerate(lc, history);
				}
			}
		}
		long end=System.currentTimeMillis();
		long score=Math.round(1e6*cost);
		System.err.println("--- Result ---");
		System.err.println("elaps    : " + (end-start));
		System.err.println("iteration: " + iteration);
		System.err.println("score    : " + score);
	}
	void solve2x(Scanner sc) {
		int T=2*N*N;
//		System.err.println("T="+T);
		double cost=0;
		List<Candidate> lc=generate();
		for (int t=0; t<T; t++) {
			normalize(lc);
//			System.err.println(lc);
			double ess=ess(lc);
			Candidate best=best(lc);
			double maxl=best.likelihood;
			if (!RELEASE) System.err.printf("%3d:%3d best=%04d maxl=%.4f ess=%.1f ", (t+1), lc.size(), best.id, maxl, ess);
			System.err.println();
			double[][] aad=calculateBinaryEntropyMap(lc);
			double maxe=best(aad);
			double sume=sum(aad);
			double rate=sume/N/N;
//			System.err.printf("calculateBinaryEntropyMap maxe=%.4f sume=%.4f rate=%.1f", maxe, sume, rate);
//			System.err.println();
//			debug(aad);
			double[][] aad2=calculateEntropyMap(lc);
			double maxe2=best(aad2);
			double sume2=sum(aad2);
			double rate2=sume2/N/N;
			System.err.printf("calculateEntropyMap maxe=%.4f sume=%.4f rate=%.1f", maxe2, sume2, rate2);
			System.err.println();
			debug(aad2);
			if (maxl>0.8) {
				Answer a=new Answer(best);
				a.print();
				int rc=sc.nextInt();
				a.result=rc;
				if (!RELEASE) System.err.println(a);
				if (rc==1) break;
				cost++;
				best.lnLikelihood=-1e100;
				best.bDrop=true;
			} else {
				Query q=new Query(aad);
//				Query q=new Query();
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
//		if (M==2) solve2x(sc);
//		else if (M==3 || M==4) solve3x(sc);
//		else solve12(sc);
		solve12(sc);
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
	int N;
	Points P;
	int M;
	double E;
	Oil[] ao;
	int V;
	long[][][] zhash;
	void init(Scanner sc) {
		N=sc.nextInt();
		P=new Points(N);
		M=sc.nextInt();
		E=sc.nextDouble();
		ao=new Oil[M];
		V=0;
		for (int i=0; i<M; i++) {
			int d=sc.nextInt();
			ao[i]=new Oil(sc, i, d);
			V+=d;
		}
		zhash=new long[M][N][N];
		for (int m=0; m<M; m++) {
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					zhash[m][y][x]=rand.nextLong();
				}
			}
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
