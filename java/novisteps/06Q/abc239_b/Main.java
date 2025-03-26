import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long x = sc.nextLong();
		long ans=0;
		if (x>=0) ans=x/10;
		else {
			x=-x;
			x+=9;
			x/=10;
			x=-x;
			ans=x;
		}
		System.out.println(ans);
	}
}
/*
47

-24

50

-30

987654321987654321
*/
