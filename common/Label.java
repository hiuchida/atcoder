	static class Label implements Comparable<Label> { //Label20250409
		final String s;
		final int v;
		final int idx;
		Label(String s, int v, int idx) {
			this.s = s;
			this.v = v;
			this.idx = idx;
		}
		@Override
		public int compareTo(Label that) {
			int cmp = 0;
			cmp = this.s.compareTo(that.s);
			if (cmp != 0) return cmp;
			cmp = Integer.compare(this.v, that.v);
			return cmp;
		}
		@Override
		public int hashCode() {
			return Objects.hash(s, v);
		}
		@Override
		public boolean equals(Object obj) {
			Label that = (Label) obj;
			return this.s == that.s && this.v == that.v;
		}
		@Override
		public String toString() {
			return "(" + s + "," + v + "," + idx + ")";
		}
	}
