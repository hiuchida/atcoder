import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		List<String> list = new ArrayList<>();
		dfs(n, m, list, 1, "");
//		for (int a = 1; a <= m; a++) {
//			for (int b = a + 10; b <= m; b++) {
//				for (int c = b + 10; c <= m; c++) {
//					list.add("" + a + " " + b + " " + c);
//				}
//			}
//		}
		System.out.println(list.size());
		for (String s : list) {
			System.out.println(s);
		}
	}

	static void dfs(int n, int m, List<String> list, int st, String str) {
		for (int a = st; a <= m; a++) {
			if (a + (n - 1) * 10 > m) {
				break;
			}
			String s;
			if (str.length() == 0) {
				s = "" + a;
			} else {
				s = str + " " + a;
			}
			if (n > 1) {
				dfs(n - 1, m, list, a + 10, s);
			} else {
				list.add(s);
			}
		}
	}
	/*
	 * dfs(3, 1)
	 * dfs(2, 11)
	 * dfs(1, 21)
	 */

} // 3 23
