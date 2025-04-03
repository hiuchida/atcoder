import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=sc.nextInt();
		}
		int[] dp=new int[2*n+1+1];
		for (int i=1; i<=n; i++) {
			int a=ary[i];
			dp[2*i]=dp[a]+1;
			dp[2*i+1]=dp[a]+1;
//			System.out.println(Arrays.toString(dp));
		}
		for (int i=1; i<=2*n+1; i++) {
			System.out.println(dp[i]);
		}
	}
}
/*
2
1 2

4
1 3 5 2
*/
