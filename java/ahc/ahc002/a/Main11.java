import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = false;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		init(sc);
//		System.out.println(sp);
//		for (int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(at[i]));
//		}
//		for (int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(ap[i]));
//		}
		Part part=new Part("");
		Part answer=part;
//		System.out.println(part);
		Deque<Part> que=new ArrayDeque<>();
//		PriorityQueue<Part> que=new PriorityQueue<>();
		que.addLast(part);
		long start=System.currentTimeMillis();
        final int time_limit=1700;
        int iteration=0;
		while (que.size()>0) {
			long end=System.currentTimeMillis();
			if (end-start>=time_limit) break;
			part=que.pollLast();
//			System.out.println(iteration+" "+part);
			Set<Integer> set=new HashSet<>();
			Point p0=sp;
			set.add(at[p0.y][p0.x]);
			for (int k=0; k<part.ans.length(); k++) {
				char ch=part.ans.charAt(k);
				int d=DS.indexOf(ch);
				if (canMove(p0, ch)) {
					Point p1=new Point(p0.y+DY[d], p0.x+DX[d]);
					p0=p1;
					set.add(at[p0.y][p0.x]);
				}
			}
			List<Part> lp=new ArrayList<>();
			for (int d=0; d<DS.length(); d++) {
				char ch=DS.charAt(d);
				if (canMove(p0, ch)) {
					Point p1=new Point(p0.y+DY[d], p0.x+DX[d]);
					if (!set.contains(at[p1.y][p1.x])) {
						Part part2=new Part(part.ans+ch);
						if (part2.score>0) {
							lp.add(part2);
						}
					}
				}
			}
			if (lp.size()==0) continue;
			Collections.sort(lp);
//			if (lp.size()==1) {
//				System.err.println("trace1 "+lp.get(0));
//			} else if (lp.size()==2) {
//				System.err.println("trace2 "+lp.get(0)+" "+lp.get(1));
//			} else if (lp.size()==3) {
//				System.err.println("trace3 "+lp.get(0)+" "+lp.get(1)+" "+lp.get(2));
//			} else if (lp.size()==4) {
//				System.err.println("trace4 "+lp.get(0)+" "+lp.get(1)+" "+lp.get(2)+" "+lp.get(3));
//			}
			for (int k=lp.size()-1; k>=0; k--) {
				que.addLast(lp.get(k));
			}
			if (answer.score<lp.get(0).score) {
				answer=lp.get(0);
                if (!RELEASE) System.err.println("iteration: "+iteration+", score: "+answer.score);
			}
			iteration++;
		}
		long end=System.currentTimeMillis();
		System.err.println("elaps:"+(end-start));
		String ans=answer.ans;
		int score=check(ans);
		if (!RELEASE) System.err.println("first "+ans+" "+"score="+score);
		System.out.println(ans);
	}
	static String resolve(String ans, int i, int j) {
		StringBuilder sb=new StringBuilder();
		Set<Integer> set=new HashSet<>();
		Point p0=sp;
		set.add(at[p0.y][p0.x]);
		for (int k=0; k<i; k++) {
			char ch=ans.charAt(k);
			sb.append(ch);
			int d=DS.indexOf(ch);
			if (canMove(p0, ch)) {
				Point p1=new Point(p0.y+DY[d], p0.x+DX[d]);
				p0=p1;
				set.add(at[p0.y][p0.x]);
			}
		}
		while (true) {
			int[] av=new int[DS.length()];
			Arrays.fill(av, -1);
			for (int d=0; d<DS.length(); d++) {
				char ch=DS.charAt(d);
				if (canMove(p0, ch)) {
					int y1=p0.y+DY[d];
					int x1=p0.x+DX[d];
					if (!set.contains(at[y1][x1])) {
						int v=ap[y1][x1];
						av[d]=v;
					}
				}
			}
			int[] ai=sort(av);
			if (av[0]<0) break;
			if (av[1]<0) break;
			if (j<=2 && av[2]<0) break;
			if (j<=3 && av[3]<0) break;
			int maxi=ai[j];
			sb.append(DS.charAt(maxi));
			p0=new Point(p0.y+DY[maxi], p0.x+DX[maxi]);
			set.add(at[p0.y][p0.x]);
			break;
		}
		while (true) {
			int[] av=new int[DS.length()];
			Arrays.fill(av, -1);
			for (int d=0; d<DS.length(); d++) {
				char ch=DS.charAt(d);
				if (canMove(p0, ch)) {
					int y1=p0.y+DY[d];
					int x1=p0.x+DX[d];
					if (!set.contains(at[y1][x1])) {
						int v=ap[y1][x1];
						av[d]=v;
					}
				}
			}
			int[] ai=sort(av);
			if (av[0]<0) break;
			int maxi=ai[0];
			sb.append(DS.charAt(maxi));
			p0=new Point(p0.y+DY[maxi], p0.x+DX[maxi]);
			set.add(at[p0.y][p0.x]);
		}
		return sb.toString();
	}
	static String solve() {
//		String ans="LDDDDDLLULLLUUULLLDDRDDLDDLDDDDDLLLDDLDDRDRDDRRRUURRURUUULULDDD";
//		return ans;
		StringBuilder sb=new StringBuilder();
		Set<Integer> set=new HashSet<>();
		Point p0=sp;
		set.add(at[p0.y][p0.x]);
		while (true) {
			int[] av=new int[DS.length()];
			Arrays.fill(av, -1);
			for (int d=0; d<DS.length(); d++) {
				char ch=DS.charAt(d);
				if (canMove(p0, ch)) {
					int y1=p0.y+DY[d];
					int x1=p0.x+DX[d];
					if (!set.contains(at[y1][x1])) {
						int v=ap[y1][x1];
						av[d]=v;
					}
				}
			}
			int[] ai=sort(av);
			if (av[0]<0) break;
			int maxi=ai[0];
			sb.append(DS.charAt(maxi));
			p0=new Point(p0.y+DY[maxi], p0.x+DX[maxi]);
			set.add(at[p0.y][p0.x]);
		}
		return sb.toString();
	}
	static int[] sort(int[] av) {
		int[] ai=new int[av.length];
		for (int i=0; i<av.length; i++) {
			ai[i]=i;
		}
		for (int i=0; i<av.length; i++) {
			for (int j=i+1; j<av.length; j++) {
				if (av[i]<av[j]) {
					int v=av[i];
					av[i]=av[j];
					av[j]=v;
					v=ai[i];
					ai[i]=ai[j];
					ai[j]=v;
				}
			}
		}
		return ai;
	}
	static int check(String ans) {
		Set<Integer> set=new HashSet<>();
		Point p0=sp;
		set.add(at[p0.y][p0.x]);
		int v=ap[p0.y][p0.x];
		int sum=v;
		if (DEBUG) System.out.println(0+" "+p0+" "+v+" "+sum);
		for (int i=0; i<ans.length(); i++) {
			char ch=ans.charAt(i);
			int d=DS.indexOf(ch);
			if (canMove(p0, ch)) {
				Point p1=new Point(p0.y+DY[d], p0.x+DX[d]);
				if (set.contains(at[p1.y][p1.x])) {
					if (!RELEASE) System.err.println("debug "+ans+" "+i+" "+ch+" "+p0);
					return -1;
				}
				p0=p1;
				set.add(at[p0.y][p0.x]);
				v=ap[p0.y][p0.x];
				sum+=v;
				if (DEBUG) System.out.println((i+1)+" "+p0+" "+v+" "+sum);
			} else {
				if (!RELEASE) System.err.println("debug "+ans+" "+i+" "+ch+" "+p0);
				return -1;
			}
		}
		return sum;
	}
	static boolean canMove(Point pt, char d) {
		switch (d) {
		case 'U':
			if (pt.y==0) return false;
			return at[pt.y][pt.x]!=at[pt.y-1][pt.x];
		case 'D':
			if (pt.y==N-1) return false;
			return at[pt.y][pt.x]!=at[pt.y+1][pt.x];
		case 'L':
			if (pt.x==0) return false;
			return at[pt.y][pt.x]!=at[pt.y][pt.x-1];
		case 'R':
			if (pt.x==N-1) return false;
			return at[pt.y][pt.x]!=at[pt.y][pt.x+1];
		}
		return false;
	}
	static class Part implements Comparable<Part> {
		String ans;
		int score;
		Part(String ans) {
			this.ans=ans;
			this.score=check(ans);
		}
		@Override
		public int compareTo(Part that) {
			return -Integer.compare(this.score, that.score);
		}
		@Override
		public String toString() {
			return "score="+score;
//			return "score="+score+",ans="+ans;
		}
	}
	static final int N=50;
	static Point sp;
	static int[][] at;
	static int[][] ap;
	static void init(Scanner sc) {
		int si=sc.nextInt();
		int sj=sc.nextInt();
		sp=new Point(si, sj);
		at=new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				at[i][j]=sc.nextInt();
			}
		}
		ap=new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				ap[i][j]=sc.nextInt();
			}
		}
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
