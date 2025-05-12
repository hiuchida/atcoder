import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		long bb=calc(b);
//		System.out.println(b+" "+bb);
		long ans=(a*bb+b)*2;
		System.out.println(ans);
	}
	static long calc(int x) { //abc020_b,abc353_d: x<10^kを満たす最小の10^k
		long v=1;
		while (v<=x) v*=10;
		return v;
	}
}
/*
1 23

999 999
*/
