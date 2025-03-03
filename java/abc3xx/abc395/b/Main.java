import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		boolean[][] ary=new boolean[n+1][n+1];
		for (int i=1; i<=n; i++) {
			int j=n+1-i;
			if (i<=j) {
				boolean bOdd=i%2==1;
				for (int y=i; y<=j; y++) {
					for (int x=i; x<=j; x++) {
						ary[y][x]=bOdd;
					}
				}
			}
		}
		for (int y=1; y<=n; y++) {
			for (int x=1; x<=n; x++) {
				System.out.print(ary[y][x]?'#':'.');
			}
			System.out.println();
		}
	}
}
/*
11

5

8
*/
