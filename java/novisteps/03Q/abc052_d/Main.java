import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int a=sc.nextInt();
		int b=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		long ans=0;
		for (int i=1; i<n; i++) {
			long d=ary[i]-ary[i-1];
			ans+=Math.min(a*d, b);
		}
		System.out.println(ans);
	}
}
/*
4 2 5
1 2 5 7

7 1 100
40 43 45 105 108 115 124

7 1 2
24 35 40 68 72 99 103
*/
