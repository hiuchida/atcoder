import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		String s = sc.next();
		List<Integer> list = new ArrayList<>();
		int ok = 0;
		char prev = s.charAt(0);
		if (prev == 'O') {
			ok++;
		}
		for (int i=1; i<s.length(); i++) {
			char ch = s.charAt(i);
			if (prev == ch) {
				if (ch == 'O') {
					ok++;
				} else {
				}
			} else {
				if (ch == 'O') {
					ok++;
				} else {
					list.add(ok);
					ok = 0;
				}
			}
			prev = ch;
		}
		if (ok > 0) {
			list.add(ok);
		}
//		System.out.println(list);
		int ans = 0;
		for (int v : list) {
			ans += v / k;
		}
		System.out.println(ans);
	}
}
