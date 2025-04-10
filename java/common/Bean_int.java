	static class Bean { //Bean_int20250410
		int v;
		int idx;
		Bean(int v, int idx) {
			this.v=v;
			this.idx=idx;
		}
		void add(int n) {
			v+=n;
		}
		@Override
		public String toString() {
			return "(" + v + "," + idx + ")";
		}
	}
