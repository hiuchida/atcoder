import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = false;
	long end;
	boolean[] flag;
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		flag=new boolean[N];
		for (int i=0; i<N; i++) {
			int v1=og.calcL1(ar[i].st);
			int v2=og.calcL1(ar[i].ed);
			if (v1>400 || v2>400) flag[i]=true;
		}
		int n=50;
		Answer ans=new Answer(n);
		Point p0=og;
		for (int i=0; i<n; i++) {
			int minist=minvst(p0);
			ans.ai[i]=minist;
			flag[minist]=true;
			p0=ar[minist].st;
		}
		int idx=0;
		ans.ap[idx++]=og;
		Arrays.fill(flag, true);
		for (int i=0; i<n; i++) {
			flag[ans.ai[i]]=false;
		}
		p0=og;
		for (int i=0; i<n; i++) {
			int minist=minvst(p0);
			if (minist<0) break;
			flag[minist]=true;
			Point p1=ar[minist].st;
			ans.ap[idx++]=p1;
			p0=p1;
		}
		Arrays.fill(flag, true);
		for (int i=0; i<n; i++) {
			flag[ans.ai[i]]=false;
		}
		for (int i=0; i<n; i++) {
			int minied=minved(p0, ans);
			if (minied<0) break;
			flag[minied]=true;
			Point p1=ar[minied].ed;
			ans.ap[idx++]=p1;
			p0=p1;
		}
		ans.ap[idx++]=og;
		System.err.println("len="+ans.calcLen());
        int current_dist = ans.calcLen();
        
        int iteration = 0;
        for (int kk=0; kk<1000*1000; kk++) {
//        while (true) {
        	if (isTimeout()) break;
            int i=rand.nextInt(n)+n+1;
            int j=rand.nextInt(n)+n+1;
//            if (i==j) continue;
            if (i<j) ans.rotateLeft(i, j);
            else ans.rotateRight(j, i);
            int new_dist = ans.calcLen();

//            if (new_dist<=current_dist) {
//                if (new_dist < current_dist) {
//                    System.err.println("iteration: " + iteration + ", total distance: " + new_dist+" "+i+" "+j);
//                }
//                current_dist=new_dist;
//            } else {
//                if (i<j) ans.rotateRight(i, j);
//                else ans.rotateLeft(j, i);
//            }
//            iteration++;

            if (new_dist<=current_dist || isHot(current_dist-new_dist)) {
                current_dist=new_dist;
            } else {
                if (i<j) ans.rotateRight(i, j);
                else ans.rotateLeft(j, i);
            }
            iteration++;
            if (iteration % 100000 == 0) {
                System.err.println("iteration: " + iteration + ", total distance: " + current_dist);
            }
            double progress=(double)kk/1000/1000;
//            double progress = getProgress();
            updateTemperature(progress);
        }
		end=System.currentTimeMillis();
        System.err.println("--- Result ---");
		System.err.println("elaps         : " + (end-start));
        System.err.println("iteration     : " + iteration);
        System.err.println("total distance: " + current_dist);
		ans.print();
		if (!RELEASE) System.err.println("score="+ans.score+" length="+ans.length);
	}
	Point srch(Set<Point> set, Point p0) {
		int minv=Integer.MAX_VALUE;
		Point minp=null;
		for (Point p1 : set) {
			int v=p0.calcL1(p1);
			if (minv>v) {
				minv=v;
				minp=p1;
			}
		}
		return minp;
	}
	int minvsted(Point p0) {
		int minv=Integer.MAX_VALUE;
		int mini=-1;
		for (int i=0; i<N; i++) {
			if (flag[i]) continue;
			int v1=p0.calcL1(ar[i].st);
			int v2=p0.calcL1(ar[i].ed);
			int v=v1+v2;
			if (minv>v) {
				minv=v;
				mini=i;
			}
		}
		return mini;
	}
	int minvst(Point p0) {
		int minv=Integer.MAX_VALUE;
		int mini=-1;
		for (int i=0; i<N; i++) {
			if (flag[i]) continue;
			int v=p0.calcL1(ar[i].st);
			if (minv>v) {
				minv=v;
				mini=i;
			}
		}
		return mini;
	}
	int minvst0(Point p0) {
		int minv=Integer.MAX_VALUE;
		int mini=-1;
		for (int i=0; i<N; i++) {
			if (flag[i]) continue;
			int v=p0.calcL1(ar[i].st)+ar[i].dist;
			if (minv>v) {
				minv=v;
				mini=i;
			}
		}
		return mini;
	}
	int minved(Point p0, Answer ans) {
		int minv=Integer.MAX_VALUE;
		int mini=-1;
		for (int k=0; k<ans.ai.length; k++) {
			int i=ans.ai[k];
			if (flag[i]) continue;
			int v=p0.calcL1(ar[i].ed);
			if (minv>v) {
				minv=v;
				mini=i;
			}
		}
		return mini;
	}
	int minved0(Point p0) {
		int minv=Integer.MAX_VALUE;
		int mini=-1;
		for (int i=0; i<N; i++) {
			if (flag[i]) continue;
			int v=p0.calcL1(ar[i].ed)+ar[i].dist;
			if (minv>v) {
				minv=v;
				mini=i;
			}
		}
		return mini;
	}
	int minvsted(Point p0, Point pn) {
		int minv=Integer.MAX_VALUE;
		int mini1=-1;
		int mini2=-1;
		for (int i=0; i<N; i++) {
			if (flag[i]) continue;
			int v1=p0.calcL1(ar[i].st)+ar[i].dist;
			if (v1>minv) continue;
			for (int j=0; j<N; j++) {
				if (flag[j]) continue;
				int v2=pn.calcL1(ar[j].ed)+ar[j].dist;
				if (v1+v2>minv) continue;
				int v3=ar[i].ed.calcL1(ar[j].st);
				int v=v1+v2+v3;
				if (minv>v) {
					minv=v;
					mini1=i;
					mini2=j;
				}
			}
		}
		return mini1*N+mini2;
	}
	static class Answer {
		int m;
		int[] ai;
		int n;
		Point[] ap;
		int length;
		long score;
		Answer(int m) {
			this.m=m;
			this.ai=new int[m];
			this.n=(m+1)*2;
			this.ap=new Point[n];
		}
		int calcLen() {
			int length=0;
			Point p0=ap[0];
			for (int i=0; i<n; i++) {
				Point p1=ap[i];
				length+=p0.calcL1(p1);
				p0=p1;
			}
			return length;
		}
		void rotateLeft(int i, int j) {
			Point pt0=ap[i];
			for (int k=i; k<j; k++) {
				ap[k]=ap[k+1];
			}
			ap[j]=pt0;
		}
		void rotateRight(int i, int j) {
			Point pt0=ap[j];
			for (int k=j; k>i; k--) {
				ap[k]=ap[k-1];
			}
			ap[i]=pt0;
		}
		void print() {
			StringBuilder sb1=new StringBuilder();
			sb1.append(m);
			for (int i=0; i<m; i++) {
				sb1.append(" "+(ai[i]+1));
			}
			
			StringBuilder sb2=new StringBuilder();
			this.length=0;
			sb2.append(n);
			Point p0=ap[0];
			for (int i=0; i<n; i++) {
				Point p1=ap[i];
				sb2.append(" "+p1.y+" "+p1.x);
				length+=p0.calcL1(p1);
				p0=p1;
			}

			score=(long)(10*10000*10000/(1000+length));
			score=(score+5)/10;

			System.out.println(sb1.toString());
			System.out.println(sb2.toString());
		}
	}
	static class Rectangle {
		final Point st;
		final Point ed;
		final int dist;
		Rectangle(Point st, Point ed) {
			this.st=st;
			this.ed=ed;
			this.dist = st.calcL1(ed);
		}
		Rectangle(int y0, int x0, int y1, int x1) {
			this(new Point(y0, x0), new Point(y1, x1));
		}
		@Override
		public String toString() {
			return "(" + dist + ":" + st.y + "," + st.x + ":" + ed.y + "," + ed.x + ")";
		}
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
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
	final int N=1000;
	final Point og=new Point(400, 400);
	Rectangle[] ar;
	void init(Scanner sc) {
		ar=new Rectangle[N];
		for (int i=0; i<N; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int c=sc.nextInt();
			int d=sc.nextInt();
			ar[i]=new Rectangle(a, b, c, d);
		}
//		System.out.println(Arrays.toString(ar));
	}
	static Random rand=new Random(42);
	static long start;
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
		resetTemperature();
		new Main().solve();
	}
}
/*



*/
