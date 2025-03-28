import java.util.*;
public class Main {
	static int n;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int q = sc.nextInt();
		int l=1;
		int r=2;
		int ans=0;
		for (int i=0; i<q; i++) {
			String h = sc.next();
			int t = sc.nextInt();
			int d,e,f;
			switch (h) {
			case "L":
				d=count(l, r, t, 1);
				e=count(l, r, t, -1);
				f=Math.min(d, e);
//				System.out.println("L "+d+" "+e+" "+f);
				ans+=f;
				l=t;
				break;
			case "R":
				d=count(r, l, t, 1);
				e=count(r, l, t, -1);
				f=Math.min(d, e);
//				System.out.println("R "+d+" "+e+" "+f);
				ans+=f;
				r=t;
				break;
			}
		}
		System.out.println(ans);
	}
	static int count(int my, int you, int t, int d) {
		int c=0;
		while (my!=t) {
			c++;
			my=modadd(my, d);
			if (my==you) {
				c=Integer.MAX_VALUE;
				break;
			}
		}
		return c;
	}
	static void test_modadd() {
		for (int i=1; i<=n; i++) {
			System.out.println(i+" "+modadd(i, 1)+" "+modadd(i, -1));
		}
	}
	static int modadd(int x, int d) {
		x+=d;
		x+=n-1;
		x%=n;
		x++;
		return x;
	}
}
/*
6 3
R 4
L 5
R 6

100 2
L 1
R 2

30 8
R 23
R 26
R 29
L 20
R 29
R 19
L 7
L 16
*/
