import java.util.*;
public class Main {
	static int n;
	static int m;
	static String[] aa;
	static String[] ab;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		aa=new String[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.next();
		}
		ab=new String[m];
		for (int i=0; i<m; i++) {
			ab[i]=sc.next();
		}
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				check(y, x);
			}
		}
		System.out.println("No");
	}
	static void check(int y, int x) {
		if (y+m>=n || x+m>=n) return;
		for (int i=0; i<m; i++) {
			String a=aa[y+i];
			String b=ab[i];
			for (int j=0; j<m; j++) {
				if (a.charAt(x+j)!=b.charAt(j)) return;
			}
		}
		System.out.println("Yes");
		System.exit(0);
	}
}
/*
3 2
#.#
.#.
#.#
#.
.#

4 1
....
....
....
....
#
*/
