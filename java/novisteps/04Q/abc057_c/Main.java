import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		int ans=Integer.MAX_VALUE;
		for (long a=1; a*a<=n; a++) {
			if (n%a!=0) continue;
			long b=n/a;
			int x=Math.max(calc(a), calc(b));
			ans=Math.min(ans, x);
		}
		System.out.println(ans);
	}
	static int calc(long n) { //abc057_c: nの桁数
		int ans=0;
		while (n>0) {
			ans++;
			n/=10;
		}
		return ans;
	}
}
/*
10000

1000003

9876543210
*/
