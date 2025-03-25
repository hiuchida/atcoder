import java.util.*;
public class Main {
	static int n;
	static long[][] ary;
	static long ans=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		ary=new long[2*n+1][2*n+1];
		for (int i=1; i<=2*n-1; i++) {
			for (int j=i+1; j<=2*n; j++) {
				int a = sc.nextInt();
				ary[i][j]=a;
				ary[j][i]=a;
			}
		}
//		for (int i=0; i<2*n+1; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		boolean[] flag=new boolean[2*n+1];
		dfs(0, flag, 0);
		System.out.println(ans);
	}
	static void dfs(int idx, boolean[] flag, long val) {
//		System.out.println(idx+" "+val+" "+Arrays.toString(flag));
		if (idx==2*n) {
			ans=Math.max(ans, val);
			return;
		}
		for (int i=1; i<=2*n-1; i++) {
			if (flag[i]) continue;
			flag[i]=true;
			for (int j=i+1; j<=2*n; j++) {
				if (flag[j]) continue;
				flag[j]=true;
				long val2 = val ^ ary[i][j];
				dfs(idx+2, flag, val2);
				flag[j]=false;
			}
			flag[i]=false;
		}
	}
}
/*
2
4 0 1
5 3
2

1
5

5
900606388 317329110 665451442 1045743214 260775845 726039763 57365372 741277060 944347467
369646735 642395945 599952146 86221147 523579390 591944369 911198494 695097136
138172503 571268336 111747377 595746631 934427285 840101927 757856472
655483844 580613112 445614713 607825444 252585196 725229185
827291247 105489451 58628521 1032791417 152042357
919691140 703307785 100772330 370415195
666350287 691977663 987658020
1039679956 218233643
70938785
*/
