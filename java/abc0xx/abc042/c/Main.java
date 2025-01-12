import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		Set<Integer> set = new HashSet<>();
		for (int i=0; i<k; i++) {
			set.add(sc.nextInt());
		}
		while (check(n, set)) {
			n++;
		}
		System.out.println(n);
	}
	static boolean check(int i, Set<Integer> set) {
		while (i>0) {
			int c=i%10;
			if (set.contains(c)) return true;
			i/=10;
		}
		return false;
	}
}
/*
1000 8
1 3 4 5 6 7 8 9

9999 1
0
*/
