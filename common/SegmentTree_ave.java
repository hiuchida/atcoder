	static class Bean { //SegmentTree_ave20250429
		int cnt;
		int sum;
		Bean() {
			this(0, 0);
		}
		Bean(int value) {
			this(1, value);
		}
		Bean(int count, int value) {
			this.cnt=count;
			this.sum=value;
		}
		void set(int value) {
			this.cnt=1;
			this.sum=value;
		}
		@Override
		public int hashCode() {
			return Objects.hash(cnt, sum);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			Bean other = (Bean) obj;
			return cnt == other.cnt && sum == other.sum;
		}
		@Override
		public String toString() {
			return "("+cnt+","+sum+")";
		}
	}
	static class SegmentTree { //SegmentTree_ave20250429
		int siz;
		Bean[] ary;
		int def;
		Bean inf;
		SegmentTree(int n, int def, int inf) {
			this.siz = ceil2pow(n);
			this.ary = new Bean[2 * siz];
			this.def = def;
			this.inf = new Bean(0, inf);
			for (int i=0; i<ary.length; i++) {
				ary[i]=new Bean(0, def);
			}
		}
		Bean add(Bean x, Bean a, Bean b) {
			x.cnt=a.cnt+b.cnt;
			x.sum=a.sum+b.sum;
			return x;
		}
		void update(int i, int value) {
			i += siz - 1;
			ary[i].set(value);
			while (i > 0) {
				i = (i - 1) / 2;
				add(ary[i], ary[2 * i + 1], ary[2 * i + 2]);
			}
		}
		Bean query(int a, int b) {
			return query(a, b, 0, 0, siz);
		}
		Bean query(int a, int b, int k, int lt, int rt) {
			if (rt <= a || b <= lt) {
//				System.out.println(a+" "+b+" "+k+" "+lt+" "+rt+" -> "+def);
				return inf;
			}
			if (a <= lt && rt <= b) {
//				System.out.println(a+" "+b+" "+k+" "+lt+" "+rt+" -> "+ary[k]);
				return ary[k];
			}
//			System.out.println(a+" "+b+" "+k+" "+lt+" "+rt);
			Bean vl = query(a, b, 2 * k + 1, lt, (lt + rt) / 2);
			Bean vr = query(a, b, 2 * k + 2, (lt + rt) / 2, rt);
			return add(new Bean(), vl, vr);
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<ary.length; i++) {
				String v = !ary[i].equals(inf) ? "" + ary[i] : "-";
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
