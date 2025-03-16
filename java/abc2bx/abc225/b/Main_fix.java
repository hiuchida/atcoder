import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n+1];
		for (int i=0; i<n-1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			ary[a]++;
			ary[b]++;
		}
		for (int i=1; i<=n; i++) {
			if (ary[i]==n-1) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
5
1 4
2 4
3 4
4 5

4
2 4
1 4
2 3

10
9 10
3 10
4 10
8 10
1 10
2 10
7 10
6 10
5 10
*/
