	static class MyArray { //Prime20250426
		int[] array;
		int size=0;
		MyArray() {
			this(100);
		}
		MyArray(int n) {
			this.array = new int[n + 1];
		}
		int size() {
			return size;
		}
		int get(int idx) {
			return array[idx];
		}
		void add(int v) {
			if (array.length == size) array = Arrays.copyOf(array, size * 2);
			array[size++] = v;
		}
		int remove() {
			if (size == 0) return -1;
			return array[--size];
		}
		void trimToSize() {
			if (size < array.length) array = Arrays.copyOf(array, size);
		}
		int[] toArray() {
			int[] ans=new int[size];
			System.arraycopy(array, 0, ans, 0, size);
			return ans;
		}
		String join(String delimiter) {
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<size; i++) {
				if (i>0) sb.append(delimiter);
				sb.append(array[i]);
			}
			return sb.toString();
		}
		@Override
		public String toString() {
			return Arrays.toString(array) + " " + size;
		}
	}
	//1,000,000,000  ----ms
	//  100,000,000  4759ms
	//   10,000,000   311ms
	//    1,000,000    20ms
	static class Prime { //Prime20250426
		int n;
		boolean[] isp;
		int[] minf;
		int[] lst;
		Prime(int n) {
			this.n = n;
			this.isp = new boolean[n];
			this.minf = new int[n];
			init();
		}
		void init() {
			MyArray ma=new MyArray();
			isp[0] = false;
			isp[1] = false;
			for (int i=2; i<n; i++) isp[i] = true;
			minf[1] = 1;
			for (int i=2; i<n; i++) {
				if (isp[i]) {
					minf[i] = i;
					ma.add(i);
					for (int j=i*2; j<n; j+=i) {
						isp[j] = false;
						if (minf[j] == 0) minf[j] = i;
					}
				}
			}
			lst=ma.toArray();
		}
		boolean check(long x) {
			if (x>(long)n*n) throw new RuntimeException();
			if (x<n) return isp[(int)x];
			for (int i : lst) {
				if (x%i==0) return false;
			}
			return true;
		}
		long count(int n) {
			long ans=1;
			while (n>1) {
				int nxt=minf[n];
				int cnt=1;
				while (n%nxt==0) {
					cnt++;
					n/=nxt;
				}
				ans*=cnt;
			}
			return ans;
		}
		long sum(int n) {
			long ans=1;
			while (n>1) {
				int nxt=minf[n];
				int val=1;
				long sum=1;
				while (n%nxt==0) {
					val*=nxt;
					sum+=val;
					n/=nxt;
				}
				ans*=sum;
			}
			return ans;
		}
		int[] divisors(int n) {
			MyArray ma=new MyArray();
			ma.add(1);
			while (n>1) {
				int[] ary=ma.toArray();
				int nxt=minf[n];
				int val=nxt;
				while (n%nxt==0) {
					for (int v : ary) ma.add(v*val);
					n/=nxt;
					val*=nxt;
				}
			}
			int[] ans=ma.toArray();
			Arrays.sort(ans);
			return ans;
		}
		int[] factors(int n) {
			MyArray ma=new MyArray();
			while (n>1) {
				int nxt=minf[n];
				while (n%nxt==0) {
					ma.add(nxt);
					n/=nxt;
				}
			}
			int[] ans=ma.toArray();
			return ans;
		}
		int[][] factorize(int n) {
			MyArray mab=new MyArray();
			MyArray mae=new MyArray();
			while (n>1) {
				int nxt=minf[n];
				int cnt=0;
				while (n%nxt==0) {
					n/=nxt;
					cnt++;
				}
				mab.add(nxt);
				mae.add(cnt);
			}
			int[][] ans=new int[mab.size()][2];
			for (int i=0; i<mab.size(); i++) {
				ans[i][0]=mab.get(i);
				ans[i][1]=mae.get(i);
			}
			return ans;
		}
	}
