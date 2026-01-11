	static class Bean { //Counter_int_bean20250416
		long v;
		Bean(long v) {
			this.v = v;
		}
		@Override
		public String toString() {
			return "("+v+")";
		}
	}
	static class Counter { //Counter_int_bean20250416
		Map<Integer, Bean> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		Bean get(int k) {
			Bean v = map.get(k);
			if (v == null) v = new Bean(0);
			return v;
		}
		void put(int k, Bean v) {
			map.put(k, v);
		}
		void remove(int k) {
			map.remove(k);
		}
		void add(int k, int x) {
			Bean v = get(k);
			v.v += x;
			put(k, v);
		}
		NavigableSet<Integer> keySet() {
			return (NavigableSet<Integer>) map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
