import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
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
//			long base=calc(ary[i]);
//			long x=sum*base; //ng

//			long sum0=sum%M; //fix
//			long base=calc(ary[i]);
//			base%=M; //fix
//			long x=sum0*base; //fix
			
			long base=calc(ary[i]);
			long x=modmul(sum, base);
			long y=(long)ary[i]*i;
			long z=modadd(x, y);
//			System.out.println(sum+" "+base+" "+x+" "+y+" "+z+" "+z0);
			ans=modadd(ans, z);
		}
		System.out.println(ans);
	}
	static long calc(int x) { //abc353_d: x<10^kを満たす最小の10^k
		long v=1;
		while (v<=x) v*=10;
		return v;
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250423
		val%=M;
		if (val<0) val+=M;
		return val;
	}
	//val+xをMで割った余り
	static long modadd(long val, long x) { //ModFunc20250423
		val=mod(val);
		x=mod(x);
		return mod(val+x);
	}
	//val*xをMで割った余り
	static long modmul(long val, long x) { //ModFunc20250423
		val=mod(val);
		x=mod(x);
		return mod(val*x);
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
240946599
↓ok
174754156
*/
