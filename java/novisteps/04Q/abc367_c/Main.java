import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static long ans = 0;
	static int n;
	static int k;
	static int[] ary;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		List<Integer> list = new ArrayList<>();
		dfs(0, list);
	}
	static void dfs(int s, List<Integer> list) {
		int i=list.size();
		if (i==n) {
			if (s%k==0) {
				String ss = list.toString();
				ss = conv(ss);
				System.out.println(ss);
			}
			return;
		}
		for (int j=1; j<=ary[i]; j++) {
			list.add(j);
			dfs(s+j, list);
			list.remove(list.size()-1);
		}
	}
	static String conv(String s) { //abc270_c,abc293_b,abc367_c,abc368_a,abc373_d,abc388_d: List等のtoString()の文字列からカッコとカンマを省く
		return s.replace("[", "").replace("]", "").replaceAll(",", "");
	}
}
/*
3 2
2 1 3

1 2
1

5 5
2 3 2 3 2
*/
