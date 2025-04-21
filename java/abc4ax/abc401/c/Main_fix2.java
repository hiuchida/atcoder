import java.util.*;
public class Main {
	static final long M=1000000000; //10^9
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		if (n<k) {
			System.out.println(1);
			System.exit(0);
		}
		long[] ary=new long[n+1];
		long sum=0;
		for (int i=0; i<k; i++) {
			ary[i]=1;
			sum+=ary[i];
		}
		for (int i=k; i<=n; i++) {
			ary[i]=sum;
//			sum-=ary[i-k]; //ng
//			sum+=M-ary[i-k]; //fix
//			sum+=ary[i];
//			sum%=M;
			sum=modadd(sum, -ary[i-k]);
			sum=modadd(sum, ary[i]);
		}
//		System.out.println(Arrays.toString(ary));
		long ans=ary[n];
		System.out.println(ans);
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250422
		val%=M;
		if (val<0) val+=M;
		return val;
	}
	//val+xをMで割った余り
	static long modadd(long val, long x) { //ModFunc20250422
		return mod(val+x);
	}
}
/*
4 2

10 20

1000000 500000
*/
/*
10 9

10 10

10 11

10 12

100 10

1000000 100000

1000000 1000000
*/
