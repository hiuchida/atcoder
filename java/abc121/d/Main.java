import java.util.*;
public class Main {
	static void DEBUG(Object x) {
//		System.out.println(x);
	}
	static void DEBUG(long x) {
		DEBUG(""+x);
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long a = sc.nextLong();
		long b = sc.nextLong();
		long ans = 0;
		if (a%2==1) { //奇数
			ans=a;
			a++;
		}
		DEBUG(ans + " " + a + " " + b);
		long dif = (b-a)/4*4;
		a+=dif;
		DEBUG(ans + " " + a + " " + b);
		for (long i=a; i<=b; i++) {
			ans ^= i;
			DEBUG(ans + " " + a + " " + b + " " + i);
		}
		System.out.println(ans);
	}
}
