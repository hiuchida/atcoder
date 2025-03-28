import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		int ans=Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			int r=i+n-k-1;
			if (r>=n) break;
			ans=Math.min(ans, ary[r]-ary[i]);
		}
		System.out.println(ans);
	}
}
/*
5 2
3 1 5 4 9

6 5
1 1 1 1 1 1

8 3
31 43 26 6 18 36 22 13
*/
