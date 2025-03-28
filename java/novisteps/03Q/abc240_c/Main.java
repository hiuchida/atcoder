import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		int[] aa=new int[n];
		int[] ab=new int[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
			ab[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(ab));
		int[][] ary=new int[n+1][x+2];
		ary[0][0]=1;
		for (int i=0; i<n; i++) {
			for (int j=0; j<x; j++) {
				if (ary[i][j]>i) {
					int ja=Math.min(j+aa[i], x+1);
					int jb=Math.min(j+ab[i], x+1);
					ary[i+1][ja]=ary[i][j]+1;
					ary[i+1][jb]=ary[i][j]+1;
				}
			}
		}
//		for (int i=0; i<=n; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		if (ary[n][x]>0) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
2 10
3 6
4 5

2 10
10 100
10 100

4 12
1 8
5 7
3 4
2 6
*/
