	static class Aggregate2D { //Aggregate2D20250505
		int h;
		int w;
		int[][] element;
		Aggregate2D(int[][] ary) {
			h=ary.length;
			w=ary[0].length;
			element=new int[h+1][w+1];
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					element[i+1][j+1]=element[i+1][j]+element[i][j+1]-element[i][j]+ary[i][j];
				}
			}
		}
		public int sum(int lt, int tp) {
			return element[lt][tp];
		}
		public int sum(int lt, int tp, int rt, int bm) {
			return element[rt][bm]-element[rt][tp]-element[lt][bm]+element[lt][tp];
		}
		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder();
			for (int i=0; i<h+1; i++) {
				for (int j=0; j<w+1; j++) {
					if (j>0) sb.append(" ");
					sb.append(element[i][j]);
				}
				sb.append(System.getProperty("line.separator"));
			}
			return sb.toString();
		}
	}
