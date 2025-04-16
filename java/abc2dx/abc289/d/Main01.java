import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] an=new int[n];
		for (int i=0; i<n; i++) {
			an[i]=sc.nextInt();
		}
		int m=sc.nextInt();
		int[] am=new int[m];
		for (int i=0; i<m; i++) {
			am[i]=sc.nextInt();
		}
		int x=sc.nextInt();
//		System.out.println(Arrays.toString(an));
//		System.out.println(Arrays.toString(am));
		int[] dp=new int[x+1];
		for (int i=0; i<m; i++) {
			dp[am[i]]=-1;
		}
		for (int i=0; i<x; i++) {
			if (dp[i]<0) continue;
			for (int j=0; j<n; j++) {
				int a=i+an[j];
				if (a<=x && dp[a]==0) dp[a]=1;
			}
		}
//		System.out.println(Arrays.toString(dp));
		if (dp[x]>0) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
3
3 4 5
4
4 5 6 8
15

4
2 3 4 5
4
3 4 5 6
8

4
2 5 7 8
5
2 9 10 11 19
20
*/
