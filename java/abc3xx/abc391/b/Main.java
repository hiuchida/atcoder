import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		String[] a=new String[n];
		for (int i=0; i<n; i++) {
			a[i] = sc.next();
		}
		String[] b=new String[n];
		for (int i=0; i<m; i++) {
			b[i] = sc.next();
		}
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				boolean ng=false;
				for (int yy=0; yy<m; yy++) {
					for (int xx=0; xx<m; xx++) {
						if (y+yy>=n || x+xx>=n) {
							ng=true;
							break;
						}
						if (a[y+yy].charAt(x+xx) != b[yy].charAt(xx)) {
							ng=true;
							break;
						}
					}
					if (ng) break;
				}
				if (!ng) {
					System.out.println((y+1) + " " + (x+1));
					System.exit(0);
				}
			}
		}
	}
}
/*
3 2
#.#
..#
##.
.#
#.

2 1
#.
##
.
*/
