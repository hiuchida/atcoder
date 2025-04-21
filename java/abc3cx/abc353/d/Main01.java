import java.util.*;
public class Main {
	static final long M=998244353L;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		long sum=0;
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			sum+=ary[i];
		}
//		System.out.println(Arrays.toString(ary));
		long ans=0;
		for (int i=n-1; i>0; i--) {
			sum-=ary[i];
			long base=calcBase(ary[i]);
			long x=sum*base;
			long y=(long)ary[i]*i;
//			x%=M;
//			y%=M;
			long z=x+y;
			long z0=z%M;
//			System.out.println(sum+" "+base+" "+x+" "+y+" "+z+" "+z0);
			ans+=z0;
			ans%=M;
		}
		System.out.println(ans);
	}
	static long calcBase(int x) {
		long v=1;
		while (v<=x) v*=10;
		return v;
	}
}
/*
3
3 14 15

5
1001 5 1000000 1000000000 100000
*/
/*
2
1000000000 1000000000
↓ng
-757297754
*/
