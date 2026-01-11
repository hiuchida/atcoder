	static class Point implements Comparable<Point> {
		final int st;
		final int ed;
		final int idx;
		Point(int st, int ed, int idx) {
			this.st = st;
			this.ed = ed;
			this.idx = idx;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.st, o2.st);
					if (cmp != 0) return -cmp;
					cmp = Integer.compare(o1.idx, o2.idx);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.ed, o2.ed);
					if (cmp != 0) return -cmp;
					cmp = Integer.compare(o1.idx, o2.idx);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator3() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.st+o1.ed, o2.st+o2.ed);
					if (cmp != 0) return -cmp;
					cmp = Integer.compare(o1.idx, o2.idx);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(Point that) {
			int cmp = Integer.compare(this.st, that.st);
			if (cmp != 0) return cmp;
			return Integer.compare(this.ed, that.ed);
		}
		@Override
		public int hashCode() {
			return Objects.hash(st, ed);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.st == that.st && this.ed == that.ed;
		}
		@Override
		public String toString() {
			return "(" + st + "," + ed + "," + idx + ")";
		}
	}
