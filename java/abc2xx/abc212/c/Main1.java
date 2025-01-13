import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] aa = new int[n];
		for (int i=0; i<aa.length; i++) {
			aa[i] = sc.nextInt();
		}
		int[] ab = new int[m];
		for (int i=0; i<ab.length; i++) {
			ab[i] = sc.nextInt();
		}
		Arrays.sort(aa);
		Arrays.sort(ab);
		int ans=Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				ans=Math.min(ans, Math.abs(aa[i]-ab[j]));
			}
		}
		System.out.println(ans);
	}
}
/*
*/
