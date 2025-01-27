import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			ary[i] = sc.nextInt();
		}
		int ans = 0;
		for (int i=0; i<n; i++) {
			if (is(ary[i])) ans++;
		}
		System.out.println(ans);
	}
	static boolean is(int s) {
		for (int a=1; true; a++) {
			if (4*a+3*a+3>s) break;
			for (int b=1; true; b++) {
				int x=4*a*b+3*a+3*b;
				if (x==s) return false;
				if (x>s) break;
			}
		}
		return true;
	}
}
/*
3
10 20 39

5
666 777 888 777 666
*/
