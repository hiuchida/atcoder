	static class Bean { //Counter_int_listbean20250414
		int v;
		int w;
		Bean(int v, int w) {
			this.v = v;
			this.w = w;
		}
		@Override
		public String toString() {
			return "("+v+","+w+")";
		}
	}
	static class Counter { //Counter_int_listbean20250414
		Map<Integer, List<Bean>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		List<Bean> get(int k) {
			List<Bean> v = map.get(k);
			if (v == null) v = new ArrayList<>();
			return v;
		}
		void put(int k, List<Bean> v) {
			map.put(k, v);
		}
		void remove(int k) {
			map.remove(k);
		}
		void add(int k, Bean val) {
			List<Bean> v = get(k);
			v.add(val);
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
