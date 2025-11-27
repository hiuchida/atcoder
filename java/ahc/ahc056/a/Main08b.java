import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	static int n;
	static int k;
	static int t;
	static MazeWall mw;
	static Point[] ap;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		k=sc.nextInt();
		t=sc.nextInt();
		mw=new MazeWall(n);
		mw.init(sc);
		ap=new Point[k];
		for (int i=0; i<k; i++) {
			int y=sc.nextInt();
			int x=sc.nextInt();
			ap[i]=new Point(y, x);
		}
		Field fld=new Field();
//		fld.fillUnique();
//		int c=fld.maxCol;
		List<Route> lroute=new ArrayList<>();
		for (int j=0; j<k-1; j++) {
			lroute.add(new Route(ap[j], ap[j+1]));
		}
		lroute=merge(lroute);
//		for (Route r : lroute) {
//			System.out.println(r);
//		}
		List<Direction> ldir=new ArrayList<>();
		for (int j=0; j<lroute.size(); j++) {
			Direction dir=new Direction(lroute.get(j), j==lroute.size()-1);
			ldir.add(dir);
//			System.out.println("["+j+"]");
//			System.out.print(dir);
		}
		Direction dir=new Direction(lroute);
//		System.out.println("[root]");
//		System.out.print(dir);
		dir.attach(fld);
		attach(fld, dir, ldir);
		RuleMgr rulemgr=new RuleMgr();
		for (int j=0; j<lroute.size(); j++) {
			Route r=lroute.get(j);
			for (int i=0; i<r.size()-1; i++) {
				Step step=r.get(i);
				Point p0=step.pt;
				char d=step.d;
//				Step step2=r.get(i+1);
//				Point pn=step2.pt;
//				System.out.println(p0+" "+pn+" "+d);
				if (rulemgr.size()>=t) break;
				int color=fld.getColor(p0);
				int state2=j;
				if (i==r.size()-2 && j!=lroute.size()-1) {
					state2++;
				}
				Rule rule=new Rule(color, j, color, state2, d);
				rulemgr.add(rule);
			}
		}
		fld.fillZero();
		Answer ans=new Answer(lroute, rulemgr, fld);
		ans.print();
		if (!RELEASE) System.err.println("Score="+ans.getScore());
	}
	static void attach(Field fld, Direction dir, List<Direction> ldir) {
		for (int idx1=0; idx1<n*n; idx1++) {
			int y1=idx1/n;
			int x1=idx1%n;
			int v1=fld.as[y1][x1];
			if (v1>=0) continue;
//			System.out.print("check "+y1+" "+x1+":");
			for (int idx2=idx1+1; idx2<n*n; idx2++) {
				int y2=idx2/n;
				int x2=idx2%n;
				int v2=fld.as[y2][x2];
				if (v2>=0) continue;
//				System.out.print(" "+y2+" "+x2+" / ");
				boolean ng=false;
				for (int z=0; z<ldir.size(); z++) {
					Direction d1=ldir.get(z);
					if (!check(d1.ad[y1][x1], d1.ad[y2][x2])) {
						ng=true;
						break;
					}
				}
				if (!ng) {
					boolean same3=false;
					for (int idx3=idx2+1; idx3<n*n; idx3++) {
						int y3=idx3/n;
						int x3=idx3%n;
						int v3=fld.as[y3][x3];
						if (v3>=0) continue;
						boolean ng3=false;
						for (int z=0; z<ldir.size(); z++) {
							Direction d1=ldir.get(z);
							if (!check(d1.ad[y1][x1], d1.ad[y3][x3])) {
								ng3=true;
								break;
							}
							if (!check(d1.ad[y2][x2], d1.ad[y3][x3])) {
								ng3=true;
								break;
							}
						}
						if (!ng3) {
							v1=Color.next();
							fld.as[y1][x1]=v1;
							fld.as[y2][x2]=v1;
							fld.as[y3][x3]=v1;
							same3=true;
//							System.out.println("attach "+y1+" "+x1+" + "+y2+" "+x2+" + "+y3+" "+x3);
							break;
						}
					}
					if (!same3) {
						v1=Color.next();
						fld.as[y1][x1]=v1;
						fld.as[y2][x2]=v1;
//						System.out.println("attach "+y1+" "+x1+" + "+y2+" "+x2);
					}
					break;
				}
			}
//			System.out.println();
		}
	}
	static boolean check(char c1, char c2) {
		if (c1=='.' || c2=='.') return true;
		if (c1==c2) return true;
		return false;
	}
	static List<Route> merge(List<Route> lroute) {
		List<Route> ans=new ArrayList<>();
		ans.add(lroute.remove(0));
		while (lroute.size()>0) {
			Route r0=ans.get(ans.size()-1);
			Route r1=lroute.remove(0);
			if (r0.isCross(r1)) {
				ans.add(r1);
			} else {
				ans.remove(ans.size()-1);
				r0.connect(r1);
				ans.add(r0);
			}
		}
		return ans;
	}
	static String bfs(Point p0, Point p1) {
		int[][] flag=new int[n][n];
		flag[p0.y][p0.x]=1;
		Deque<Point> que=new ArrayDeque<>();
		que.offer(p0);
		while (que.size()>0) {
			Point pt=que.poll();
			if (pt.equals(p1)) {
				break;
			}
			int step=flag[pt.y][pt.x];
			if (mw.canMove(pt, 'U') && pt.y>0 && flag[pt.y-1][pt.x]==0) {
				flag[pt.y-1][pt.x]=step+1;
				que.offer(new Point(pt.y-1, pt.x));
			}
			if (mw.canMove(pt, 'D') && pt.y<n-1 && flag[pt.y+1][pt.x]==0) {
				flag[pt.y+1][pt.x]=step+1;
				que.offer(new Point(pt.y+1, pt.x));
			}
			if (mw.canMove(pt, 'L') && pt.x>0 && flag[pt.y][pt.x-1]==0) {
				flag[pt.y][pt.x-1]=step+1;
				que.offer(new Point(pt.y, pt.x-1));
			}
			if (mw.canMove(pt, 'R') && pt.x<n-1 && flag[pt.y][pt.x+1]==0) {
				flag[pt.y][pt.x+1]=step+1;
				que.offer(new Point(pt.y, pt.x+1));
			}
		}
//		for (int y=0; y<n; y++) {
//			for (int x=0; x<n; x++) {
//				System.out.printf("%2d ", flag[y][x]);
//			}
//			System.out.println();
//		}
		StringBuilder sb=new StringBuilder();
		Point pt=p1;
		int step=flag[pt.y][pt.x];
		while (true) {
//			System.out.print(step+" "+pt+" ");
			if (pt.equals(p0)) {
//				System.out.println();
				break;
			}
			if (mw.canMove(pt, 'U') && pt.y>0 && flag[pt.y-1][pt.x]==step-1) {
				pt=new Point(pt.y-1, pt.x);
				step--;
				sb.append("D");
//				System.out.println("D");
			} else if (mw.canMove(pt, 'D') && pt.y<n-1 && flag[pt.y+1][pt.x]==step-1) {
				pt=new Point(pt.y+1, pt.x);
				step--;
				sb.append("U");
//				System.out.println("U");
			} else if (mw.canMove(pt, 'L') && pt.x>0 && flag[pt.y][pt.x-1]==step-1) {
				pt=new Point(pt.y, pt.x-1);
				step--;
				sb.append("R");
//				System.out.println("R");
			} else if (mw.canMove(pt, 'R') && pt.x<n-1 && flag[pt.y][pt.x+1]==step-1) {
				pt=new Point(pt.y, pt.x+1);
				step--;
				sb.append("L");
//				System.out.println("L");
			}
		}
		sb.reverse();
//		System.out.println(sb);
		return sb.toString();
	}
	static class Answer {
		int c;
		int q;
		int m;
		Field fld;
		RuleMgr rulemgr;
		Answer(List<Route> lroute, RuleMgr rulemgr, Field fld) {
			this.c=Color.maxCol;
			this.q=lroute.size();
			this.m=rulemgr.size();
			this.fld=fld;
			this.rulemgr=rulemgr;
		}
		int getScore() {
			return c+q;
		}
		void print() {
			System.out.println(c+" "+q+" "+m);
			System.out.print(fld.toString());
			for (Rule r : rulemgr.lrule) {
				System.out.println(r);
			}
		}
	}
	static class Direction {
		char[][] ad=new char[n][n];
		Direction(Route r, boolean bLast) {
			for (int y=0; y<n; y++) {
				Arrays.fill(ad[y], '.');
			}
			for (int i=0; i<r.size()-1; i++) {
				Step step=r.get(i);
				Point p0=step.pt;
				char d=step.d;
				if (i==r.size()-2 && !bLast) {
					d='S';
				}
				switch (ad[p0.y][p0.x]) {
				case '.':
					ad[p0.y][p0.x]=d;
					break;
				case 'U':
				case 'D':
				case 'R':
				case 'L':
					ad[p0.y][p0.x]='S';
					break;
				case 'S':
					break;
				}
			}
		}
		Direction(List<Route> lroute) {
			for (int y=0; y<n; y++) {
				Arrays.fill(ad[y], '.');
			}
			for (int j=0; j<lroute.size(); j++) {
				Route r=lroute.get(j);
				for (int i=0; i<r.size()-1; i++) {
					Step step=r.get(i);
					Point p0=step.pt;
					char d=step.d;
					if (i==r.size()-2 && j!=lroute.size()-1) {
						d='S';
					}
					switch (ad[p0.y][p0.x]) {
					case '.':
						ad[p0.y][p0.x]=d;
						break;
					case 'U':
					case 'D':
					case 'R':
					case 'L':
					case 'C':
						if (d!='S') d='C';
						ad[p0.y][p0.x]=d;
						break;
					case 'S':
						break;
					}
				}
			}
		}
		void attach(Field fld) {
			Map<Character,Integer> map=new HashMap<>();
			for (int y=0; y<n; y++) {
				for (int x=0; x<n; x++) {
					char ch=ad[y][x];
					switch (ch) {
					case '.':
					case 'C':
					case 'S':
						break;
					case 'U':
					case 'D':
					case 'R':
					case 'L':
						Integer val=map.get(ch);
						if (val==null) {
							val=Color.next();
							map.put(ch, val);
						}
						break;
					}
				}
			}
			for (int y=0; y<n; y++) {
				for (int x=0; x<n; x++) {
					char ch=ad[y][x];
					switch (ch) {
					case '.':
						fld.as[y][x]=0;
						break;
					case 'U':
					case 'D':
					case 'R':
					case 'L':
						Integer val=map.get(ch);
						fld.as[y][x]=val;
						break;
					case 'S':
						fld.as[y][x]=Color.next();
						break;
					case 'C':
						break;
					}
				}
			}
		}
		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder();
			for (int y=0; y<n; y++) {
				for (int x=0; x<n; x++) {
					if (x>0) sb.append(" ");
					sb.append(ad[y][x]);
				}
				sb.append(getCRLF());
			}
			return sb.toString();
		}
	}
	static class Field {
		int[][] as=new int[n][n];
		int maxCol=0;
		Field() {
			for (int y=0; y<n; y++) {
				Arrays.fill(as[y], -1);
			}
		}
		void fillUnique() {
			for (int y=0; y<n; y++) {
				for (int x=0; x<n; x++) {
					int col=y*n+x;
					as[y][x]=col;
					maxCol=Math.max(maxCol, col+1);
				}
			}
		}
		void fillZero() {
			for (int y=0; y<n; y++) {
				for (int x=0; x<n; x++) {
					if (as[y][x]<0) as[y][x]=0;
				}
			}
		}
		int getColor(Point pt) {
			int col=as[pt.y][pt.x];
			if (col<0) {
				col=Color.next();
				as[pt.y][pt.x]=col;
			}
			return col;
		}
		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder();
			for (int y=0; y<n; y++) {
				for (int x=0; x<n; x++) {
					if (x>0) sb.append(" ");
					sb.append(as[y][x]);
				}
				sb.append(getCRLF());
			}
			return sb.toString();
		}
	}
	static class Color {
		static int maxCol=0;
		static void reset() {
			maxCol=0;
		}
		static int next() {
			return maxCol++;
		}
	}
	static class Route {
		List<Step> lstep=new ArrayList<>();
		Route(Point p0, Point p1) {
			String route=bfs(p0, p1);
			for (int i=0; i<route.length(); i++) {
				char d=route.charAt(i);
				Point pn=null;
				if (d=='U') {
					pn=new Point(p0.y-1, p0.x);
				} else if (d=='D') {
					pn=new Point(p0.y+1, p0.x);
				} else if (d=='L') {
					pn=new Point(p0.y, p0.x-1);
				} else if (d=='R') {
					pn=new Point(p0.y, p0.x+1);
				}
//				System.out.println(p0+" "+pn);
				lstep.add(new Step(p0, d));
				p0=pn;
			}
			lstep.add(new Step(p1, 'S'));
		}
		int size() {
			return lstep.size();
		}
		Step get(int idx) {
			return lstep.get(idx);
		}
		boolean isCross(Route that) {
			Set<Point> set=new HashSet<>();
			for (Step step : that.lstep) {
				set.add(step.pt);
			}
			for (int i=0; i<lstep.size()-1; i++) {
				Step step=lstep.get(i);
				if (set.contains(step.pt)) return true; 
			}
			return false;
		}
		void connect(Route that) {
			lstep.remove(lstep.size()-1);
			for (Step step : that.lstep) {
				lstep.add(step);
			}
		}
		@Override
		public String toString() {
			return lstep.toString();
		}
	}
	static class Step {
		Point pt;
		char d;
		Step(Point pt, char d) {
			this.pt=pt;
			this.d=d;
		}
		@Override
		public String toString() {
			return "("+pt.y+" "+pt.x+" "+d+")";
		}
	}
	static class RuleMgr {
		List<Rule> lrule=new ArrayList<>();
		Set<Rule> srule=new HashSet<>();
		int size() {
			return lrule.size();
		}
		void add(Rule rule) {
			if (!srule.contains(rule)) {
				lrule.add(rule);
				srule.add(rule);
			}
		}
	}
	static class Rule {
		int c;
		int q;
		int a;
		int s;
		char d;
		Rule(int c, int q, int a, int s, char d) {
			this.c=c;
			this.q=q;
			this.a=a;
			this.s=s;
			this.d=d;
		}
		@Override
		public int hashCode() {
			return Objects.hash(c, q);
		}
		@Override
		public boolean equals(Object obj) {
			Rule that = (Rule) obj;
			return this.c == that.c && this.q == that.q;
		}
		@Override
		public String toString() {
			return c+" "+q+" "+a+" "+s+" "+d;
		}
	}
	static class MazeWall {
		int n;
		boolean[][] av;
		boolean[][] ah;
		MazeWall(int n) {
			this.n=n;
			this.av=new boolean[n][n-1];
			this.ah=new boolean[n-1][n];
		}
		void init(Scanner sc) {
			for (int y=0; y<n; y++) {
				String s=sc.next();
				for (int x=0; x<n-1; x++) {
					char ch=s.charAt(x);
					if (ch=='1') av[y][x]=true;
				}
			}
			for (int y=0; y<n-1; y++) {
				String s=sc.next();
				for (int x=0; x<n; x++) {
					char ch=s.charAt(x);
					if (ch=='1') ah[y][x]=true;
				}
			}
		}
		boolean canMove(Point p, char d) {
			switch (d) {
			case 'U':
				if (p.y==0) return false;
				return !ah[p.y-1][p.x];
			case 'D':
				if (p.y==n-1) return false;
				return !ah[p.y][p.x];
			case 'L':
				if (p.x==0) return false;
				return !av[p.y][p.x-1];
			case 'R':
				if (p.x==n-1) return false;
				return !av[p.y][p.x];
			}
			return false;
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
    static String getCRLF() {
    	return System.getProperty("line.separator");
    }
}
/*



*/
