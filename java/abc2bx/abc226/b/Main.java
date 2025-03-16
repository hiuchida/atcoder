import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] ary = new String[n];
		for (int i=0; i<n; i++) {
			int l = sc.nextInt();
			StringBuilder sb=new StringBuilder();
			sb.append(l);
			for (int j=0; j<l; j++) {
				int a = sc.nextInt();
				sb.append(",").append(a);
			}
			ary[i]=sb.toString();
		}
		Arrays.sort(ary);
		int ans = n;
		for (int i=1; i<n; i++) {
			if (ary[i-1].equals(ary[i])) ans--;
		}
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
