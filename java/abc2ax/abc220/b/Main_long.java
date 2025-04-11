import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		long a = sc.nextLong();
		long b = sc.nextLong();
		long aa=calc(k, a);
		long bb=calc(k, b);
		long ans = aa*bb;
		System.out.println(ans);
	}
	static long calc(int k, long x) { //abc220_b: k進表現のxを十進数に変換する
		long ans=0;
		long w=1;
		while (x>0) {
			long v=x%10;
			ans+=v*w;
			w*=k;
			x/=10;
		}
		return ans;
	}
}
/*
2
1011 10100

7
123 456
*/
/*
2
11000011010100000 11000011010100000
*/
