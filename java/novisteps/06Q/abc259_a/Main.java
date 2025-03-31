import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int x = sc.nextInt();
		int t = sc.nextInt();
		int d = sc.nextInt();
		if (x<=m) System.out.println(t);
		else {
			int ans=t-(x-m)*d;
			System.out.println(ans);
		}
	}
}
/*
38 20 17 168 3

1 0 1 3 2

100 10 100 180 1
*/
