import java.util.*;
public class Main {
	static int N=(int)1e6;
	static int M=10007;
	static int[] dp;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		dp=new int[N+1];
		int n=sc.nextInt();
		dp[3]=1;
		for (int i=4; i<=n; i++) {
			dp[i]=dp[i-3]+dp[i-2]+dp[i-1];
			dp[i]%=M;
		}
		int ans=dp[n];
//		int ans=calc(n); //StackOverflowError
		System.out.println(ans);
	}
	static int calc(int n) {
		if (n==1 || n==2) return 0;
		if (n==3) return 1;
		if (dp[n]>0) return dp[n];
		int v1=calc(n-3);
		int v2=calc(n-2);
		int v3=calc(n-1);
		int v=v1+v2+v3;
		v%=M;
		dp[n]=v;
		return v;
	}
}
/*
7

1

100000
*/
