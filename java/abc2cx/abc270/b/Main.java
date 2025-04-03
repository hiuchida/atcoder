import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int z = sc.nextInt();
		if (x>=0) {
			if (x<y || y<0) {
				System.out.println(x);
				System.exit(0);
			}
			if (y<z) {
				System.out.println(-1);
				System.exit(0);
			}
			int ans=0;
			if (z<0) ans=-z;
			else ans=z;
			ans+=x-z;
			System.out.println(ans);
		} else {
			if (x>y || y>0) {
				System.out.println(-x);
				System.exit(0);
			}
			if (y>z) {
				System.out.println(-1);
				System.exit(0);
			}
			int ans=0;
			if (z<0) ans=-z;
			else ans=z;
			ans+=z-x;
			System.out.println(ans);
		}
	}
}
/*
10 -10 1

20 10 -10

100 1 1000
*/
