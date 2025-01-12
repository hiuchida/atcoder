import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ary = new int[256];
		String s = sc.next();
		for (int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			ary[ch] = i;
		}
		long ans = 0;
		for (char ch='A'; ch<'Z'; ch++) {
			int i = ary[ch];
			int j = ary[ch+1];
			ans += Math.abs(i - j);
		}
		System.out.println(ans);
	}
}
