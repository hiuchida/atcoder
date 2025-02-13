import java.util.*;
public class Main_abc382_d {
	static int n;
	static int m;
	static List<String> ans = new ArrayList<>();
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		dfs(1, 1, "");
//		for (int a = 1; a <= m; a++) {
//			for (int b = a + 10; b <= m; b++) {
//				for (int c = b + 10; c <= m; c++) {
//					ans.add("" + a + " " + b + " " + c);
//				}
//			}
//		}
		System.out.println(ans.size());
		for (String s : ans) {
			System.out.println(s);
		}
	}

	static void dfs(int depth, int st, String str) {
		for (int a = st; a <= m; a++) {
			if (a + (n - depth) * 10 > m) {
				break;
			}
			String s;
			if (str.length() == 0) {
				s = "" + a;
			} else {
				s = str + " " + a;
			}
			if (depth < n) {
				dfs(depth + 1, a + 10, s);
			} else {
				ans.add(s);
			}
		}
	}
} 
/*
12 120
*/
