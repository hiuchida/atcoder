	static class Que { //Que_curpre20250416
		int cur;
		int pre;
		Que(int cur, int pre) {
			this.cur=cur;
			this.pre=pre;
		}
		@Override
		public String toString() {
			return "(" + pre + "->" + cur + ")";
		}
	}
