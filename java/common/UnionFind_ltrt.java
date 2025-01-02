	static class UnionFind {
		int[] uf;
		int[] lt;
		int[] rt;
		public UnionFind(int n) {
			uf = new int[n];
			lt = new int[n];
			rt = new int[n];
			for (int i=0; i<n; i++) {
				uf[i] = -1;
				lt[i] = i;
				rt[i] = i;
			}
		}
		public int root(int v) {
			if (uf[v] < 0) return v;
			uf[v] = root(uf[v]);
			return uf[v];
		}
		public void merge(int u, int v) {
			u = root(u);
			v = root(v);
			if (u == v) return;
			int uw = uf[u];
			int vw = uf[v];
			if (uw <= vw) {
				uf[u] += uf[v];
				lt[u] = Math.min(lt[u], lt[v]);
				rt[u] = Math.max(rt[u], rt[v]);
				uf[v] = u;
				lt[v] = -1;
				rt[v] = -1;
			} else if (uw > vw) {
				uf[v] += uf[u];
				lt[v] = Math.min(lt[u], lt[v]);
				rt[v] = Math.max(rt[u], rt[v]);
				uf[u] = v;
				lt[u] = -1;
				rt[u] = -1;
			}
		}
		public boolean same(int u, int v) {
			return root(u) == root(v);
		}
		public int size(int v) {
			v = root(v);
			return -uf[v];
		}
		public int left(int v) {
			v = root(v);
			return lt[v];
		}
		public int right(int v) {
			v = root(v);
			return rt[v];
		}
		@Override
		public String toString() {
			List<String> list = new ArrayList<>();
			for (int i=0; i<uf.length; i++) {
				if (uf[i] < -1) {
					list.add(-uf[i] + "(" + lt[i] + "-" + rt[i] + ")");
				} else if (uf[i] < 0) {
					list.add(""+ (-uf[i]));
				} else {
					list.add("*"+uf[i]);
				}
			}
			return "[" + String.join(", ", list) + "]";
		}
	}
