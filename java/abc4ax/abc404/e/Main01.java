import java.util.*;
public class Main {
	public static void main(String[] args) {
		int N=(int)1e9;
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ac=new int[n];
		for (int i=1; i<n; i++) {
			ac[i]=sc.nextInt();
		}
		int[] aa=new int[n];
		for (int i=1; i<n; i++) {
			aa[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(ac));
//		System.out.println(Arrays.toString(aa));
		int[][] dp=new int[n+1][n+1];
		Arrays.fill(dp[0], N);
		dp[0][0]=0;
		for (int i=1; i<n; i++) {
//			Arrays.fill(dp[i], N);
			for (int j=0; j<=n; j++) {
				dp[i][j]=dp[i-1][j];
			}
			int min=N;
			for (int j=0; j<=ac[i]; j++) {
				if (i-j>=0) min=Math.min(min, dp[i-1][i-j]);
			}
			dp[i][i]=Math.min(dp[i][i], min+1);
		}
		for (int i=0; i<n; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		TreeSet<Integer> set=new TreeSet<>();
		long ans=0;
		for (int i=n-1; i>0; i--) {
			int a=aa[i];
			if (a==0) continue;
			if (set.contains(i)) continue;
			set.add(i);
			ans+=dp[n-1][i];
			while (a>0) {
				if (set.contains(i)) break;
				set.add(i);
				a--;
				for (int j=ac[i]; j>=0; j--) {
					if (i-j>=0 && dp[n-1][i-j]==a) {
						a=i-j;
						break;
					}
				}
			}
			System.out.println(i+" "+a+" "+set+" "+ans);
		}
		System.out.println();
		System.out.println("Yes");
		System.out.println("No");
		System.exit(0);
	}
}
/*
5
1 1 2 1
1 0 0 1

6
1 2 1 3 1
1 1 0 1 1

16
1 1 1 2 5 1 1 3 4 1 4 3 1 1 2
1 0 0 0 1 0 0 1 1 0 0 0 0 0 1
*/
