import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int r=sc.nextInt();
		long ans=calc_c(n, r);
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
6 2
*/
