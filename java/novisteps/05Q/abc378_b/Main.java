import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Pair[] ary = new Pair[n];
		for (int i = 0; i < n; i++) {
			int q = sc.nextInt();
			int r = sc.nextInt();
			ary[i] = new Pair(q, r);
		}
//		System.out.println(Arrays.toString(ary));
		int q = sc.nextInt();
		for (int i = 0; i < q; i++) {
			int t = sc.nextInt();
			int d = sc.nextInt();
			Pair p = ary[t-1];
			int v = d % p.q;
			if (v == p.r) {
			} else if (v < p.r) {
				d += p.r - v;
			} else {
				d += p.q - v + p.r;
			}
			System.out.println(d);
		}
	}
	static class Pair {
		int q;
		int r;
		Pair(int q, int r) {
			this.q = q;
			this.r = r;
		}
		@Override
		public String toString() {
			return "(" + q + "," + r + ")";
		}
	}
}
/*
2
7 3
4 2
5
1 1
1 3
1 4
1 15
2 7
*/
