import java.util.*;
public class Main {
	static final long M=1000000007; //10^9+7
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long ans=1;
		for (int i=1; i<=n; i++) {
//			ans*=i;
//			ans%=M;
			ans=modmul(ans, i);
		}
		System.out.println(ans);
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250421
		return val%M;
	}
	//val*xをMで割った余り
	static long modmul(long val, long x) { //ModFunc20250421
		return mod(val*x);
	}
}
/*
3

10

100000
*/
