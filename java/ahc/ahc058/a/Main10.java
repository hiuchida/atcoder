import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	Answer solve10() {
		Answer ans=new Answer();
		State10 sts=new State10();
		for (int t=0; t<T; t++) {
			int i=0;
			int j=-1;
			int maxi=-1;
			int maxj=-1;
			long maxv=-1;
			for (i=0; i<L; i++) {
				if (!sts.chk(i)) break;
			}
			if (i==L) i--;
			for (; i>0; i--) {
				Pair p=sts.srch(t, i);
				if (p.v>maxv) {
					maxi=i;
					maxj=p.i;
					maxv=p.v;
				}
			}
			Pair p=sts.srch0(t);
			if (p.v>maxv) {
				maxi=i;
				maxj=p.i;
				maxv=p.v;
			}
			if (maxi==-1) {
				i=-1;
				j=0;
				sts.act1(i, j);
				ans.ai[t]=i;
			} else {
				i=maxi;
				j=maxj;
				sts.act1(i, j);
				ans.ai[t]=i;
				ans.aj[t]=j;
			}
			sts.act2();
//			sts.print(t+1, i, j);
		}
		ans.score=sts.score();
		if (!RELEASE) System.err.println("Score="+ans.score);
		return ans;
	}
	Answer solve09() {
		Answer ans=new Answer();
		State09 sts=new State09();
		for (int t=0; t<T; t++) {
			int i=0;
			int j=-1;
			int maxi=-1;
			int maxj=-1;
			long maxv=-1;
			for (i=0; i<L; i++) {
				if (!sts.chk(i)) break;
			}
			if (i==L) i--;
			for (; i>0; i--) {
				Pair p=sts.srch(i);
				if (p.v>maxv) {
					maxi=i;
					maxj=p.i;
					maxv=p.v;
				}
			}
			Pair p=sts.srch0(t);
			if (p.v>maxv) {
				maxi=i;
				maxj=p.i;
				maxv=p.v;
			}
			if (maxi==-1) {
				i=-1;
				j=0;
				sts.act1(i, j);
				ans.ai[t]=i;
			} else {
				i=maxi;
				j=maxj;
				sts.act1(i, j);
				ans.ai[t]=i;
				ans.aj[t]=j;
			}
			sts.act2();
//			sts.print(t+1, i, j);
		}
		ans.score=sts.score();
		if (!RELEASE) System.err.println("Score="+ans.score);
		return ans;
	}
	Answer solve07() {
		Answer ans=new Answer();
		State07 sts=new State07();
		for (int t=0; t<T; t++) {
			boolean bDone=false;
			int i=0;
			int j=-1;
//			for (i=0; i<L; i++) {
//				if (sts.chknone(i)) {
//					j=sts.srchnone(i);
//					if (j>=0) {
//						sts.act1(i, j);
//						ans.ai[t]=i;
//						ans.aj[t]=j;
//						bDone=true;
//						break;
//					}
//				}
//			}
			if (!bDone) {
				for (i=0; i<L; i++) {
					if (!sts.chk(i)) break;
				}
				if (i==L) i--;
				for (; i>0; i--) {
					j=sts.srch(i);
					if (j>=0) {
						i=sts.srchlvl(i, j);
						sts.act1(i, j);
						ans.ai[t]=i;
						ans.aj[t]=j;
						bDone=true;
						break;
					}
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
		ans.score=sts.score();
		if (!RELEASE) System.err.println("Score="+ans.score);
		return ans;
	}
	Answer solve05() {
		Answer ans=new Answer();
		State05 sts=new State05();
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
		ans.score=sts.score();
		if (!RELEASE) System.err.println("Score="+ans.score);
		return ans;
	}
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		Answer ans05=solve05();
		Answer ans07=solve07();
		Answer ans09=solve09();
		Answer ans10=solve10();
		Answer ans=ans05;
		if (ans.score<ans07.score) ans=ans07;
		if (ans.score<ans09.score) ans=ans09;
		if (ans.score<ans10.score) ans=ans10;
		if (!RELEASE) System.err.println("Score="+ans.score);
		if (RELEASE) ans.print();
	}
	class State10 {
		long k;
		long[][] aac;
		long[][] aab;
		long[][] aap;
		State10() {
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
		boolean chknone(int ii) {
			int i=ii;
			for (int j=0; j<N; j++) {
				if (aap[i][j]==0) return true;
			}
			return false;
		}
		int srchnone(int ii) {
			int mini=-1;
			long minv=Long.MAX_VALUE;
			int i=ii;
			for (int j=0; j<N; j++) {
				if (aap[i][j]>0) continue;
				long c=aac[i][j]*(aap[i][j]+1);
				if (c>k) continue;
				if (c<minv) {
					mini=j;
					minv=c;
				}
			}
			return mini;
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
				if (aap[i][j]==0) {
					long c=aac[i][j]*(aap[i][j]+1);
					if (c>k) return false;
				}
			}
			return true;
		}
		int srchlvl(int ii, int jj) {
			int i;
			int j=jj;
			for (i=0; i<ii; i++) {
				if (aap[i][j]==0) {
					return i;
				}
			}
			return i;
		}
		Pair srch(int tt, int ii) {
			int maxi=-1;
			long maxv=-1;
			int i=ii;
			for (int j=0; j<N; j++) {
				long c=aac[i][j]*(aap[i][j]+1);
				if (c>k) continue;
				long v=AA[j]*aab[i][j];
				for (int p=0; p<i; p++) {
					v*=aap[p][j];
				}
				if (c>v*(T-tt)) continue;
//				if (!chk(i, j)) continue;
				if (v>maxv) {
					maxi=j;
					maxv=v;
				}
			}
			return new Pair(maxi, maxv);
		}
		Pair srch0(int tt) {
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
			return new Pair(maxi, maxv);
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
	class State09 {
		long k;
		long[][] aac;
		long[][] aab;
		long[][] aap;
		State09() {
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
		boolean chknone(int ii) {
			int i=ii;
			for (int j=0; j<N; j++) {
				if (aap[i][j]==0) return true;
			}
			return false;
		}
		int srchnone(int ii) {
			int mini=-1;
			long minv=Long.MAX_VALUE;
			int i=ii;
			for (int j=0; j<N; j++) {
				if (aap[i][j]>0) continue;
				long c=aac[i][j]*(aap[i][j]+1);
				if (c>k) continue;
				if (c<minv) {
					mini=j;
					minv=c;
				}
			}
			return mini;
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
				if (aap[i][j]==0) {
					long c=aac[i][j]*(aap[i][j]+1);
					if (c>k) return false;
				}
			}
			return true;
		}
		int srchlvl(int ii, int jj) {
			int i;
			int j=jj;
			for (i=0; i<ii; i++) {
				if (aap[i][j]==0) {
					return i;
				}
			}
			return i;
		}
		Pair srch(int ii) {
			int maxi=-1;
			long maxv=-1;
			int i=ii;
			for (int j=0; j<N; j++) {
				long c=aac[i][j]*(aap[i][j]+1);
				if (c>k) continue;
				long v=AA[j]*aab[i][j];
				for (int p=0; p<i; p++) {
					v*=aap[p][j];
				}
//				if (!chk(i, j)) continue;
				if (v>maxv) {
					maxi=j;
					maxv=v;
				}
			}
			return new Pair(maxi, maxv);
		}
		Pair srch0(int tt) {
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
			return new Pair(maxi, maxv);
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
	class Pair {
		int i;
		long v;
		Pair(int i, long v) {
			this.i=i;
			this.v=v;
		}
	}
	class State07 {
		long k;
		long[][] aac;
		long[][] aab;
		long[][] aap;
		State07() {
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
		boolean chknone(int ii) {
			int i=ii;
			for (int j=0; j<N; j++) {
				if (aap[i][j]==0) return true;
			}
			return false;
		}
		int srchnone(int ii) {
			int mini=-1;
			long minv=Long.MAX_VALUE;
			int i=ii;
			for (int j=0; j<N; j++) {
				if (aap[i][j]>0) continue;
				long c=aac[i][j]*(aap[i][j]+1);
				if (c>k) continue;
				if (c<minv) {
					mini=j;
					minv=c;
				}
			}
			return mini;
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
				if (aap[i][j]==0) {
					long c=aac[i][j]*(aap[i][j]+1);
					if (c>k) return false;
				}
			}
			return true;
		}
		int srchlvl(int ii, int jj) {
			int i;
			int j=jj;
			for (i=0; i<ii; i++) {
				if (aap[i][j]==0) {
					return i;
				}
			}
			return i;
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
	class State05 {
		long k;
		long[][] aac;
		long[][] aab;
		long[][] aap;
		State05() {
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
		long score;
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
