import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=5;
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			ary[i] = sc.nextInt();
		}
		for (int i=1; i<n; i++) {
			int t=ary[i-1];
			ary[i-1]=ary[i];
			ary[i]=t;
			if (is(ary)) {
				System.out.println("Yes");
				System.exit(0);
			}
			t=ary[i-1];
			ary[i-1]=ary[i];
			ary[i]=t;
		}
		System.out.println("No");
	}
	static boolean is(int[] ary) {
		for (int i=0; i<ary.length; i++) {
			if (ary[i]!=i+1) return false;
		}
		return true;
	}
}
/*
1 2 4 3 5

5 3 2 4 1

1 2 3 4 5

2 1 3 4 5
*/
