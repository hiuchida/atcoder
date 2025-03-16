import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		TreeSet<String> set = new TreeSet<>();
		for (int i=0; i<n; i++) {
			int l = sc.nextInt();
			StringBuilder sb=new StringBuilder();
			sb.append(l);
			for (int j=0; j<l; j++) {
				int a = sc.nextInt();
				sb.append(",").append(a);
			}
			set.add(sb.toString());
		}
		int ans = set.size();
		System.out.println(ans);
	}
}
/*
4
2 1 2
2 1 1
2 2 1
2 1 2

5
1 1
1 1
1 2
2 1 1
3 1 1 1

1
1 1
*/
