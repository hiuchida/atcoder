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
	static long calc(int k, String s) { //abc220_b: k進表現のsを十進数に変換する
		long ans=0;
		for (int i=0; i<s.length(); i++) {
			long v=s.charAt(i)-'0';
			ans*=k;
			ans+=v;
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
