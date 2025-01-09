import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int t = sc.nextInt();
		int p = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		Arrays.sort(ary);
		int c=0;
		for (int i=n-1; i>=0; i--) {
			if (ary[i] >= t) p--;
			else {
				c+=t-ary[i];
				t=ary[i];
				p--;
			}
			if (p == 0) break;
		}
		System.out.println(c);
	}
}
/*
5 10 3
3 11 1 6 2

2 5 2
10 10

3 10 1
1 2 3
*/
