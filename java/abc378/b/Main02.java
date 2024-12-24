import java.util.*;
public class Main {
	static class Pair {
		int q;
		int r;
		Pair(int q, int r) {
			this.q = q;
			this.r = r;
		}
		@Override
		public String toString() {
			return "Pair [q=" + q + ", r=" + r + "]";
		}
	}
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
				System.out.println(v);
				continue;
			}
			if (v < p.r) {
				long dd = d;
				dd += p.r - v;
				System.out.println(dd);
			} else {
				long dd = d;
				dd += p.q - v + p.r;
				System.out.println(dd);
			}
		}
	}
}
