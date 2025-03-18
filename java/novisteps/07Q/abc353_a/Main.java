import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int h0 = sc.nextInt();
		for (int i=1; i<n; i++) {
			int h = sc.nextInt();
			if (h>h0) {
				System.out.println(i+1);
				System.exit(0);
			}
		}
		System.out.println(-1);
	}
}
/*
4
3 2 5 2

3
4 3 2

7
10 5 10 2 10 13 15
*/
