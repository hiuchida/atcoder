	//1,000,000,000 16678ms
	//  100,000,000  1537ms
	//   10,000,000    98ms
	//    1,000,000    17ms
	static class Prime { //Prime20250420
		int n;
		boolean[] isp;
		int[] minf;
		List<Integer> list = new ArrayList<>();
		Prime(int n) {
			this.n = n;
			this.isp = new boolean[n];
			this.minf = new int[n];
			init();
		}
		void init() {
			isp[0] = false;
			isp[1] = false;
			for (int i=2; i<n; i++) isp[i] = true;
			minf[1] = 1;
			for (int i=2; i<n; i++) {
				if (isp[i]) {
					minf[i] = i;
					list.add(i);
					for (int j=i*2; j<n; j+=i) {
						isp[j] = false;
						if (minf[j] == 0) minf[j] = i;
					}
				}
			}
		}
		boolean check(long x) {
			if (x>(long)n*n) throw new RuntimeException();
			if (x<n) return isp[(int)x];
			for (int i : list) {
				if (x%i==0) {
					return false;
				}
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
		int[] divisors(int n) {
			List<Integer> list=new ArrayList<>();
			list.add(1);
			while (n>1) {
				List<Integer> lst=new ArrayList<>();
				int nxt=minf[n];
				int val=nxt;
				while (n%nxt==0) {
					for (int v : list) {
						lst.add(v*val);
					}
					n/=nxt;
					val*=nxt;
				}
				list.addAll(lst);
			}
			Collections.sort(list);
			int[] ans=new int[list.size()];
			for (int i=0; i<list.size(); i++) {
				ans[i]=list.get(i);
			}
			return ans;
		}
	}
