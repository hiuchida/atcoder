import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		n%=12;
		double n1=n/12.0*360+m/60.0/12.0*360;
		double m1=m/60.0*360;
//		System.out.println(n1+" "+m1);
		double ans=Math.abs(n1-m1);
		if (ans>180) ans=360-ans;
		System.out.println(ans);
	}
}
/*
15 0

3 17

0 0

6 0

23 59
*/
