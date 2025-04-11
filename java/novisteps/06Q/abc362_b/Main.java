import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int xa = sc.nextInt();
		int ya = sc.nextInt();
		int xb = sc.nextInt();
		int yb = sc.nextInt();
		int xc = sc.nextInt();
		int yc = sc.nextInt();
		long ab = calc(xa, ya, xb, yb);
		long bc = calc(xb, yb, xc, yc);
		long ac = calc(xa, ya, xc, yc);
		if (ab < ac && bc < ac) {
			if (ac==ab+bc) ok();
			ng();
		} else if (bc < ab && ac < ab) {
			if (ab==bc+ac) ok();
			ng();
		} else {
			if (bc==ab+ac) ok();
			ng();
		}
	}
	static long calc(int x1, int y1, int x2, int y2) { //abc362_b: x1,y1からx2,y2までのユークリッド距離の2乗
		long dx=x1-x2;
		long dy=y1-y2;
		return dx*dx+dy*dy;
	}
	static void ok() {
		System.out.println("Yes");
		System.exit(0);
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
}
/*
0 0
4 0
0 3

-4 3
2 1
3 4

2 4
-3 2
1 -2
*/
