import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long s = sc.nextLong();
		int[] ary = new int[n];
		for (int i = 0; i < n; i++) {
			ary[i] = sc.nextInt();
		}
		Set<Long> set = new HashSet<>();
		long a = 0;
		for (int i = 0; i < n; i++) {
			a += ary[i];
			set.add(a);
		}
		s %= a;
		for (int i = 0; i < n; i++) {
			a += ary[i];
			set.add(a);
		}
		for (long v : set) {
			if (set.contains(v + s)) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
