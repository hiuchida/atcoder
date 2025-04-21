import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	public static void main(String[] args) {
		for (int i=2; i<=(int)2e5; i++) {
			long a1=main1(i);
			long a0=main0(i);
			if (a1!=a0) {
				System.out.println(i+" "+a1+" "+a0);
				System.exit(0);
			}
		}
	}
	public static long main1(int n) {
		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
		int[] ary=new int[n];
		long sum=0;
		for (int i=0; i<n; i++) {
			int a=(int)1e9;
			ary[i]=a;
			sum+=ary[i];
		}
//		System.out.println(Arrays.toString(ary));
		long ans=0;
		for (int i=n-1; i>0; i--) {
			sum-=ary[i];
			long sum0=sum%M;
			long base=calcBase(ary[i]);
			base%=M;
			long x=sum0*base;
			long y=(long)ary[i]*i;
//			x%=M;
//			y%=M;
			long z=x+y;
			long z0=z%M;
//			System.out.println(sum+" "+base+" "+x+" "+y+" "+z+" "+z0);
			ans+=z0;
			ans%=M;
		}
//		System.out.println(ans);
		return ans;
	}
	public static long main0(int n) {
		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
		int[] ary=new int[n];
		long sum=0;
		for (int i=0; i<n; i++) {
			int a=(int)1e9;
			ary[i]=a;
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
//		System.out.println(ans);
		return ans;
	}
	static long calcBase(int x) {
		long v=1;
		while (v<=x) v*=10;
		return v;
	}
}
/*
2 174754156 -757297754
*/
