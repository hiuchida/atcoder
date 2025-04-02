import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] at=new int[n+1];
		int[] ax=new int[n];
		int[] aa=new int[n];
		for (int i=0; i<n; i++) {
			at[i]=sc.nextInt();
			ax[i]=sc.nextInt();
			aa[i]=sc.nextInt();
		}
		int maxt=at[n-1];
		int maxx=5;
//		System.out.println(Arrays.toString(at));
//		System.out.println(Arrays.toString(ax));
//		System.out.println(Arrays.toString(aa));
//		System.out.println(maxt);
		long[][] dp=new long[maxt+1][maxx];
		for (int t=0; t<=maxt; t++) {
			for (int x=0; x<maxx; x++) {
				dp[t][x]=-1;
			}
		}
		dp[0][0]=0;
		int idx=0;
		for (int t=0; t<maxt; t++) {
			int curx=-1;
			int cura=-1;
			if (at[idx]==t+1) {
				curx=ax[idx];
				cura=aa[idx];
				idx++;
			}
			for (int x=0; x<maxx; x++) {
				long v=dp[t][x];
				if (v>=0) {
					if (x>0) {
						long v1=v;
						if (curx==x-1) {
							v1+=cura;
						}
						dp[t+1][x-1]=Math.max(dp[t+1][x-1], v1);
					}
					if (x<maxx-1) {
						long v2=v;
						if (curx==x+1) {
							v2+=cura;
						}
						dp[t+1][x+1]=Math.max(dp[t+1][x+1], v2);
					}
					{
						long v3=v;
						if (curx==x) {
							v3+=cura;
						}
						dp[t+1][x]=Math.max(dp[t+1][x], v3);
					}
				}
			}
//			for (int i=0; i<=maxt; i++) {
//				System.out.println(Arrays.toString(dp[i]));
//			}
//			System.out.println();
		}
//		for (int t=0; t<=maxt; t++) {
//			System.out.println(Arrays.toString(dp[t]));
//		}
		long ans=0;
		for (int x=0; x<maxx; x++) {
			ans=Math.max(ans, dp[maxt][x]);
		}
		System.out.println(ans);
	}
}
/*
3
1 0 100
3 3 10
5 4 1

3
1 4 1
2 4 1
3 4 1

10
1 4 602436426
2 1 623690081
3 3 262703497
4 4 628894325
5 3 450968417
6 1 161735902
7 1 707723857
8 2 802329211
9 0 317063340
10 2 125660016
*/
