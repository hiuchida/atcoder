import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int q=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		SegmentTree st=new SegmentTree(n, 0, 0);
		for (int i=0; i<n; i++) {
			st.update(i, ary[i]);
		}
		for (int qq=0; qq<q; qq++) {
			int cmd=sc.nextInt();
			if (cmd==1) {
				int x=sc.nextInt();
				int y=sc.nextInt();
				st.update(x-1, y);
//				System.out.println(st);
			} else {
				int x=sc.nextInt();
				int y=sc.nextInt();
				int v=st.query(x-1, y-1+1);
				System.out.println(v);
			}
		}
	}
	static class SegmentTree { //SegmentTree_xor20250427
		int siz;
		int[] ary;
		int def;
		int inf;
		SegmentTree(int n, int def, int inf) {
			this.siz = ceil2pow(n);
			this.ary = new int[2 * siz];
			this.def = def;
			this.inf = inf;
			Arrays.fill(ary, def);
		}
		int xor(int a, int b) {
			return a ^ b;
		}
		void update(int i, int value) {
			i += siz - 1;
			ary[i] = xor(ary[i], value);
			while (i > 0) {
				i = (i - 1) / 2;
				ary[i] = xor(ary[2 * i + 1], ary[2 * i + 2]);
			}
		}
		int query(int a, int b) {
			return query(a, b, 0, 0, siz);
		}
		int query(int a, int b, int k, int lt, int rt) {
			if (rt <= a || b <= lt) {
//				System.out.println(a+" "+b+" "+k+" "+lt+" "+rt+" -> "+def);
				return inf;
			}
			if (a <= lt && rt <= b) {
//				System.out.println(a+" "+b+" "+k+" "+lt+" "+rt+" -> "+ary[k]);
				return ary[k];
			}
//			System.out.println(a+" "+b+" "+k+" "+lt+" "+rt);
			int vl = query(a, b, 2 * k + 1, lt, (lt + rt) / 2);
			int vr = query(a, b, 2 * k + 2, (lt + rt) / 2, rt);
			return xor(vl, vr);
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<ary.length; i++) {
				String v = ary[i] != inf ? "" + ary[i] : "-";
				sb.append(v + " ");
			}
			return sb.toString();
		}
		private int ceil2pow(int n) {
			if (n == 0) return 1;
			n--;
			n |= (n >>> 1);
			n |= (n >>> 2);
			n |= (n >>> 4);
			n |= (n >>> 8);
			n |= (n >>> 16);
			n++;
			return n;
			/*
			int pow = 1;
			while (pow < n) pow <<= 1;
			return pow;
			*/
		}
	}
}
/*
3 4
1 2 3
2 1 3
2 2 3
1 2 3
2 2 3

10 10
0 5 3 4 7 0 0 0 1 0
1 10 7
2 8 9
2 3 6
2 1 6
2 1 10
1 9 4
1 6 1
1 6 3
1 1 7
2 3 5
*/
