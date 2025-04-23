import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[][] aa=new int[n][n];
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				aa[y][x]=sc.nextInt();
			}
		}
		int[][] ab=new int[n][n];
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				ab[y][x]=sc.nextInt();
			}
		}
//		for (int y=0; y<n; y++) {
//			System.out.println(Arrays.toString(ab[y]));
//		}
//		System.out.println();
		for (int i=0; i<4; i++) {
			if (comp(n, aa, ab)) {
				System.out.println("Yes");
				System.exit(0);
			}
			int[][] ac=rotate(n, aa);
			aa=ac;
//			for (int y=0; y<n; y++) {
//				System.out.println(Arrays.toString(aa[y]));
//			}
//			System.out.println();
		}
		System.out.println("No");
	}
	static int[][] rotate(int n, int[][] ary) { //abc298_b: 時計周りに90度回転する
		int[][] ans=new int[n][n];
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				ans[y][x]=ary[n-1-x][y];
			}
		}
		return ans;
	}
	static boolean comp(int n, int[][] aa, int[][] ab) { //abc298_b: aa[i][j]が1の場合のみab[i][j]と比較する
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (aa[y][x]==0) continue;
				if (aa[y][x]!=ab[y][x]) return false;
			}
		}
		return true;
	}
}
/*
3
0 1 1
1 0 0
0 1 0
1 1 0
0 0 1
1 1 1

2
0 0
0 0
1 1
1 1

5
0 0 1 1 0
1 0 0 1 0
0 0 1 0 1
0 1 0 1 0
0 1 0 0 1
1 1 0 0 1
0 1 1 1 0
0 0 1 1 1
1 0 1 0 1
1 1 0 1 0
*/
