import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();
		int[][] ary=new int[2][2];
		for (int i=0; i<2; i++) {
			for (int j=0; j<2; j++) {
				ary[i][j]=sc.nextInt();
			}
		}
		int ans=ary[r-1][c-1];
		System.out.println(ans);
	}
}
/*
1 2
1 0
0 1

2 2
1 2
3 4

2 1
90 80
70 60
*/
