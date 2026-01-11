	static class Bean { //Bean_int20250416
		int v;
		Bean(int v) {
			this.v=v;
		}
		void add(int n) {
			v+=n;
		}
		@Override
		public String toString() {
			return "(" + v + ")";
		}
	}
