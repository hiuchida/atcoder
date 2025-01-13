import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] aa = new int[n];
		for (int i=0; i<aa.length; i++) {
			aa[i] = sc.nextInt();
		}
		int[] ab = new int[m];
		for (int i=0; i<ab.length; i++) {
			ab[i] = sc.nextInt();
		}
		Arrays.sort(aa);
		Arrays.sort(ab);
		int ans=Integer.MAX_VALUE;
		for (int i=0,j=0; true; ) {
			if (i>=n || j>=m) break;
			ans=Math.min(ans, Math.abs(aa[i]-ab[j]));
			if (aa[i]<ab[j]) {
				if (i<n) i++;
				else j++;
			} else if (aa[i]>ab[j]) {
				if (j<m) j++;
				else i++;
			} else {
				break;
			}
		}
		System.out.println(ans);
	}
}
/*
*/
