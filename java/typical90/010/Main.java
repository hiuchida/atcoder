import java.util.*;
public class Main {
	static void DEBUG(Object x) {
		System.out.println(x);
	}
	static void DEBUG(long x) {
		DEBUG(""+x);
    }
	static void DEBUG(int[] x) {
		DEBUG(Arrays.toString(x));
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] sum1 = new int[n+1];
		int[] sum2 = new int[n+1];
		for (int i=1; i<=n; i++) {
			int c = sc.nextInt();
			int p = sc.nextInt();
			sum1[i] = sum1[i-1];
			sum2[i] = sum2[i-1];
			if (c == 1) sum1[i] += p;
			else sum2[i] += p;
		}
//		DEBUG(sum1);
//		DEBUG(sum2);
		int q = sc.nextInt();
		for (int i=1; i<=q; i++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			int s1 = sum1[r] - sum1[l-1];
			int s2 = sum2[r] - sum2[l-1];
			System.out.println(s1 + " " + s2);
		}
	}
}
