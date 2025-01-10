import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		int d=sc.nextInt();
		int e=sc.nextInt();
		int f=sc.nextInt();
		int g=sc.nextInt();
		int h=sc.nextInt();
		int i=sc.nextInt();
		int j=sc.nextInt();
		int k=sc.nextInt();
		int l=sc.nextInt();
		boolean b1=isInner(a, d, g, j);
		boolean b2=isInner(b, e, h, k);
		boolean b3=isInner(c, f, i, l);
		if (b1 && b2 && b3) ok();
		ng();
	}
	static boolean isInner(int a, int b, int c, int d) {
		if (b<=c || d<=a) return false;
		return true;
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
0 0 0 4 5 6
2 3 4 5 6 7

0 0 0 2 2 2
0 0 2 2 2 4

0 0 0 1000 1000 1000
10 10 10 100 100 100
*/
