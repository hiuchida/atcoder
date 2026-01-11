import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		Answer ans=new Answer();
		State sts=new State();
		for (int t=0; t<T; t++) {
			int i=0;
			for (; i<L; i++) {
				if (!sts.chk(i)) break;
			}
			if (i==L) i--;
			int j=-1;
			boolean bDone=false;
			for (; i>0; i--) {
				j=sts.srch(i);
				if (j>=0) {
					sts.act1(i, j);
					ans.ai[t]=i;
					ans.aj[t]=j;
					bDone=true;
					break;
				}
			}
			if (!bDone) {
				j=sts.srch0(t);
				if (j==-1) {
					i=-1;
					j=0;
					sts.act1(i, j);
					ans.ai[t]=i;
				} else {
					i=0;
					sts.act1(i, j);
					ans.ai[t]=i;
					ans.aj[t]=j;
				}
			}
			sts.act2();
//			sts.print(t+1, i, j);
		}
		ans.print();
		if (!RELEASE) System.err.println("Score="+sts.score());
	}
	class State {
		long k;
		long[][] aac;
		long[][] aab;
		long[][] aap;
		State() {
			k=K;
			aac=new long[L][N];
			for (int i=0; i<L; i++) {
				for (int j=0; j<N; j++) {
					aac[i][j]=AAC[i][j];
				}
			}
			aab=new long[L][N];
			for (int i=0; i<L; i++) {
				for (int j=0; j<N; j++) {
					aab[i][j]=1;
				}
			}
			aap=new long[L][N];
			for (int i=0; i<L; i++) {
				for (int j=0; j<N; j++) {
					aap[i][j]=0;
				}
			}
		}
		boolean chk(int ii) {
			int i=ii;
			for (int j=0; j<N; j++) {
				if (aap[i][j]>0) return true;
			}
			return false;
		}
		boolean chk(int ii, int jj) {
			int j=jj;
			for (int i=ii-1; i>=0; i--) {
				if (aap[i][j]==0) return false;
			}
			return true;
		}
		int srch(int ii) {
			int maxi=-1;
			long maxv=-1;
			int i=ii;
			for (int j=0; j<N; j++) {
				long c=aac[i][j]*(aap[i][j]+1);
				long v=AA[j]*aab[i][j];
				if (c>k) continue;
				if (!chk(i, j)) continue;
				if (v>maxv) {
					maxi=j;
					maxv=v;
				}
			}
			return maxi;
		}
		int srch0(int tt) {
			int maxi=-1;
			long maxv=-1;
			int i=0;
			for (int j=0; j<N; j++) {
				long c=aac[i][j]*(aap[i][j]+1);
				long v=AA[j]*aab[i][j];
				if (c>k) continue;
				if (c>v*(T-tt)) continue;
				if (v>maxv) {
					maxi=j;
					maxv=v;
				}
			}
			return maxi;
		}
		void act1(int i, int j) {
			if (i==-1) return;
			long c=aac[i][j]*(aap[i][j]+1);
			if (c>k) {
				System.err.println("ERROR: c>k c="+c+" k="+k+" i="+i+" j="+j);
				System.exit(0);
			}
			k-=c;
			aap[i][j]++;
		}
		void act2() {
			for (int j=0; j<N; j++) {
				k+=AA[j]*aab[0][j]*aap[0][j];
			}
			for (int i=1; i<L; i++) {
				for (int j=0; j<N; j++) {
					aab[i-1][j]+=aab[i][j]*aap[i][j];
				}
			}
		}
		long score() {
			double v=Math.log(k)/Math.log(2);
			v*=1e5;
			long s=Math.round(v);
			return s;
		}
		void print(int tt, int ii, int jj) {
			System.out.println("t="+tt+" i="+ii+" j="+jj);
			System.out.println("k="+k);
			for (int i=0; i<L; i++) {
				System.out.println(Arrays.toString(aab[i]));
			}
			for (int i=0; i<L; i++) {
				System.out.println(Arrays.toString(aap[i]));
			}
			System.out.println("Score="+score());
		}
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
	int[] AA;
	long[][] AAC;
	void init(Scanner sc) {
		N=sc.nextInt();
		L=sc.nextInt();
		T=sc.nextInt();
		K=sc.nextInt();
		AA=new int[N];
		for (int i=0; i<N; i++) {
			int a=sc.nextInt();
			AA[i]=a;
		}
//		System.out.println(Arrays.toString(AA));
		AAC=new long[L][N];
		for (int i=0; i<L; i++) {
			for (int j=0; j<N; j++) {
				long c=sc.nextLong();
				AAC[i][j]=c;
			}
		}
//		for (int i=0; i<L; i++) {
//			System.out.println(Arrays.toString(AAC[i]));
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
