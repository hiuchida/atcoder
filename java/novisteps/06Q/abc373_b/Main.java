import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		long ans = 0;
		int prev = -1;
		for (char ch='A'; ch<='Z'; ch++) {
			int idx = s.indexOf(ch);
			if (prev < 0) {
				prev = idx;
			} else {
				ans += Math.abs(idx - prev);
				prev = idx;
			}
		}
		System.out.println(ans);
	}
}
/*
ABCDEFGHIJKLMNOPQRSTUVWXYZ

MGJYIZDKSBHPVENFLQURTCWOAX
*/
