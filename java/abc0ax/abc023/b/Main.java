import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		String x="b";
		int ans=0;
		if (s.equals(x)) print(ans);
		while (true) {
			if (s.length() < x.length()) print(-1);
			ans++;
			if (ans%3==1) {
				x="a"+x+"c";
			} else if (ans%3==2) {
				x="c"+x+"a";
			} else {
				x="b"+x+"b";
			}
			if (s.equals(x)) print(ans);
		}
	}
	static void print(int ans) {
		System.out.println(ans);
		System.exit(0);
	}
}
/*
3
abc

6
abcabc

7
atcoder

19
bcabcabcabcabcabcab
*/
