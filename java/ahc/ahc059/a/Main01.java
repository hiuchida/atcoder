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
	void up(int d) {
		for (int i=0; i<d; i++) System.out.println("U");
	}
	void down(int d) {
		for (int i=0; i<d; i++) System.out.println("D");
	}
	void left(int d) {
		for (int i=0; i<d; i++) System.out.println("L");
	}
	void right(int d) {
		for (int i=0; i<d; i++) System.out.println("R");
	}
	void pick() {
		System.out.println("Z");
	}
	void drop() {
		System.out.println("X");
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
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		int cp=pos(0, 0);
		for (int t=0; t<n*n/2; t++) {
			int[] ap=srch(t);
			int[] ad=new int[2];
			ad[0]=calcL1(cp, ap[0]);
			ad[1]=calcL1(cp, ap[1]);
//			System.out.println(t+":"+pstr(ap[0])+"="+ad[0]+","+pstr(ap[1])+"="+ad[1]);
			move(cp, ap[0]);
			pick();
			move(ap[0], ap[1]);
			pick();
			cp=ap[1];
		}
	}
	int n;
	int[][] aai;
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
