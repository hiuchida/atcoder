import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		int[] aa = new int[n];
		System.arraycopy(ary, 0, aa, 0, n);
		Arrays.sort(aa);
		for (int i=0; i<n; i++) {
			if (ary[i] == aa[n-2]) System.out.println(i+1);
		}
	}
}
/*
4
8 2 5 1

8
1 2 3 4 5 10 9 11
*/
