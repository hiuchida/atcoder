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
//			int sum=0;
//			for (int j=i-k; j<i; j++) {
//				sum+=ary[j];
//				sum%=M;
//			}
			ary[i]=sum;
//			sum-=ary[i-k];
			sum+=M-ary[i-k];
			sum+=ary[i];
//			long c=1+Math.abs(sum/M);
//			sum+=M*c;
			sum%=M;
		}
//		System.out.println(Arrays.toString(ary));
		long ans=ary[n]%M;
		System.out.println(ans);
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
