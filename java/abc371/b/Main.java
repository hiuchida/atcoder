import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		boolean[] flg = new boolean[n+1];
		for (int i=0; i<m; i++) {
			int a = sc.nextInt();
			String b = sc.next();
			if ("M".equals(b)) {
				if (!flg[a]) {
					System.out.println("Yes");
					flg[a] = true;
				} else {
					System.out.println("No");
				}
			} else {
				System.out.println("No");
			}
		}
	}
}
