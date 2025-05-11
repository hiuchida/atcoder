import java.util.*;
public class Main {
	static final long M=1000000007; //10^9+7
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		int cnt=0;
		if (n%2==1) {
			if (ary[0]!=0) {
				System.out.println(0);
				System.exit(0);
			}
			for (int i=1; i<n; i+=2) {
				if (ary[i]!=i+1 || ary[i+1]!=i+1) {
					System.out.println(0);
					System.exit(0);
				}
				cnt++;
			}
		} else {
			for (int i=0; i<n; i+=2) {
				if (ary[i]!=i+1 || ary[i+1]!=i+1) {
					System.out.println(0);
					System.exit(0);
				}
				cnt++;
			}
		}
		long ans=modpow(2, cnt, M);
		System.out.println(ans);
	}
	//a^n mod mを計算する
	static long modpow(long a, long n, long m) { //ModFunc20250427
		long ans = 1;
		while (n > 0) {
			if ((n & 1) > 0) ans = ans * a % m;
			a = a * a % m;
			n >>= 1;
		}
		return ans;
	}
}
/*
5
2 4 4 0 2

7
6 4 0 2 4 0 2

8
7 5 1 1 7 3 5 3
*/
