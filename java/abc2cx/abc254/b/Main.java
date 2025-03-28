import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] ary=new int[n][n];
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (x==0 || y==x) ary[y][x]=1;
				else if (y==0) ;
				else ary[y][x]=ary[y-1][x-1]+ary[y-1][x];
			}
		}
//		for (int y=0; y<n; y++) {
//			System.out.println(Arrays.toString(ary[y]));
//		}
		for (int y=0; y<n; y++) {
			for (int x=0; x<=y; x++) {
				System.out.print(ary[y][x]+" ");
			}
			System.out.println();
		}
	}
}
/*
3

10
*/
