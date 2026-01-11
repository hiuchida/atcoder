	static class Coords {
		int w;
		Coords(int w) {
			this.w=w;
		}
		int size(int h) {
			return h*w;
		}
		int getOffset(int y, int x) {
			return y*w+x;
		}
		int getY(int o) {
			return o/w;
		}
		int getX(int o) {
			return o%w;
		}
		String toString(int o) {
			return toString(getY(o), getX(o));
		}
		String toString(int y, int x) {
			return "(" + y + "," + x + ")";
		}
	}
