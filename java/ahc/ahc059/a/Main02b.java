import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	int py(int p) {
		return p/n;
	}
	int px(int p) {
		return p%n;
	}
	String pstr(int p) {
		return "("+py(p)+","+px(p)+")";
	}
	int pos(int y, int x) {
		return y*n+x;
	}
	int[] srch(int t) {
		int p1=-1;
		int p2=-1;
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (aai[y][x]==t) {
					if (p1<0) p1=pos(y, x);
					else p2=pos(y, x);
				}
			}
		}
		return new int[] { p1, p2 };
	}
	int near(int p) {
		int[] dp = { -n, -1, 1, n };
		List<Integer> li=new ArrayList<>();
		Set<Integer> set=new HashSet<>();
		li.add(p);
		set.add(p);
		while (true) {
			List<Integer> li2=new ArrayList<>();
			for (int i : li) {
				int y=py(i);
				int x=px(i);
				if (!flag[y][x]) {
					return pos(y, x);
				}
				for (int d : dp) {
					int i2=i+d;
					int y2=py(i2);
					int x2=px(i2);
					if (y2<0 || n-1<y2 || x2<0 || n-1<x2) continue;
					if (set.contains(i2)) continue;
					set.add(i2);
//					if (flag[y2][x2]) continue;
					li2.add(i2);
				}
			}
			li=li2;
		}
	}
	boolean[][] flag;
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		flag=new boolean[n][n];
		Answer ans=new Answer();
		int cp=pos(0, 0);
		for (int t=0; t<n*n/2; t++) {
			int np=near(cp);
//			System.out.println(t+":"+np);
			int nt=aai[py(np)][px(np)];
			int[] ap=srch(nt);
			flag[py(ap[0])][px(ap[0])]=true;
			flag[py(ap[1])][px(ap[1])]=true;
			int[] ad=new int[2];
			ad[0]=calcL1(cp, ap[0]);
			ad[1]=calcL1(cp, ap[1]);
//			System.out.println(t+":"+pstr(ap[0])+"="+ad[0]+","+pstr(ap[1])+"="+ad[1]);
			if (ad[0]<=ad[1]) {
				ans.move(cp, ap[0]);
				ans.pick();
				ans.move(ap[0], ap[1]);
				ans.pick();
				cp=ap[1];
			} else {
				ans.move(cp, ap[1]);
				ans.pick();
				ans.move(ap[1], ap[0]);
				ans.pick();
				cp=ap[0];
			}
		}
		ans.print();
		System.err.println("Score="+ans.score());
	}
	class Answer {
		List<String> ls=new ArrayList<>();
		int k;
		int score;
		void up(int d) {
			for (int i=0; i<d; i++) ls.add("U");
			k+=d;
		}
		void down(int d) {
			for (int i=0; i<d; i++) ls.add("D");
			k+=d;
		}
		void left(int d) {
			for (int i=0; i<d; i++) ls.add("L");
			k+=d;
		}
		void right(int d) {
			for (int i=0; i<d; i++) ls.add("R");
			k+=d;
		}
		void pick() {
			ls.add("Z");
		}
		void drop() {
			ls.add("X");
		}
		void move(int p1, int p2) {
			int y1=py(p1);
			int y2=py(p2);
			int x1=px(p1);
			int x2=px(p2);
			if (y1>y2) up(y1-y2);
			else if (y1<y2) down(y2-y1);
			if (x1>x2) left(x1-x2);
			else if (x1<x2) right(x2-x1);
		}
		void print() {
			for (String s : ls) {
				System.out.println(s);
			}
		}
		int score() {
			score=n*n+2*n*n*n-k;
			return score;
		}
	}
	int n;
	int[][] aai;
	int[] ad;
	void init(Scanner sc) {
		n=sc.nextInt();
		aai=new int[n][n];
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				int a=sc.nextInt();
				aai[y][x]=a;
			}
		}
//		for (int y=0; y<n; y++) {
//			System.out.println(Arrays.toString(aai[y]));
//		}
		ad=new int[n*n/2];
		for (int t=0; t<n*n/2; t++) {
			int[] ap=srch(t);
			ad[t]=calcL1(ap[0], ap[1]);
		}
//		System.out.println(Arrays.toString(ad));
	}
	static Random rand=new Random(42);
	static long start;
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
	}
	int calcL1(int p1, int p2) {
		return calcL1(py(p1), px(p1), py(p2), px(p2));
	}
	static int calcL1(int x1, int y1, int x2, int y2) { //x1,y1からx2,y2までのマンハッタン距離
		int dx=x1-x2;
		int dy=y1-y2;
		return Math.abs(dx)+Math.abs(dy);
	}
}
/*

*/
