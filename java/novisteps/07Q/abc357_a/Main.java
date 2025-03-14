import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int ans=0;
		for (int i=0; i<n; i++) {
			int h = sc.nextInt();
			if (h<=m) {
				m-=h;
				ans++;
			} else break;
		}
		System.out.println(ans);
	}
}
/*
5 10
2 3 2 5 3

5 10
2 3 2 3 5

1 5
1
*/
