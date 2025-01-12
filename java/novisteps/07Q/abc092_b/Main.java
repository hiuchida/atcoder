import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int d = sc.nextInt();
		int x = sc.nextInt();
		int ans = 0;
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			int c=1+(d-1)/a;
			ans+=c;
		}
		ans+=x;
		System.out.println(ans);
	}
}
/*
3
7 1
2
5
10

2
8 20
1
10

5
30 44
26
18
81
18
6
*/
