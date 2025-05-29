import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] cnt=new int[4];
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			cnt[a]++;
		}
//		System.out.println(Arrays.toString(cnt));
		long c1=calc_c(cnt[1], 2);
		long c2=calc_c(cnt[2], 2);
		long c3=calc_c(cnt[3], 2);
		long ans=c1+c2+c3;
		System.out.println(ans);
	}
	static long calc_c(int n, int k) {
		long ans=1;
		for (int i=0; i<k; i++) {
			ans*=n-i;
		}
		for (int i=1; i<=k; i++) {
			ans/=i;
		}
		return ans;
	}
}
/*
6
1 3 2 1 1 2
*/
