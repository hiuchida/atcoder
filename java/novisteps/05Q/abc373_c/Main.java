import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] aa = new int[n];
		int[] ab = new int[n];
		for (int i=0; i<n; i++) {
			aa[i] = sc.nextInt();
		}
		for (int i=0; i<n; i++) {
			ab[i] = sc.nextInt();
		}
		Arrays.sort(aa);
		Arrays.sort(ab);
		long ans = aa[n-1]+ab[n-1];
		System.out.println(ans);
	}
}
/*
2
-1 5
3 -7

6
15 12 3 -13 -1 -19
7 17 -13 -10 18 4
*/
