	static class Counter { //Counter_str_setstr250414
		Map<String, TreeSet<String>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		TreeSet<String> get(String k) {
			TreeSet<String> v = map.get(k);
			if (v == null) v = new TreeSet<>();
			return v;
		}
		void put(String k, TreeSet<String> v) {
			map.put(k, v);
		}
		void remove(String k) {
			map.remove(k);
		}
		boolean is(String k, String val) {
			TreeSet<String> v = get(k);
			return v.contains(val);
		}
		void add(String k, String val) {
			TreeSet<String> v = get(k);
			v.add(val);
			put(k, v);
		}
		void del(String k, String val) {
			TreeSet<String> v = get(k);
			v.remove(val);
			put(k, v);
		}
		NavigableSet<String> keySet() {
			return (NavigableSet<String>) map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
