import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		boolean[] ary=new boolean[n+1];
		for (int i=1; i<=n; i++) ary[i]=true;
		for (int i=0; i<q; i++) {
			int t=sc.nextInt();
			ary[t]=!ary[t];
		}
		int ans=0;
		for (int i=1; i<=n; i++) if (ary[i]) ans++;
		System.out.println(ans);
	}
}
/*
30 6
2 9 18 27 18 9


1 7
1 1 1 1 1 1 1

9 20
9 5 1 2 2 2 8 9 2 1 6 2 6 5 8 7 8 5 9 8
*/
