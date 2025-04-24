	//1,000,000,000 17139ms
	//  100,000,000  1537ms
	//   10,000,000   122ms
	//    1,000,000    15ms
	static class PrimeMini { //PrimeMini20250421
		int n;
		boolean[] isp;
		List<Integer> list = new ArrayList<>();
		PrimeMini(int n) {
			this.n = n;
			this.isp = new boolean[n];
			init();
		}
		void init() {
			isp[0] = false;
			isp[1] = false;
			for (int i=2; i<n; i++) isp[i] = true;
			for (int i=2; i<n; i++) {
				if (isp[i]) {
					list.add(i);
					for (int j=i*2; j<n; j+=i) {
						isp[j] = false;
					}
				}
			}
		}
		boolean check(long x) {
			if (x>(long)n*n) throw new RuntimeException();
			if (x<n) return isp[(int)x];
			for (int i : list) {
				if (x%i==0) return false;
			}
			return true;
		}
	}
