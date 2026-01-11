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
	int sq(int p1, int p2) {
		int y1=Math.min(py(p1), py(p2));
		int x1=Math.min(px(p1), px(p2));
		int y2=Math.max(py(p1), py(p2));
		int x2=Math.max(px(p1), px(p2));
		return (y2-y1+1)*(x2-x1+1);
	}
	int[] other(int p1, int p2) {
		int y1=Math.min(py(p1), py(p2));
		int x1=Math.min(px(p1), px(p2));
		int y2=Math.max(py(p1), py(p2));
		int x2=Math.max(px(p1), px(p2));
		Set<Integer> set1=new HashSet<>();
		Set<Integer> set2=new HashSet<>();
		for (int y=y1; y<=y2; y++) {
			for (int x=x1; x<=x2; x++) {
				if (y==y1 && x==x1) continue;
				if (y==y2 && x==x2) continue;
				if (flag[y][x]) continue;
				int v=aai[y][x];
				if (set1.contains(v)) {
					set2.add(v);
				} else {
					set1.add(v);
				}
			}
		}
		int maxv=0;
		int[] maxi=null;
		for (int v : set2) {
			int[] ap=srch(v);
			if (py(p1)<=py(p2)) {
				if (py(ap[0])>py(ap[1])) {
					int tmp=ap[0];
					ap[0]=ap[1];
					ap[1]=tmp;
				}
			} else {
				if (py(ap[0])<py(ap[1])) {
					int tmp=ap[0];
					ap[0]=ap[1];
					ap[1]=tmp;
				}
			}
			if (px(p1)<=px(p2)) {
				if (px(ap[0])<=px(ap[1])) {
					int sq=sq(ap[0], ap[1]);
					if (maxv<sq) {
						maxv=sq;
						maxi=ap;
					}
				}
			} else {
				if (px(ap[0])>px(ap[1])) {
					int sq=sq(ap[0], ap[1]);
					if (maxv<sq) {
						maxv=sq;
						maxi=ap;
					}
				}
			}
		}
		return maxi;
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
			if (ad[0]>ad[1]) {
				int tmp=ap[0];
				ap[0]=ap[1];
				ap[1]=tmp;
			}
			{
				ans.move(cp, ap[0]);
				ans.pick();
				int[] ao=other(ap[0], ap[1]);
				if (ao!=null) {
					flag[py(ao[0])][px(ao[0])]=true;
					flag[py(ao[1])][px(ao[1])]=true;
					t++;
					ans.move(ap[0], ao[0]);
					ans.pick();
					int[] aoo=other(ao[0], ao[1]);
					if (aoo!=null) {
						flag[py(aoo[0])][px(aoo[0])]=true;
						flag[py(aoo[1])][px(aoo[1])]=true;
						t++;
						ans.move(ao[0], aoo[0]);
						ans.pick();
						int[] aooo=other(aoo[0], aoo[1]);
						if (aooo!=null) {
							flag[py(aooo[0])][px(aooo[0])]=true;
							flag[py(aooo[1])][px(aooo[1])]=true;
							t++;
							ans.move(aoo[0], aooo[0]);
							ans.pick();
							ans.move(aooo[0], aooo[1]);
							ans.pick();
							ans.move(aooo[1], aoo[1]);
						} else {
							ans.move(aoo[0], aoo[1]);
						}
						ans.pick();
						ans.move(aoo[1], ao[1]);
					} else {
						ans.move(ao[0], ao[1]);
					}
					ans.pick();
					ans.move(ao[1], ap[1]);
				} else {
					ans.move(ap[0], ap[1]);
				}
				ans.pick();
				cp=ap[1];
//			} else {
//				ans.move(cp, ap[1]);
//				ans.pick();
//				int[] ao=other(ap[1], ap[0]);
//				if (ao!=null) {
//					ans.move(ap[1], ao[0]);
//					ans.pick();
//					int[] aoo=other(ao[0], ao[1]);
//					if (aoo!=null) {
//						ans.move(ao[0], aoo[0]);
//						ans.pick();
//						ans.move(aoo[0], aoo[1]);
//						ans.pick();
//						ans.move(aoo[1], ao[1]);
//						flag[py(aoo[0])][px(aoo[0])]=true;
//						flag[py(aoo[1])][px(aoo[1])]=true;
//						t++;
//					} else {
//						ans.move(ao[0], ao[1]);
//					}
//					ans.pick();
//					ans.move(ao[1], ap[0]);
//					flag[py(ao[0])][px(ao[0])]=true;
//					flag[py(ao[1])][px(ao[1])]=true;
//					t++;
//				} else {
//					ans.move(ap[1], ap[0]);
//				}
//				ans.pick();
//				cp=ap[0];
			}
		}
		ans.print();
		if (!RELEASE) System.err.println("Score="+ans.score());
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
