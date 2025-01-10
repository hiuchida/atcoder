import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int x = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		List<String> list = new ArrayList<>();
		for (int i=0; i<k; i++) {
			list.add(""+ary[i]);
		}
		list.add(""+x);
		for (int i=k; i<n; i++) {
			list.add(""+ary[i]);
		}
		System.out.println(String.join(" ", list));
	}
}
/*
4 3 7
2 3 5 11

1 1 100
100

8 8 3
9 9 8 2 4 4 3 5
*/
