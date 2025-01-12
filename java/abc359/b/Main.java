import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[2*n];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		int ans = 0;
		for (int i=0; i<2*n-2; i++) {
			if (ary[i]==ary[i+2]) ans++;
		}
		System.out.println(ans);
	}
}
/*
3
1 2 1 3 2 3

2
1 1 2 2

4
4 3 2 3 2 1 4 1
*/
