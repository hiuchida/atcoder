	static class SegmentTree { //SegmentTree_summap20250502
		int siz;
		Map<Integer, Integer> map=new TreeMap<>();
		int def;
		int inf;
		SegmentTree(int n, int def, int inf) {
			this.siz = ceil2pow(n);
			this.def = def;
			this.inf = inf;
		}
		int add(int a, int b) {
			return a+b;
		}
		void update(int i, int value) {
			i += siz - 1;
			map.put(i, value);
			while (i > 0) {
				i = (i - 1) / 2;
				Integer v1=map.get(2 * i + 1);
				Integer v2=map.get(2 * i + 2);
				if (v1==null && v2==null) map.remove(i);
				else {
					if (v1==null) v1=def;
					if (v2==null) v2=def;
					map.put(i, add(v1, v2));
				}
			}
		}
		int get(int i) {
			i += siz - 1;
			Integer v=map.get(i);
			if (v==null) v=def;
			return v;
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
				Integer v=map.get(k);
				if (v==null) v=0;
				return v;
			}
//			System.out.println(a+" "+b+" "+k+" "+lt+" "+rt);
			int vl = query(a, b, 2 * k + 1, lt, (lt + rt) / 2);
			int vr = query(a, b, 2 * k + 2, (lt + rt) / 2, rt);
			return add(vl, vr);
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
//			for (int i=0; i<ary.length; i++) {
//				String v = ary[i] != inf ? "" + ary[i] : "-";
//				sb.append(v + " ");
//			}
			sb.append(map);
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
