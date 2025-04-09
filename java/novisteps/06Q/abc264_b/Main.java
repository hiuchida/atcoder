import java.util.*;
public class Main {
	static int[][] ary;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=15;
		int r = sc.nextInt();
		int c = sc.nextInt();
		ary=new int[n+1][n+1];
		init(1, n);
		init(3, n-2);
		init(5, n-4);
		init(7, n-6);
//		for (int i=1; i<=n; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		if (ary[r][c]==1) System.out.println("black");
		else System.out.println("white");
	}
	static void init(int lt, int rt) {
		for (int i=lt; i<=rt; i++) {
			ary[i][lt]=1;
			ary[i][rt]=1;
			ary[lt][i]=1;
			ary[rt][i]=1;
		}
	}
}
/*
3 5

4 5
*/
