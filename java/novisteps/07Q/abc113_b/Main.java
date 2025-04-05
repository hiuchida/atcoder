import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int t=sc.nextInt();
		int a=sc.nextInt();
		double d=Integer.MAX_VALUE;
		int ans=0;
		for (int i=1; i<=n; i++) {
			int h=sc.nextInt();
			double x=t-h*0.006;
			double dd=Math.abs(x-a);
			if (d>dd) {
				ans=i;
				d=dd;
			}
//			System.out.println(ans+" "+d+" "+x+" "+dd);
		}
		System.out.println(ans);
	}
}
/*
2
12 5
1000 2000

3
21 -11
81234 94124 52141
*/
