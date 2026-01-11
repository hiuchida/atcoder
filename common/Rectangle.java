	static class Rectangle {
		final Point st;
		final Point ed;
		final int dist;
		Rectangle(Point st, Point ed) {
			this.st=st;
			this.ed=ed;
			this.dist = st.calcL1(ed);
		}
		Rectangle(int y0, int x0, int y1, int x1) {
			this(new Point(y0, x0), new Point(y1, x1));
		}
		@Override
		public String toString() {
			return "(" + dist + ":" + st.y + "," + st.x + ":" + ed.y + "," + ed.x + ")";
		}
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.y, o2.y);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.x, o2.x);
					return cmp;
				}
			};
		}
		int calcL1(Point that) { //thatまでのマンハッタン距離
			int dx = this.x - that.x;
			int dy = this.y - that.y;
			return Math.abs(dx) + Math.abs(dy);
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
			return "(" + y + "," + x + ")";
		}
	}
