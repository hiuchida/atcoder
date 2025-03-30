import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i=0; i<t; i++) {
			int n = sc.nextInt();
			int ans=0;
			for (int j=0; j<n; j++) {
				int a=sc.nextInt();
				if (a%2==1) ans++;
			}
			System.out.println(ans);
		}
	}
}
/*
4
3
1 2 3
2
20 23
10
6 10 4 1 5 9 8 6 5 1
1
1000000000
*/
