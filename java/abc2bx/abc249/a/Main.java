import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int e = sc.nextInt();
		int f = sc.nextInt();
		int x = sc.nextInt();
		int xt=calc(a, b, c, x);
		int xa=calc(d, e, f, x);
		if (xt>xa) System.out.println("Takahashi");
		else if (xt<xa) System.out.println("Aoki");
		else System.out.println("Draw");
	}
	static int calc(int a, int b, int c, int x) {
		int ans=0;
		while (x>0) {
			int aa=Math.min(a, x);
			ans+=aa*b;
			x-=aa;
			x-=c;
		}
		return ans;
	}
}
/*
4 3 3 6 2 5 10

3 1 4 1 5 9 2

1 1 1 1 1 1 1
*/
