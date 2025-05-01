import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		Set<Integer> set = new HashSet<>();
		for (int i=0; i<n; i++) {
			if (set.contains(ary[i])) set.remove(ary[i]);
			else set.add(ary[i]);
		}
		System.out.println(set.size());
	}
}
/*
3
6
2
6

4
2
5
5
2

6
12
22
16
22
18
12
*/
