	static class MyHeapque { //MyHeapque_int20250428
		int[] ary;
		int cnt;
		int size;
		MyHeapque() {
			this(100);
		}
		MyHeapque(int n) {
			this.ary=new int[n];
			this.cnt=0;
			this.size=0;
		}
		int size() {
			return size;
		}
		void add(int i) {
			if (ary.length == cnt) ary = Arrays.copyOf(ary, cnt * 2);
			ary[cnt]=i;
			upheap(ary, cnt);
			cnt++;
			size++;
		}
		int remove() {
			int x=ary[0];
			ary[0]=Integer.MAX_VALUE;
			downheap(ary, cnt);
			if (cnt%2==0) {
				while (cnt>=2 && ary[cnt-2]==Integer.MAX_VALUE && ary[cnt-1]==Integer.MAX_VALUE) {
					ary[cnt-2]=0;
					ary[cnt-1]=0;
					cnt-=2;
				}
			}
			size--;
			return x;
		}
		private void upheap(int[] ary, int i) {
			int x=ary[i];
			int j=i;
			while (i>0) {
				j=(i-1)/2;
				if (ary[j]>x) {
					ary[i]=ary[j];
				} else {
					break;
				}
				i=j;
			}
			ary[i]=x;
		}
		private void downheap(int[] ary, int n) {
			int i=0;
			while (true) {
				int x=ary[i];
				int lt=i*2+1;
				int rt=i*2+2;
				if (lt>=n) break;
				if (rt<n && ary[lt]>ary[rt]) lt=rt;
				if (x<=ary[lt]) break;
				ary[i]=ary[lt];
				ary[lt]=x;
				i=lt;
			}
		}
		@Override
		public String toString() {
			return Arrays.toString(ary)+" cnt=" + cnt + ", size=" + size;
		}
	}
