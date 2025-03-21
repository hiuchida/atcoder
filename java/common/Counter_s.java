	static class Counter {
		Map<String, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(String c) {
			Integer v = map.get(c);
			if (v == null) v = 0;
			return v;
		}
		void inc(String c) {
			int v = get(c);
			v++;
			map.put(c, v);
		}
		void dec(String c) {
			int v = get(c);
			v--;
			if (v==0) map.remove(c);
			else map.put(c, v);
		}
		void add(String c, int x) {
			int v = get(c);
			v += x;
			map.put(c, v);
		}
		void sub(String c, int x) {
			int v = get(c);
			v -= x;
			if (v==0) map.remove(c);
			else map.put(c, v);
		}
		Set<String> keySet() {
			return map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
