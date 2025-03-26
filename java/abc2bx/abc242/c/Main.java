import java.util.*;
public class Main {
	static final long M=998244353;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] ary=new long[10];
		long[] ans2=calc2(ary);
		ary=ans2;
		for (int i=3; i<=n; i++) {
			long[] ans3=calc(ary);
			ary=ans3;
		}
//		System.out.println(Arrays.toString(ary));
		long ans=sum(ary);
		System.out.println(ans);
	}
	static long mod(long val) {
		return val%M;
	}
	static long sum(long[] ary) {
		long ans=0;
		for (int i=1; i<=9; i++) {
			ans+=ary[i];
			ans=mod(ans);
		}
		return ans;
	}
	static long[] calc(long[] ary) {
		long[] ans=new long[10];
		for (int i=1; i<=9; i++) {
			if (i==1) {
				ans[i+1]+=ary[i];
				ans[i+1]=mod(ans[i+1]);
			} else if (i==9) {
				ans[i-1]+=ary[i];
				ans[i-1]=mod(ans[i-1]);
			} else {
				ans[i-1]+=ary[i];
				ans[i-1]=mod(ans[i-1]);
				ans[i+1]+=ary[i];
				ans[i+1]=mod(ans[i+1]);
			}
			ans[i]+=ary[i];
			ans[i]=mod(ans[i]);
		}
		return ans;
	}
	static long[] calc2(long[] ary) {
		long[] ans=new long[10];
		for (int i=1; i<=9; i++) {
			if (i==1) {
				ans[i]++;
				ans[i+1]++;
			} else if (i==9) {
				ans[i-1]++;
				ans[i]++;
			} else {
				ans[i-1]++;
				ans[i]++;
				ans[i+1]++;
			}
		}
		return ans;
	}
}
/*
4

2

1000000
*/
