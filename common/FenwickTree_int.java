	static class FenwickTree { //FenwickTree_int20250427
		int[] ary;
		int size;
		FenwickTree(int size) {
			this.ary = new int[size];
			this.size = size;
		}
		FenwickTree(int[] ary) {
			this.ary = ary;
			this.size = ary.length;
			init();
		}
		private void init() {
			for (int i = 0; i < size; i++) {
				int j = i + lsb(i + 1);
				if (j < size) ary[j] += ary[i];
			}
		}
		void add(int i, int delta) {
			while (i < size) {
				ary[i] += delta;
				i += lsb(i+1);
			}
		}
		void set(int i, int value) {
			add(i, value - get(i));
		}
		int get(int i) {
			return range(i, i + 1);
		}
		int sum(int i) {
			int ans = 0;
			while (i > 0) {
				ans += ary[i - 1];
				i -= lsb(i);
			}
			return ans;
		}
		int range(int i, int j) {
			int ans = 0;
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
