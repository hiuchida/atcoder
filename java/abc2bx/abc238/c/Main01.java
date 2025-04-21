import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long ans=0;
		ans=calc(ans, n);
		System.out.println(ans);
	}
	static long calc(long ans, long n) {
		if (n<10) {
			return calc1(ans, n);
		} else if (n<100) {
			return calc2(ans, n);
		} else if (n<1000) {
			return calc3(ans, n);
		}
		return 0;
	}
	static long calc1(long ans, long n) {
		return ans+n*(n+1)/2;
	}
	static long calc2(long ans, long n) {
		ans+=calc1(ans, 9);
		n-=10-1;
		return ans+n*(n+1)/2;
	}
	static long calc3(long ans, long n) {
		ans+=calc2(ans, 99);
		n-=100-1;
		return ans+n*(n+1)/2;
	}
}
/*
16

238

999999999999999999
*/
