import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		String s = sc.next();
		StringBuilder sb = new StringBuilder();
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			ary[i] = sc.nextInt();
		}
		Arrays.sort(ary);
		int ans = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				ans += ary[i] - ary[j];
			}
		}
		int sum = a + b + c;
		if (sum < 10) System.out.println(sum + " " + s);
		else System.out.println("error");
		System.out.println(ans);
		System.out.println("Yes");
		System.out.println("No");
		System.exit(0);
	}
}
/*
1 2 3
test
3
3 2 1

3 4 5
test2
3
5 4 3
*/
