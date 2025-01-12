import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ary = new int[5];
		for (int i = 0; i < 4; i++) {
			int a = sc.nextInt();
			ary[a]++;
		}
		int ans = 0;
		for (int i = 0; i < ary.length; i++) {
			ans += ary[i] / 2;
		}
		System.out.println(ans);
	}
}
