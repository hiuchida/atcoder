import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long a=sc.nextLong();
		long b=sc.nextLong();
		long ans=0;
		while (a!=b) {
			if (a>b) {
				long c=(a-1)/b;
				a-=b*c;
				ans+=c;
//				a=a-b;
//				ans++;
			} else if (a<b) {
				long c=(b-1)/a;
				b-=a*c;
				ans+=c;
//				b=b-a;
//				ans++;
			}
//			System.out.println(a+" "+b+" "+ans);
		}
		System.out.println(ans);
	}
}
/*
3 8

1234567890 1234567890

1597 987
*/
/*
12345678901234567 1

12345678901234567 12345678
*/
