import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		long aa=calc(1, a);
		long bb=calc(1, b);
		long cc=calc(1, c);
		long ans=modmul(aa, bb);
		ans=modmul(ans, cc);
		System.out.println(ans);
	}
	static long calc(int a, int b) { //abc181_b,abc369_c,arc107_a: 初項a、末項bの等差数列の和
		long n=b-a+1;
		long ans=n*(a+b)/2;
		return ans;
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
1 2 3

1000000000 987654321 123456789
*/
