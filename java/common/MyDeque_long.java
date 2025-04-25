	static class MyDeque { //MyDeque_long20250425
		long[] ary;
		int head;
		int tail;
		MyDeque() {
			this(100, -1);
		}
		MyDeque(int n, int i) {
			n++;
			n += n%2==0 ? 1 : 0;
			if (i<0) i=n/2;
			ary = new long[n];
			head = i;
			tail = i;
			Arrays.fill(ary, -1);
		}
		int size() {
			int t=tail;
			if (head>t) t += ary.length;
			return t-head;
		}
		long get(int i) {
			i += head;
			if (i >= ary.length) i -= ary.length;
			return ary[i];
		}
		private void grow() {
			int s0=ary.length-1;
			int[] tmp=new int[s0 * 2 + 1];
			int s1=ary.length/2;
			int s2=ary.length-head;
			int s3=s1+s2;
			int s4=tail;
			Arrays.fill(tmp, 0, tmp.length, -1);
			System.arraycopy(ary, head, tmp, s1, s2);
			System.arraycopy(ary, 0, tmp, s3, s4);
			ary=tmp;
			head=s1;
			tail=head+s0;
		}
		void addFirst(long x) {
			if (size()==ary.length-1) grow();
			head--;
			if (head < 0) head += ary.length;
			ary[head]=x;
		}
		void addLast(long x) {
			if (size()==ary.length-1) grow();
			ary[tail]=x;
			tail++;
			if (tail >= ary.length) tail -= ary.length;
		}
		long removeFirst() {
			if (size()==0) return -1;
			long x=ary[head];
			ary[head]=-1;
			head++;
			if (head >= ary.length) head -= ary.length;
			return x;
		}
		long removeLast() {
			if (size()==0) return -1;
			tail--;
			if (tail < 0) tail += ary.length;
			long x=ary[tail];
			ary[tail]=-1;
			return x;
		}
		@Override
		public String toString() {
			return Arrays.toString(ary) + " " + head + " " + tail;
		}
	}
