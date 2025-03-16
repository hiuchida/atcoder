import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] aryx = new int[n];
		for (int i=0; i<n; i++) {
			int x = sc.nextInt();
			aryx[i] = x;
		}
		int[] aryp = new int[n];
		for (int i=0; i<n; i++) {
			int p = sc.nextInt();
			aryp[i] = p;
		}
//		System.out.println(Arrays.toString(aryx));
//		System.out.println(Arrays.toString(aryp));
		long[] sum = new long[n+1];
		for (int i=1; i<=n; i++) {
			sum[i] = sum[i-1] + aryp[i-1];
		}
//		System.out.println(Arrays.toString(sum));
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			int il = Arrays.binarySearch(aryx, l);
			if (il < 0) {
				il = -(il+1);
			} else {
//				il++;
			}
//			il = Math.max(il-1, 0);
			int ir = Arrays.binarySearch(aryx, r);
			if (ir < 0) {
				ir = -(ir+1);
			} else {
				ir++;
			}
			long v = sum[ir] - sum[il];
//			System.out.println("" + il + " " + ir);
			System.out.println(v);
		}
	}
}
