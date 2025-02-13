import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Set<Integer> set = new HashSet<>();
		for (int i=0; i<m; i++) {
			int a = sc.nextInt();
			set.add(a);
		}
		System.out.println(n-m);
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=n; i++) {
			if (!set.contains(i)) {
				if (sb.length() > 0) sb.append(" ");
				sb.append(i);
			}
		}
		System.out.println(sb);
	}
}
/*
10 3
3 9 2

6 6
1 3 5 2 4 6

9 1
9
*/
