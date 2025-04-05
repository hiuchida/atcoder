import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int M=380000;
		final long R=(long)1e8;
		int n=sc.nextInt();
		long ans=0;
		for (int i=1; i<=n; i++) {
			String x=sc.next();
			String u=sc.next();
			if ("JPY".equals(u)) {
				long v1=Integer.parseInt(x);
				long v2=v1*R;
				ans+=v2;
//				System.out.println(u+" "+v1+" "+v2+" "+ans);
			} else {
				int idx=x.indexOf(".");
				long v1=Integer.parseInt(x.substring(0, idx));
				long v2=Integer.parseInt(x.substring(idx+1));
				long v3=v1*R+v2;
				long v4=v3*380000;
				ans+=v4;
//				System.out.println(u+" "+v1+" "+v2+" "+v3+" "+v4+" "+ans);
			}
		}
		ans/=R;
		System.out.println(ans);
	}
}
/*
2
10000 JPY
0.10000000 BTC

3
100000000 JPY
100.00000000 BTC
0.00000001 BTC
*/
