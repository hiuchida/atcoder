import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		int ans = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '/') {
				int d = 1;
				for (; true; d++) {
					int l = i - d;
					int r = i + d;
					if (l < 0 || n-1 < r) break;
					if (s.charAt(l) != '1') break;
					if (s.charAt(r) != '2') break;
				}
				int e = (d-1) * 2 + 1;
				ans = Math.max(ans, e);
			}
		}
		System.out.println(ans);
	}
}
