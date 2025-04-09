import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long a=sc.nextLong();
		long b=sc.nextLong();
		long x=sc.nextLong();
		long aa=ceil(a, x);
		long bb=floor(b, x);
//		System.out.println(aa+" "+bb);
		if (aa>bb) System.out.println(0);
		else System.out.println((bb-aa)/x+1);
//		long aa=(a-1)/x;
//		long bb=b/x;
//		System.out.println(aa+" "+bb);
//		if (a==0) System.out.println(bb+1);
//		else System.out.println(bb-aa);
	}
	static long ceil(long a, long b) { //abc048_b: a以上の最小のbの倍数
		if (a < 0) {
			return -1 * floor(-a, b);
		}
		return ((a + b - 1) / b) * b;
	}
	static long floor(long a, long b) { //abc048_b: a以下の最大のbの倍数
		if (a < 0) {
			return -1 * ceil(-a, b);
		}
		return (a / b) * b;
	}
}
/*
4 8 2

0 5 1

9 9 2

1 1000000000000000000 3
*/
