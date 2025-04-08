	static class UnionFind { //UnionFind_map
		Map<Integer, Integer> map=new TreeMap<>();
		public UnionFind() {
		}
		public int root(int v) {
			Integer vv = map.get(v);
			if (vv == null) {
				vv = -1;
				map.put(v, vv);
			}
			if (vv < 0) return v;
			vv = root(vv);
			map.put(v, vv);
			return vv;
		}
		public void merge(int u, int v) {
			u = root(u);
			v = root(v);
			if (u == v) return;
			int uw = map.get(u);
			int vw = map.get(v);
			int w = uw + vw;
			if (uw <= vw) {
				map.put(u, w);
				map.put(v, u);
			} else {
				map.put(v, w);
				map.put(u, v);
			}
		}
		public boolean same(int u, int v) {
			return root(u) == root(v);
		}
		public int size(int v) {
			v = root(v);
			return -map.get(v);
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
