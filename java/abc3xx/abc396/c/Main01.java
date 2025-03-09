import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] aryb=new int[n];
		for (int i=0; i<n; i++) {
			aryb[i]=sc.nextInt();
		}
		Arrays.sort(aryb);
		int[] aryw=new int[m];
		for (int i=0; i<m; i++) {
			aryw[i]=sc.nextInt();
		}
		Arrays.sort(aryw);
//		System.out.println(Arrays.toString(aryb));
//		System.out.println(Arrays.toString(aryw));
		long[] sumb=new long[n+1];
		for (int i=0; i<n; i++) {
			sumb[i+1]=sumb[i]+aryb[n-1-i];
		}
		long[] sumw=new long[m+1];
		for (int i=0; i<m; i++) {
			sumw[i+1]=sumw[i]+aryw[m-1-i];
		}
//		System.out.println(Arrays.toString(sumb));
//		System.out.println(Arrays.toString(sumw));
		long ans=0;
		for (int i=0; i<n+1; i++) {
			if (sumb[i]<0) break;
			for (int j=0; j<m+1; j++) {
				if (i<j) break;
				if (sumw[j]<0) break;
				long x=sumb[i]+sumw[j];
				ans=Math.max(ans, x);
			}
		}
		System.out.println(ans);
	}
}
/*
4 3
8 5 -1 3
3 -2 -4

4 3
5 -10 -2 -5
8 1 4

3 5
-36 -33 -31
12 12 28 24 27
*/
