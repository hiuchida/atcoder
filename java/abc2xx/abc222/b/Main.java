import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int p = sc.nextInt();
		int ans = 0;
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			if (a<p) ans++;
		}
		System.out.println(ans);
	}
}
/*
4 50
80 60 40 0

3 90
89 89 89

2 22
6 37
*/
