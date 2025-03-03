import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[1000*1000+1];
		int ans=Integer.MAX_VALUE;
		for (int i=1; i<=n; i++) {
			int a = sc.nextInt();
			int pre=ary[a];
			if (pre>0) {
				int dis=i-pre+1;
				ans=Math.min(ans, dis);
			}
			ary[a]=i;
		}
		if (ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
}
/*
5
3 9 5 3 1

4
2 5 3 1

10
1 1 2 3 5 8 13 21 34 55
*/
