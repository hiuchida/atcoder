import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			ary[i] = sc.nextInt();
		}
		long a=ary[0];
		long b=ary[1];
		for (int i=2; i<n; i++) {
			long d=ary[i];
			long c=ary[i-1];
			if (c*b!=d*a) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
}
/*
5
3 6 12 24 48

3
1 2 3

2
10 8
*/
