import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		String a = sc.next();
		String b = sc.next();
		long aa=calc(k, a);
		long bb=calc(k, b);
		long ans = aa*bb;
		System.out.println(ans);
	}
	static long calc(int k, String x) { //abc220_b: k進表現のsを十進数に変換する
		long ans=0;
		long w=1;
		while (x.length()>0) {
			char ch=x.charAt(x.length()-1);
			long v=(ch-'0');
			ans+=v*w;
			w*=k;
			x=x.substring(0, x.length()-1);
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
