	static class MyDeque {
		int[] ary;
		int head;
		int tail;
		MyDeque(int n, int i) {
			n++;
			n += n%2==0 ? 1 : 0;
			if (i<0) i=n/2;
			ary = new int[n];
			head = i;
			tail = i;
			Arrays.fill(ary, -1);
		}
		int size() {
			int t=tail;
			if (head>t) t += ary.length;
			return t-head;
		}
		int get(int i) {
			i += head;
			if (i >= ary.length) i -= ary.length;
			return ary[i];
		}
		void addFirst(int x) {
			head--;
			if (head < 0) head += ary.length;
			ary[head]=x;
		}
		void addLast(int x) {
			ary[tail]=x;
			tail++;
			if (tail >= ary.length) tail -= ary.length;
		}
		int removeFirst() {
			int x=ary[head];
			ary[head]=-1;
			head++;
			if (head >= ary.length) head -= ary.length;
			return x;
		}
		int removeLast() {
			tail--;
			if (tail < 0) tail += ary.length;
			int x=ary[tail];
			ary[tail]=-1;
			return x;
		}
		@Override
		public String toString() {
			return Arrays.toString(ary) + " " + head + " " + tail;
		}
	}
