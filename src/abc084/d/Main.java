package abc084.d;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int n = 100000;
			Prime p = new Prime(n);
			int[] ary = new int[n + 1];
			for (int i = 2; i <= n; i++) {
				if (p.isPrime(i) && p.isPrime((i + 1) / 2))
					ary[i] = 1;
			}
			Aggregate agg = new Aggregate(ary);
			int q = sc.nextInt();
			for (int i = 0; i < q; i++) {
				int l = sc.nextInt();
				int r = sc.nextInt();
				int ans = agg.sum(l, r + 1);
				System.out.println(ans);
			}
		}
	}

	public static class Aggregate {
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

	public static class Prime {
		private boolean[] prime;

		public Prime(int n) {
			prime = new boolean[n + 1];
			Arrays.fill(prime, true);
			prime[0] = false;
			prime[1] = false;
			for (int i = 2; i * i <= n; i++) {
				if (!prime[i])
					continue;
				for (int j = i * i; j <= n; j += i)
					prime[j] = false;
			}
		}

		public boolean isPrime(int x) {
			return prime[x];
		}
	}

}
