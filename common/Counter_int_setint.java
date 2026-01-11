	static class Counter { //Counter_int_setint250502
		Map<Integer, TreeSet<Integer>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int size(int k) {
			TreeSet<Integer> v = map.get(k);
			if (v == null) return 0;
			return v.size();
		}
		TreeSet<Integer> get(int k) {
			TreeSet<Integer> v = map.get(k);
			if (v == null) v = new TreeSet<>();
			return v;
		}
		void put(int k, TreeSet<Integer> v) {
			map.put(k, v);
		}
		void remove(int k) {
			map.remove(k);
		}
		boolean is(int k, int idx) {
			TreeSet<Integer> v = get(k);
			return v.contains(idx);
		}
		void add(int k, int idx) {
			TreeSet<Integer> v = get(k);
			v.add(idx);
			put(k, v);
		}
		void del(int k, int idx) {
			TreeSet<Integer> v = get(k);
			v.remove(idx);
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
