	static class StringHash { //StringHash20250501
		static final int M = Integer.MAX_VALUE;
		long[] pow;
		long[] ary;
		StringHash(String S) {
			int N = S.length();
			int[] T = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				T[i] = (int)(S.charAt(i - 1) - 'a') + 1;
			}
			pow = new long[N + 1];
			pow[0] = 1;
			for (int i = 1; i <= N; i++) {
				pow[i] = pow[i - 1] * 100 % M;
			}
			ary = new long[N + 1];
			ary[0] = 1;
			for (int i = 1; i <= N; i++) {
				ary[i] = (ary[i - 1] * 100 + T[i]) % M;
			}
		}
		// S[l, r] のハッシュ値を返す関数
		long hashValue(int l, int r) {
			long val = ary[r] - ary[l - 1] * pow[r - l + 1] % M;
			if (val < 0) val += M;
			return val;
		}
	}
