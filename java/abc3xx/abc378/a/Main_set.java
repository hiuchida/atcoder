import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Set<Integer> set = new HashSet<>();
		int ans = 0;
		for (int i = 0; i < 4; i++) {
			int a = sc.nextInt();
			if (set.contains(a)) {
				set.remove(a);
				ans++;
			} else {
				set.add(a);
			}
		}
		System.out.println(ans);
	}
}
