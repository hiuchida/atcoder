	static class Counter { //Counter_long_int20250414
		Map<Long, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(long k) {
			Integer v = map.get(k);
			if (v == null) v = 0;
			return v;
		}
		void put(long k, int v) {
			if (v==0) map.remove(k);
			else map.put(k, v);
		}
		void inc(long k) {
			int v = get(k);
			v++;
			put(k, v);
		}
		void dec(long k) {
			int v = get(k);
			v--;
			put(k, v);
		}
		void add(long k, int x) {
			int v = get(k);
			v += x;
			put(k, v);
		}
		void sub(long k, int x) {
			int v = get(k);
			v -= x;
			put(k, v);
		}
		NavigableSet<Long> keySet() {
			return (NavigableSet<Long>) map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
