import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		long m=sc.nextLong();
		long ans=0;
		if (n*2>m) {
			ans=m/2;
			System.out.println(ans);
			System.exit(0);
		}
		ans+=n;
		m-=n*2;
		ans+=m/4;
		System.out.println(ans);
	}
}
/*
1 6

12345 678901
*/
