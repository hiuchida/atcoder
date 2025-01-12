import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int x = sc.nextInt();
		int[] ary = new int[m];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		int l=0;
		int r=0;
		for (int i=0; i<m; i++) {
			if (ary[i]<x) l++;
			if (ary[i]>x) r++;
		}
		int ans = Math.min(l, r);
		System.out.println(ans);
	}
}
/*
5 3 3
1 2 4

7 3 2
4 5 6

10 7 5
1 2 3 4 6 8 9
*/
