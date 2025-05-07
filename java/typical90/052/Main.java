import java.util.*;
public class Main {
	static final long M=1000000007; //10^9+7
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=6;
		int n=sc.nextInt();
		int[] sum=new int[n];
		for (int i=0; i<n; i++) {
			int s=0;
			for (int j=0; j<N; j++) {
				int a=sc.nextInt();
				s+=a;
			}
			sum[i]=s;
		}
//		System.out.println(Arrays.toString(sum));
		long ans=1;
		for (int i=0; i<n; i++) ans=modmul(ans, sum[i]);
		System.out.println(ans);
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250427
		val%=M;
		if (val<0) val+=M;
		return val;
	}
	//val*xをMで割った余り
	static long modmul(long val, long x) { //ModFunc20250427
		val=mod(val);
		x=mod(x);
		return mod(val*x);
	}
}
/*
2
1 2 3 5 7 11
4 6 8 9 10 12

1
11 13 17 19 23 29

7
19 23 51 59 91 99
15 45 56 65 69 94
7 11 16 34 59 95
27 30 40 43 83 85
19 23 25 27 45 99
27 48 52 53 60 81
21 36 49 72 82 84
*/
