import java.util.*;
public class Main {
	static String join(String delimiter, int[] ary) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<ary.length; i++) {
			if (i>0) sb.append(delimiter);
			sb.append(ary[i]);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		int[] ans = new int[n];
		int j=0;
		for (int i=n-k; i<n; i++) {
			ans[j++] = ary[i];
		}
		for (int i=0; i<n-k; i++) {
			ans[j++] = ary[i];
		}
//		String s = Arrays.toString(ans);
//		s = s.replace("[", "").replace("]", "").replaceAll(",", "");
		String s = join(" ", ans);
		System.out.println(s);
	}
}
