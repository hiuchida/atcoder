	static class Counter { //Counter_str_int20250414
		Map<String, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(String k) {
			Integer v = map.get(k);
			if (v == null) v = 0;
			return v;
		}
		void put(String k, int v) {
			if (v==0) map.remove(k);
			else map.put(k, v);
		}
		void inc(String k) {
			int v = get(k);
			v++;
			put(k, v);
		}
		void dec(String k) {
			int v = get(k);
			v--;
			put(k, v);
		}
		void add(String k, int x) {
			int v = get(k);
			v += x;
			put(k, v);
		}
		void sub(String k, int x) {
			int v = get(k);
			v -= x;
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
