	static class Counter { //Counter_str_liststr20250414
		Map<String, List<String>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		List<String> get(String k) {
			List<String> v = map.get(k);
			if (v == null) v = new ArrayList<>();
			return v;
		}
		void put(String k, List<String> v) {
			map.put(k, v);
		}
		void remove(String k) {
			map.remove(k);
		}
		void add(String k, String val) {
			List<String> v = get(k);
			v.add(val);
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
