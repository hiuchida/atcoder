import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		List<Integer> list = new ArrayList<>();
		for (int i=n-k; i<n; i++) {
			list.add(ary[i]);
		}
		for (int i=0; i<n-k; i++) {
			list.add(ary[i]);
		}
		String s = list.toString();
		s = s.replace("[", "").replace("]", "").replaceAll(",", "");
		System.out.println(s);
	}
}
/*
5 3
1 2 3 4 5

6 2
1 2 1 2 1 2
*/
