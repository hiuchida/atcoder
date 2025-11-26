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
		String ans="";
		Point p0=sp;
		Part part=new Part(ans);
		answer=part;
		start=System.currentTimeMillis();
		boolean[] flag=new boolean[N*N];
		dfs(ans, part.score, p0, flag);
		long end=System.currentTimeMillis();
		System.err.println("elaps:"+(end-start));
		ans=answer.ans;
		int score=check(ans);
		if (!RELEASE) System.err.println("first "+ans+" "+"score="+score);
		System.out.println(ans);
	}
	static long start;
	static int iteration=0;
	static final int time_limit=1700;
	static Part answer=null;
	static void dfs(String ans, int score, Point p0, boolean[] flag) {
		long end=System.currentTimeMillis();
		if (end-start>=time_limit) return;
		iteration++;
		MoveList ml=am[p0.y][p0.x];
		for (Move m : ml.lm) {
			if (!flag[at[m.pt.y][m.pt.x]]) {
				String ans2=ans+m.d;
				int score2=score+m.score;
				if (answer.score<score2) {
					answer=new Part(ans2, score2);
	                if (!RELEASE) System.err.println("iteration: "+iteration+", score: "+answer.score);
				}
				flag[at[m.pt.y][m.pt.x]]=true;
				dfs(ans2, score2, m.pt, flag);
				flag[at[m.pt.y][m.pt.x]]=false;
			}
		}
	}
	static int check(String ans) {
//		Set<Integer> set=new HashSet<>();
		boolean[] flag=new boolean[N*N];
		Point p0=sp;
//		set.add(at[p0.y][p0.x]);
		flag[at[p0.y][p0.x]]=true;
		int v=ap[p0.y][p0.x];
		int sum=v;
		if (DEBUG) System.out.println(0+" "+p0+" "+v+" "+sum);
		for (int i=0; i<ans.length(); i++) {
			char ch=ans.charAt(i);
			int d=DS.indexOf(ch);
			if (canMove(p0, ch)) {
				Point p1=new Point(p0.y+DY[d], p0.x+DX[d]);
//				if (set.contains(at[p1.y][p1.x])) {
				if (flag[at[p1.y][p1.x]]) {
					if (!RELEASE) System.err.println("debug1 "+ans+" "+i+" "+ch+" "+p0);
					return -1;
				}
				p0=p1;
//				set.add(at[p0.y][p0.x]);
				flag[at[p0.y][p0.x]]=true;
				v=ap[p0.y][p0.x];
				sum+=v;
				if (DEBUG) System.out.println((i+1)+" "+p0+" "+v+" "+sum);
			} else {
				if (!RELEASE) System.err.println("debug2 "+ans+" "+i+" "+ch+" "+p0);
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
		Part(String ans, int score) {
			this.ans=ans;
			this.score=score;
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
	static class MoveList {
		List<Move> lm=new ArrayList<>();
		@Override
		public String toString() {
			return lm.toString();
		}
	}
	static class Move implements Comparable<Move> {
		Point pt;
		char d;
		int score;
		Move(Point pt, char d, int score) {
			this.pt=pt;
			this.d=d;
			this.score=score;
		}
		@Override
		public int compareTo(Move that) {
			return -Integer.compare(this.score, that.score);
		}
		@Override
		public String toString() {
			return "("+pt.y+" "+pt.x+" "+d+" "+score+")";
		}
	}
	static final int N=50;
	static Point sp;
	static int[][] at;
	static int[][] ap;
	static MoveList[][] am;
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
		am=new MoveList[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				MoveList ml=new MoveList();
				am[i][j]=ml;
				Point p1=new Point(i, j);
				for (int d=0; d<DS.length(); d++) {
					char ch=DS.charAt(d);
					if (canMove(p1, ch)) {
						Point p2=new Point(p1.y+DY[d], p1.x+DX[d]);
						int v=ap[p2.y][p2.x];
						ml.lm.add(new Move(p2, ch, v));
					}
				}
				Collections.sort(ml.lm);
//				System.out.println(p1+" "+ml);
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
