import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int[][] ary=new int[h][w];
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				ary[i][j]=sc.nextInt();
			}
		}
		for (int j=0; j<w; j++) {
			for (int i=0; i<h; i++) {
				if (i>0) System.out.print(" ");
				System.out.print(ary[i][j]);
			}
			System.out.println();
		}
	}
}
/*
4 3
1 2 3
4 5 6
7 8 9
10 11 12

2 2
1000000000 1000000000
1000000000 1000000000
*/
