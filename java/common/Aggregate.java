	static class Aggregate { //Aggregate20250505
		int n;
		int[] element;
		Aggregate(int[] ary) {
			n=ary.length;
			element=new int[n+1];
			for (int i=0; i<n; i++) {
				element[i+1]=element[i]+ary[i];
			}
		}
		int sum(int k) {
			return element[k];
		}
		int sum(int lt, int rt) {
			return element[rt]-element[lt];
		}
		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder();
			for (int i=0; i<n+1; i++) {
				if (i>0) sb.append(" ");
				sb.append(element[i]);
			}
			return sb.toString();
		}
	}
