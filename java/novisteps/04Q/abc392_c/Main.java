import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ap = new int[n+1];
		for (int i=1; i<=n; i++) {
			ap[i] = sc.nextInt();
		}
		int[] aq = new int[n+1];
		for (int i=1; i<=n; i++) {
			aq[i] = sc.nextInt();
		}
		int[] ar = new int[n+1];
		for (int i=1; i<=n; i++) {
			ar[aq[i]] = i;
		}
//		System.out.println(Arrays.toString(ar));
		List<String> list = new ArrayList<>();
		for (int i=1; i<=n; i++) {
			list.add(""+aq[ap[ar[i]]]);
		}
		System.out.println(String.join(" ", list));
	}
}
/*
4
4 3 2 1
2 3 1 4

10
2 6 4 3 7 8 9 10 1 5
1 4 8 2 10 5 7 3 9 6
*/
