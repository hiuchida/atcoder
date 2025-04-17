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
		s = conv(s);
		System.out.println(s);
	}
	static String conv(String s) { //abc270_c,abc293_b,abc367_c,abc368_a,abc373_d,abc388_d: List等のtoString()の文字列からカッコとカンマを省く
		return s.replace("[", "").replace("]", "").replaceAll(",", "");
	}
}
/*
5 3
1 2 3 4 5

6 2
1 2 1 2 1 2
*/
