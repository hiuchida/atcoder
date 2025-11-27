import java.util.*;
public class Main {
	static Button btn;
	static boolean[][] map;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		init(sc);
		btn=new Button();
		btn.print();
		map=new boolean[n][n];
		for (int i=0; i<m; i++) {
			Point pt=ap[i];
			map[pt.y][pt.x]=true;
		}
		Point[] ap2=makeTarget();
		boolean bDone=false;
		for (int idx=0; idx<n*n; idx++) {
			if (bDone) break;
			Point p0=ap2[idx];
			if (map[p0.y][p0.x]) continue;
			String[] ar=new String[m];
			int minl=Integer.MAX_VALUE;
			int mini=-1;
			for (int i=0; i<m; i++) {
				Point p1=ap[i];
				String route=bfs(p1, p0);
				ar[i]=route;
				if (route.length()<minl) {
					minl=route.length();
					mini=i;
				}
			}
//			System.out.println(mini+":"+ar[mini]);
			for (int i=0; i<ar[mini].length(); i++) {
				int no=9;
				switch (ar[mini].charAt(i)) {
				case 'U':
					no=0;
					break;
				case 'D':
					no=1;
					break;
				case 'L':
					no=2;
					break;
				case 'R':
					no=3;
					break;
				}
				if (!operate(no)) {
					bDone=true;
					break;
				}
			}
		}
	}
	static Point[] makeTarget() {
		int[][] flag=bfsAll();
		Point[] ans=new Point[n*n];
		Map<Integer, List<Point>> map=new TreeMap<>();
		for (int y0=0; y0<n; y0++) {
			for (int x0=0; x0<n; x0++) {
				int val=flag[y0][x0];
				if (!map.containsKey(val)) {
					map.put(val, new ArrayList<>());
				}
				List<Point> list=map.get(val);
				list.add(new Point(y0, x0));
			}
		}
//		System.out.println(map);
		int idx=0;
		for (Integer key : map.keySet()) {
			List<Point> list=map.get(key);
			for (Point pt : list) {
				ans[idx++]=pt;
			}
		}
		return ans;
	}
	static int[][] bfsAll() {
		int[][] flag=new int[n][n];
		Deque<Point> que=new ArrayDeque<>();
		for (int i=0; i<m; i++) {
			Point p0=ap[i];
			flag[p0.y][p0.x]=1;
			que.offer(p0);
		}
		while (que.size()>0) {
			Point pt=que.poll();
			int step=flag[pt.y][pt.x];
			if (canMove(pt, 'U') && pt.y>0 && flag[pt.y-1][pt.x]==0) {
				flag[pt.y-1][pt.x]=step+1;
				que.offer(new Point(pt.y-1, pt.x));
			}
			if (canMove(pt, 'D') && pt.y<n-1 && flag[pt.y+1][pt.x]==0) {
				flag[pt.y+1][pt.x]=step+1;
				que.offer(new Point(pt.y+1, pt.x));
			}
			if (canMove(pt, 'L') && pt.x>0 && flag[pt.y][pt.x-1]==0) {
				flag[pt.y][pt.x-1]=step+1;
				que.offer(new Point(pt.y, pt.x-1));
			}
			if (canMove(pt, 'R') && pt.x<n-1 && flag[pt.y][pt.x+1]==0) {
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
		return flag;
	}
	static boolean operate(int no) {
		if (limit<=0) return false;
		System.out.println(no);
		limit--;
		moveAll(no);
		return true;
	}
	static void moveAll(int no) {
		for (int i=0; i<m; i++) {
			char ch=btn.ary[no][i];
			move(i, ch);
		}
	}
	static void move(int idx, char ch) {
		Point pt=ap[idx];
		if (!canMove(pt, ch)) return;
		switch (ch) {
		case 'U':
			pt=new Point(pt.y-1, pt.x);
			break;
		case 'D':
			pt=new Point(pt.y+1, pt.x);
			break;
		case 'L':
			pt=new Point(pt.y, pt.x-1);
			break;
		case 'R':
			pt=new Point(pt.y, pt.x+1);
			break;
		}
		ap[idx]=pt;
		map[pt.y][pt.x]=true;
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
			if (canMove(pt, 'U') && pt.y>0 && flag[pt.y-1][pt.x]==0) {
				flag[pt.y-1][pt.x]=step+1;
				que.offer(new Point(pt.y-1, pt.x));
			}
			if (canMove(pt, 'D') && pt.y<n-1 && flag[pt.y+1][pt.x]==0) {
				flag[pt.y+1][pt.x]=step+1;
				que.offer(new Point(pt.y+1, pt.x));
			}
			if (canMove(pt, 'L') && pt.x>0 && flag[pt.y][pt.x-1]==0) {
				flag[pt.y][pt.x-1]=step+1;
				que.offer(new Point(pt.y, pt.x-1));
			}
			if (canMove(pt, 'R') && pt.x<n-1 && flag[pt.y][pt.x+1]==0) {
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
			if (pt.equals(p0)) {
				break;
			}
			if (canMove(pt, 'U') && pt.y>0 && flag[pt.y-1][pt.x]==step-1) {
				pt=new Point(pt.y-1, pt.x);
				step--;
				sb.append("D");
			} else if (canMove(pt, 'D') && pt.y<n-1 && flag[pt.y+1][pt.x]==step-1) {
				pt=new Point(pt.y+1, pt.x);
				step--;
				sb.append("U");
			} else if (canMove(pt, 'L') && pt.x>0 && flag[pt.y][pt.x-1]==step-1) {
				pt=new Point(pt.y, pt.x-1);
				step--;
				sb.append("R");
			} else if (canMove(pt, 'R') && pt.x<n-1 && flag[pt.y][pt.x+1]==step-1) {
				pt=new Point(pt.y, pt.x+1);
				step--;
				sb.append("L");
			}
		}
		sb.reverse();
//		System.out.println(sb);
		return sb.toString();
	}
	static boolean canMove(Point p, char d) {
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
	static class Button {
		char[][] ary=new char[k][m];
		Button() {
			for (int i=0; i<k; i++) {
				for (int j=0; j<m; j++) {
					char ch;
					switch (i) {
					case 0:
						ch='U';
						break;
					case 1:
						ch='D';
						break;
					case 2:
						ch='L';
						break;
					case 3:
						ch='R';
						break;
					default:
						ch='S';
						break;
					}
					ary[i][j]=ch;
				}
			}
		}
		void print() {
			for (int i=0; i<k; i++) {
				for (int j=0; j<m; j++) {
					if (j>0) System.out.print(" ");
					System.out.print(ary[i][j]);
				}
				System.out.println();
			}
		}
	}
	static int n;
	static int m;
	static int k;
	static int limit;
	static Point[] ap;
	static boolean[][] av;
	static boolean[][] ah;
	static void init(Scanner sc) {
		n=sc.nextInt();
		m=sc.nextInt();
		k=sc.nextInt();
		limit=2*n*n;
		ap=new Point[m];
		for (int i=0; i<m; i++) {
			int y=sc.nextInt();
			int x=sc.nextInt();
			ap[i]=new Point(y, x);
		}
		sc.nextLine();
		av=new boolean[n][n-1];
		for (int y=0; y<n; y++) {
			String s=sc.next();
			for (int x=0; x<n-1; x++) {
				char ch=s.charAt(x);
				if (ch=='1') av[y][x]=true;
			}
		}
		ah=new boolean[n-1][n];
		for (int y=0; y<n-1; y++) {
			String s=sc.next();
			for (int x=0; x<n; x++) {
				char ch=s.charAt(x);
				if (ch=='1') ah[y][x]=true;
			}
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
}
/*



*/
