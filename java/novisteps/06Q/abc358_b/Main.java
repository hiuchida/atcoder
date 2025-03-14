import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a = sc.nextInt();
		int now=0;
		for (int i=0; i<n; i++) {
			int t = sc.nextInt();
			if (now<t) now=t;
			now+=a;
			System.out.println(now);
		}
	}
}
/*
3 4
0 2 10

3 3
1 4 7

10 50000
120190 165111 196897 456895 540000 552614 561627 743796 757613 991216
*/
