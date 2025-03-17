	static class PairL implements Comparable<PairL> {
		final long st;
		final long ed;
		PairL(long st, long ed) {
			this.st = st;
			this.ed = ed;
		}
		static Comparator<PairL> newComparator1() {
			return new Comparator<PairL>() {
				@Override
				public int compare(PairL o1, PairL o2) {
					int cmp = Long.compare(o1.st, o2.st);
					return cmp;
				}
			};
		}
		static Comparator<PairL> newComparator2() {
			return new Comparator<PairL>() {
				@Override
				public int compare(PairL o1, PairL o2) {
					int cmp = Long.compare(o1.ed, o2.ed);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(PairL that) {
			int cmp = Long.compare(this.st, that.st);
			if (cmp != 0) return cmp;
			return Long.compare(this.ed, that.ed);
		}
		@Override
		public int hashCode() {
			return Objects.hash(st, ed);
		}
		@Override
		public boolean equals(Object obj) {
			PairL that = (PairL) obj;
			return this.st == that.st && this.ed == that.ed;
		}
		@Override
		public String toString() {
			return "(" + st + "," + ed + ")";
		}
	}
