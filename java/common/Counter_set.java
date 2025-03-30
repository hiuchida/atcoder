	static class Counter {
		Map<Integer, TreeSet<Integer>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		TreeSet<Integer> get(int k) {
			TreeSet<Integer> v = map.get(k);
			if (v == null) v = new TreeSet<>();
			return v;
		}
		void put(int k, TreeSet<Integer> v) {
			map.put(k, v);
		}
		void add(int k, int idx) {
			TreeSet<Integer> v = get(k);
			v.add(idx);
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
