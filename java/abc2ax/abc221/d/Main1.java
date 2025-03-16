import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] aa = new int[n];
		int[] ab = new int[n];
		for (int i=0; i<n; i++) {
			aa[i] = sc.nextInt();
			ab[i] = aa[i]+sc.nextInt();
		}
		Arrays.sort(aa);
		Arrays.sort(ab);
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(ab));
		long[] ans = new long[n+1];
		int d=0;
		for (int i=aa[0]; i<=ab[n-1]; i++) {
			int ia=Arrays.binarySearch(aa, i);
			int ib=Arrays.binarySearch(ab, i);
			if (ia>=0) d++;
			if (ib>=0) d--;
			ans[d]++;
//			System.out.println(i+" "+ia+" "+ib+" "+d);
		}
		StringBuilder sb=new StringBuilder();
		for (int i=1; i<=n; i++) {
			if (sb.length()>0) sb.append(" ");
			sb.append(ans[i]);
		}
		System.out.println(sb);
	}
}
/*
3
1 2
2 3
3 1

2
1000000000 1000000000
1000000000 1000000000
*/
