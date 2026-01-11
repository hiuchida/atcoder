	static class FenwickTree { //FenwickTree_long20250427
		long[] ary;
		int size;
		FenwickTree(int size) {
			this.ary = new long[size];
			this.size = size;
		}
		FenwickTree(int[] ary0) {
			this.size = ary0.length;
			this.ary = new long[size];
			for (int i=0; i<size; i++) {
				ary[i]=ary0[i];
			}
			init();
		}
		private void init() {
			for (int i = 0; i < size; i++) {
				int j = i + lsb(i + 1);
				if (j < size) ary[j] += ary[i];
			}
		}
		void add(int i, long delta) {
			while (i < size) {
				ary[i] += delta;
				i += lsb(i+1);
			}
		}
		void set(int i, long value) {
			add(i, value - get(i));
		}
		long get(int i) {
			return range(i, i + 1);
		}
		long sum(int i) {
			long ans = 0;
			while (i > 0) {
				ans += ary[i - 1];
				i -= lsb(i);
			}
			return ans;
		}
		long range(int i, int j) {
			long ans = 0;
			while (j > i) {
				ans += ary[j - 1];
				j -= lsb(j);
			}
			while (i > j) {
				ans -= ary[i - 1];
				i -= lsb(i);
			}
			return ans;
		}
		private int lsb(int i) {
			return i & (-i);
		}
	}
