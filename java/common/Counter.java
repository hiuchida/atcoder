	static class Counter {
		Map<Integer, Integer> map = new TreeMap<>();
		int get(int c) {
			Integer v = map.get(c);
			if (v == null) v = 0;
			return v;
		}
		void inc(int c) {
			int v = get(c);
			v++;
			map.put(c, v);
		}
		Set<Integer> keySet() {
			return map.keySet();
		}
	}
