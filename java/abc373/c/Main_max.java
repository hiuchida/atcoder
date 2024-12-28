import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ma = Integer.MIN_VALUE;
		int mb = Integer.MIN_VALUE;
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ma = Math.max(ma, a);
		}
		for (int i=0; i<n; i++) {
			int b = sc.nextInt();
			mb = Math.max(mb, b);
		}
		long ans = ma + mb;
		System.out.println(ans);
	}
}
