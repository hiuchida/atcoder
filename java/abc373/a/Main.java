import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long ans = 0;
		for (int i=0; i<12; i++) {
			String s = sc.next();
			if (s.length() == i+1) ans++;
		}
		System.out.println(ans);
	}
}
