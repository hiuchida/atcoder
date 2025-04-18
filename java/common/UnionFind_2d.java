	static class UnionFind { //UnionFind_2d20250416
		int h;
		int w;
		int[] uf;
		public UnionFind(int h, int w) {
			this.h = h;
			this.w = w;
			int n = h*w;
			this.uf = new int[n];
			for (int i=0; i<n; i++) uf[i] = -1;
		}
		public int root(int x1, int y1) {
			int u=x1+y1*w;
			return root(u);
		}
		public int root(int v) {
			if (uf[v] < 0) return v;
			uf[v] = root(uf[v]);
			return uf[v];
		}
		public void merge(int x1, int y1, int x2, int y2) {
			int u=x1+y1*w;
			int v=x2+y2*w;
			merge(u, v);
		}
		public void merge(int u, int v) {
			u = root(u);
			v = root(v);
			if (u == v) return;
			int uw = uf[u];
			int vw = uf[v];
			if (uw <= vw) {
				uf[u] += uf[v];
				uf[v] = u;
			} else if (uw > vw) {
				uf[v] += uf[u];
				uf[u] = v;
			}
		}
		public boolean same(int x1, int y1, int x2, int y2) {
			int u=x1+y1*w;
			int v=x2+y2*w;
			return same(u, v);
		}
		public boolean same(int u, int v) {
			return root(u) == root(v);
		}
		public int size(int x1, int y1) {
			int u=x1+y1*w;
			return size(u);
		}
		public int size(int v) {
			v = root(v);
			return -uf[v];
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int y=0; y<h; y++) {
				List<Integer> list = new ArrayList<>();
				for (int x=0; x<w; x++) {
					list.add(uf[y*w+x]);
				}
				if (sb.length() > 0) sb.append(System.getProperty("line.separator"));
				sb.append(list);
			}
			return sb.toString();
		}
	}
