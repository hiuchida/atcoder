import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int t = sc.nextInt();
		int[] col=new int[n];
		int[] row=new int[n];
		int slashu=0;
		int slashd=0;
		for (int i=1; i<=t; i++) {
			int a = sc.nextInt();
			int y=(a-1)/n;
			int x=(a-1)%n;
			col[x]++;
			row[y]++;
			if (x==y) slashd++;
			if (x==n-y-1) slashu++;
			if (col[x]==n || row[y]==n || slashd==n || slashu==n) {
				System.out.println(i);
				System.exit(0);
			}
		}
		System.out.println(-1);
	}
}
/*
3 5
5 1 8 9 7

3 5
4 2 9 7 5

4 12
13 9 6 5 2 7 16 14 8 3 10 11
*/
