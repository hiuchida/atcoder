import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = 0;
		int t0 = 0;
		for (int i = 0; i < n; i++) {
			int t = sc.nextInt();
			int v = sc.nextInt();
			r = Math.max(r - (t - t0), 0) + v;
			t0 = t;
		}
		System.out.println(r);
	}
}
