import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		long aa=calc(k, a);
		long bb=calc(k, b);
		long ans = aa*bb;
		System.out.println(ans);
	}
	static long calc(int k, int x) {
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