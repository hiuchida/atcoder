import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int t=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(ary));
		long ans=0;
		for (int i=0; i<n; i++) {
			int x=t;
			if (i<n-1) {
				x=Math.min(x, ary[i+1]-ary[i]);
			}
			ans+=x;
		}
		System.out.println(ans);
	}
}
/*
2 4
0 3

2 4
0 5

4 1000000000
0 1000 1000000 1000000000

1 1
0

9 10
0 3 5 7 100 110 200 300 311
*/
