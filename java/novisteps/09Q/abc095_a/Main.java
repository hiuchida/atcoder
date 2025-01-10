import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int ans = 700;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'o') ans += 100;
		}
		System.out.println(ans);
	}
}
/*
oxo

ooo

xxx
*/
