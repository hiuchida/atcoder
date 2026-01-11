	static class SegmentTree { //SegmentTree_min20250429
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
		int min(int a, int b) {
			return Math.min(a, b);
		}
		void update(int i, int value) {
			i += siz - 1;
			ary[i] = value;
			while (i > 0) {
				i = (i - 1) / 2;
				ary[i] = min(ary[2 * i + 1], ary[2 * i + 2]);
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
			return min(vl, vr);
		}
		int findLeft(int a, int b, int x) {
			return findLeft(a, b, x, 0, 0, siz);
		}
		int findRight(int a, int b, int x) {
			return findRight(a, b, x, 0, 0, siz);
		}
		int findLeft(int a, int b, int x, int k, int lt, int rt) {
			if (ary[k] > x || rt <= a || b <= lt) {
//				System.out.println(a+" "+b+" "+x+" "+k+"("+ary[k]+")"+" "+lt+" "+rt+" -> "+b);
				return b;
			}
			if (k >= siz - 1) {
//				System.out.println(a+" "+b+" "+x+" "+k+"("+ary[k]+")"+" "+lt+" "+rt+" -> "+(k-(siz-1)));
				return k - (siz - 1);
			}
//			System.out.println(a+" "+b+" "+x+" "+k+"("+ary[k]+")"+" "+lt+" "+rt);
			int vl = findLeft(a, b, x, 2 * k + 1, lt, (lt + rt) / 2);
			if (vl != b) {
				return vl;
			}
			return findLeft(a, b, x, 2 * k + 2, (lt + rt) / 2, rt);
		}
		int findRight(int a, int b, int x, int k, int lt, int rt) {
			if (ary[k] > x || rt <= a || b <= lt) {
				return a - 1;
			}
			if (k >= siz - 1) {
				return k - (siz - 1);
			}
			int vr = findRight(a, b, x, 2 * k + 2, (lt + rt) / 2, rt);
			if (vr != a- 1) {
				return vr;
			}
			return findRight(a, b, x, 2 * k + 1, lt, (lt + rt) / 2);
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
