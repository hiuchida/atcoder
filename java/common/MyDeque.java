	static class MyDeque {
		int[] ary;
		int head;
		int tail;
		MyDeque(int n, int i) {
			ary = new int[n+1];
			head = i;
			tail = i;
		}
		void addFirst(int x) {
			head--;
			if (head < 0) head = ary.length - 1;
			ary[head]=x;
		}
		void addLast(int x) {
			ary[tail]=x;
			tail++;
			if (tail >= ary.length) tail -= ary.length;
		}
		int get(int i) {
			i += head-1;
			if (i >= ary.length) i -= ary.length;
			return ary[i];
		}
		@Override
		public String toString() {
			return Arrays.toString(ary) + " " + head + " " + tail;
		}
	}
