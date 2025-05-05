import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int n = sc.nextInt();
			int[][] da = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					da[i][j] = sc.nextInt();
				}
			}
			Aggregate2D agg = new Aggregate2D(da);
			int q = sc.nextInt();
			for (int i = 0; i < q; i++) {
				int p = sc.nextInt();
				int ans = 0;
				int prey = -1;
				for (int x = (p >= n) ? n : p; x > 0; x--) {
					int y = n / x;
					if (prey == y) {
						continue;
					}
					prey = y;
					for (int lt = 0; lt + x <= n; lt++) {
						for (int tp = 0; tp + y <= n; tp++) {
							ans = Math.max(ans, agg.sum(lt, tp, lt + x, tp + y));
						}
					}
				}
				System.out.println(ans);
			}
		}
	}

	public static class Aggregate2D { //unused Aggregate2D
		private int m;
		private int n;
		private int[][] element;
		public Aggregate2D(int[][] ary) {
			m = ary.length;
			n = ary[0].length;
			element = new int[m + 1][n + 1];
			for (int i = 0; i < m; i++)
				Arrays.fill(element[i], 0);
			for (int i = 0; i < m; i++)
				for (int j = 0; j < n; j++)
					element[i + 1][j + 1] = element[i + 1][j] + element[i][j + 1] - element[i][j] + ary[i][j];
		}
		public int sum(int lt, int tp) {
			return element[lt][tp];
		}
		public int sum(int lt, int tp, int rt, int bm) {
			return element[rt][bm] - element[rt][tp] - element[lt][bm] + element[lt][tp];
		}
		public void print() {
//			for (int i = 0; i < m + 1; i++) {
//				for (int j = 0; j < n + 1; j++) {
//					if (j > 0)
//						System.out.print(" ");
//					System.out.print(element[i][j]);
//				}
//				System.out.println("");
//			}
		}
	}

}
