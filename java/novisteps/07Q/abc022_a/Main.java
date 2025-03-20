import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int s = sc.nextInt();
		int t = sc.nextInt();
		int w = sc.nextInt();
		int ans=0;
		if (s<=w && w<=t) ans++;
		for (int i=2; i<=n; i++) {
			int a = sc.nextInt();
			w+=a;
			if (s<=w && w<=t) ans++;
		}
		System.out.println(ans);
	}
}
/*
5 60 70
50
10
10
10
10

5 50 100
120
-10
-20
-30
70
*/
