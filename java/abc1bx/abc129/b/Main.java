import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int n = sc.nextInt();
			int[] ary = new int[n];
			for (int i = 0; i < n; i++) {
				ary[i] = sc.nextInt();
			}
			Aggregate agg = new Aggregate(ary);
			int ans = Integer.MAX_VALUE;
			for (int t = 1; t < n; t++) {
				int s1 = agg.sum(0, t);
				int s2 = agg.sum(t, n);
				int dif = Math.abs(s1 - s2);
				ans = Math.min(ans, dif);
			}
			System.out.println(ans);
		}
	}

	public static class Aggregate { //unused Aggregate
		private int n;
		private int[] element;

		public Aggregate(int[] ary) {
			n = ary.length;
			element = new int[n + 1];
			Arrays.fill(element, 0);
			for (int i = 0; i < n; i++)
				element[i + 1] = element[i] + ary[i];
		}

		public int sum(int k) {
			return element[k];
		}

		public int sum(int p, int q) {
			return element[q] - element[p];
		}

		public void print() {
//			for (int i = 0; i < n + 1; i++) {
//				if (i > 0)
//					System.out.print(" ");
//				System.out.print(element[i]);
//			}
//			System.out.println("");
		}
	}

}
