import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		int ans=0;
		for (int i=0; i<n; i++) {
			ans+=2*Math.min(ary[i], k-ary[i]);
		}
		System.out.println(ans);
	}
}
/*
1
10
2

2
9
3 6

5
20
11 12 9 17 12
*/
