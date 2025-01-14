import java.util.*;
public class Main {
	static String s;
	static int n;
	static TreeSet<String> set = new TreeSet<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		s = sc.next();
		n=s.length();
		int k = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		boolean[] flag = new boolean[n];
		dfs(0, sb, flag);
//		System.out.println(set);
		List<String> list = new ArrayList<>(set);
		System.out.println(list.get(k-1));
	}
	static void dfs(int i, StringBuilder sb, boolean[] flag) {
		if (i==n) {
			set.add(sb.toString());
			return;
		}
		for (int j=0; j<n; j++) {
			if (!flag[j]) {
				sb.append(s.charAt(j));
				flag[j]=true;
				dfs(i+1, sb, flag);
				sb.deleteCharAt(sb.length()-1);
				flag[j]=false;
			}
		}
	}
}
/*
aab 2

baba 4

ydxwacbz 40320
*/
