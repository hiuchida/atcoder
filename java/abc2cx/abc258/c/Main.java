import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		String s = sc.next();
		int head=0;
		for (int i=0; i<q; i++) {
			int t = sc.nextInt();
			int x = sc.nextInt();
			if (t==1) {
				head=(head+n-x)%n;
			} else {
				int pos=(head+x-1)%n;
				System.out.println(s.charAt(pos));
			}
		}
	}
}
/*
3 3
abc
2 2
1 1
2 2

10 8
dsuccxulnl
2 4
2 7
1 2
2 7
1 1
1 2
1 3
2 5
*/
