import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=sc.nextInt();
		}
		int[] sum=new int[n+1];
		for (int i=n; i>0; i--) {
			if (i==ary[i]) {
				sum[i]++;
			}
			if (i<n) sum[i]+=sum[i+1];
		}
//		System.out.println(Arrays.toString(sum));
		long ans=0;
		for (int i=1; i<=n; i++) {
			if (i==ary[i]) {
				if (i<n) {
					ans+=sum[i+1];
				}
//				for (int j=i+1; j<=n; j++) {
//					int min=Math.min(ary[i], ary[j]);
//					int max=Math.max(ary[i], ary[j]);
//					if (i==min && j==max) {
//						ans++;
//						System.out.println(ans+" "+i+" "+min+" "+j+" "+max);
//					}
//				}
			} else {
				int a=ary[i];
				if (i<a && i==ary[a]) {
					ans++;
//					System.out.println(ans+" "+i+" "+a);
				}
			}
		}
		System.out.println(ans);
	}
}
/*
4
1 3 2 4

10
5 8 2 2 1 6 7 2 9 10
*/
