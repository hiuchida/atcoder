import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=(int)1e6;
		PrimeMini pr=new PrimeMini(N+1);
		long n=sc.nextLong();
		if (pr.check(n)) {
			System.out.println(0);
			System.exit(0);
		}
		int cnt=0;
		for (int p : pr.lst) {
			while (n%p==0) {
				cnt++;
				n/=p;
			}
		}
		if (n>1) cnt++;
//		System.out.println(cnt);
		MyArray ma=new MyArray(10);
		ma.add(cnt);
		long ans=0;
		while (true) {
//			System.out.println(ans+" "+ma);
			MyArray ma2=new MyArray(10);
			for (int i=0; i<ma.size; i++) {
				int v=ma.array[i];
				if (v>1) {
					ma2.add(v/2);
					ma2.add(v-v/2);
				} else {
					ma2.add(v);
				}
			}
			if (ma.size==ma2.size) break;
			ans++;
			ma=ma2;
		}
		System.out.println(ans);
	}
	static class MyArray { //PrimeMini20250425
		int[] array;
		int size=0;
		MyArray() {
			this(100);
		}
		MyArray(int n) {
			this.array = new int[n + 1];
		}
		void add(int v) {
			if (array.length == size) array = Arrays.copyOf(array, size * 2);
			array[size++] = v;
		}
		int remove() {
			if (size == 0) return -1;
			return array[--size];
		}
		void trimToSize() {
			if (size < array.length) array = Arrays.copyOf(array, size);
		}
		int[] toArray() {
			int[] ans=new int[size];
			System.arraycopy(array, 0, ans, 0, size);
			return ans;
		}
		String join(String delimiter) {
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<size; i++) {
				if (i>0) sb.append(delimiter);
				sb.append(array[i]);
			}
			return sb.toString();
		}
		@Override
		public String toString() {
			return Arrays.toString(array) + " " + size;
		}
	}
	//1,000,000,000 15824ms
	//  100,000,000  1410ms
	//   10,000,000    90ms
	//    1,000,000    14ms
	static class PrimeMini { //PrimeMini20250425
		int n;
		boolean[] isp;
		int[] lst;
		PrimeMini(int n) {
			this.n = n;
			this.isp = new boolean[n];
			init();
		}
		void init() {
			MyArray ma=new MyArray();
			isp[0] = false;
			isp[1] = false;
			for (int i=2; i<n; i++) isp[i] = true;
			for (int i=2; i<n; i++) {
				if (isp[i]) {
					ma.add(i);
					for (int j=i*2; j<n; j+=i) {
						isp[j] = false;
					}
				}
			}
			lst=ma.toArray();
		}
		boolean check(long x) {
			if (x>(long)n*n) throw new RuntimeException();
			if (x<n) return isp[(int)x];
			for (int i : lst) {
				if (x%i==0) return false;
			}
			return true;
		}
	}
}
/*
42

48

54

53
*/
/*
1000003

2000006

549755813888

1000000000000
*/
