	static class Que { //Que_curprestp20250416
		int cur;
		int pre;
		int stp;
		Que(int cur, int pre, int stp) {
			this.cur=cur;
			this.pre=pre;
			this.stp=stp;
		}
		@Override
		public String toString() {
			return "(" + cur + "," + pre + "," + stp + ")";
		}
	}
