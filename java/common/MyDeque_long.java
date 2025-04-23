	static class MyDeque { //MyDeque_long20250316
		long[] ary;
		int head;
		int tail;
		MyDeque(int n, int i) {
			ary = new long[n+1];
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
			if (tail>0) {
				ary[tail]+=ary[tail-1];
			}
			tail++;
			if (tail >= ary.length) tail -= ary.length;
		}
		long removeFirst() {
			long x=ary[head];
			head++;
			if (head >= ary.length) head -= ary.length;
			return x;
		}
		long get(int i) {
			i += head-1;
			if (i >= ary.length) i -= ary.length;
			return ary[i];
		}
		@Override
		public String toString() {
			return Arrays.toString(ary) + " " + head + " " + tail;
		}
	}
