	static class Bean { //Bean_long20250409
		long v;
		Bean(long v) {
			this.v=v;
		}
		void add(long n) {
			v+=n;
		}
		@Override
		public String toString() {
			return "(" + v + ")";
		}
	}
