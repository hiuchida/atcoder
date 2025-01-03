	static class Prime {
		int n;
		boolean[] isp;
		List<Integer> list = new ArrayList<>();
		Prime(int n) {
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
					for (int j=i*2; j<n; j+=i) isp[j] = false;
				}
			}
		}
	}
