	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		final String s;
		Point(int y, int x, String s) {
			this.y = y;
			this.x = x;
			this.s = s;
		}
		@Override
		public int compareTo(Point that) {
			int cmp = Integer.compare(this.y, that.y);
			if (cmp != 0) return cmp;
			return Integer.compare(this.x, that.x);
		}
		@Override
		public int hashCode() {
			return Objects.hash(y, x);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.y == that.y && this.x == that.x;
		}
		@Override
		public String toString() {
			return "(" + y + "," + x + ":" + s + ")";
		}
	}
