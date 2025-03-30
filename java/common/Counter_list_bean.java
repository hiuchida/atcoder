	static class Bean {
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
	static class Counter {
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
		void add(int k, Bean x) {
			List<Bean> v = get(k);
			v.add(x);
			put(k, v);
		}
		Set<Integer> keySet() {
			return map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
