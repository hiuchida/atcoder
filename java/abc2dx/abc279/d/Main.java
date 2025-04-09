import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long a=sc.nextLong();
		long b=sc.nextLong();
		long lt=0;
		long rt=(long)1e18;
		long c=0;
		while (lt+100<rt) {
			c++;
			long mid1=(long)((double)lt*2+(double)rt)/3;
			long mid2=(long)((double)lt+(double)rt*2)/3;
			double x1=func(a, b, mid1);
			double x2=func(a, b, mid2);
//			System.out.println(c+": "+lt+" "+rt+" "+mid1+"="+x1+" "+mid2+"="+x2);
			if (x1>x2) {
				lt=mid1;
			} else {
				rt=mid2;
			}
		}
		double ans=Long.MAX_VALUE;
		for (long i=lt; i<=rt; i++) {
			double x=func(a, b, i);
			ans=Math.min(ans, x);
		}
		System.out.println(ans);
	}
	static double func(long a, long b, long g) {
		double x=(double)b*g+a/Math.sqrt(1+g);
		return x;
	}
}
/*
10 1

5 10

1000000000000000000 100
*/
