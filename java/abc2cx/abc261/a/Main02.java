import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l1 = sc.nextInt();
		int r1 = sc.nextInt();
		int l2 = sc.nextInt();
		int r2 = sc.nextInt();
		int ans=0;
		if (l1<l2) {
			if (r1>l2) ans=r1-l2;
		} else if (l1>l2) {
			if (r2>l1) ans=r2-l1;
		} else {
			if (r1>r2) ans=r2-l1;
			else ans=r1-l1;
		}
		System.out.println(ans);
	}
}
/*
0 3 1 5

0 1 4 5

0 3 3 7
*/
