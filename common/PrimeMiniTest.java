import java.util.*;
public class Main {
	public static void main(String[] args) {
		main_init(args);
		main_check(args);
	}
	//1,000,000,000 15824ms
	//  100,000,000  1410ms
	//   10,000,000    90ms
	//    1,000,000    14ms
	public static void main_init(String[] args) {
		final int N=1*1000*1000;
		long st1=System.currentTimeMillis();
		PrimeMini pr=new PrimeMini(N+1);
		long st2=System.currentTimeMillis();
		long tm2=st2-st1;
		System.out.println("end of main_init "+tm2);
	}
	//400,000,000 (N=20,000) 1ms 6,696ms 426,531ms
	//100,000,000 (N=10,000) 0ms 1,535ms 112,217ms
	//  1,000,000 (N= 1,000) 0ms    21ms     149ms
	//     10,000 (N=   100) 0ms     1ms       2ms
	public static void main_check(String[] args) {
		final int N=100;
		long st1=System.currentTimeMillis();
		PrimeMini pr1=new PrimeMini(N+1);
		long st2=System.currentTimeMillis();
		PrimeMini pr2=new PrimeMini(N*N+1);
		long st3=System.currentTimeMillis();
		for (int i=0; i<=N*N; i++) {
			if (pr1.check(i)!=pr2.isp[i]) {
				System.out.println(i);
			}
		}
		long st4=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		long tm4=st4-st3;
		System.out.println("end of main_check "+tm2+" "+tm3+" "+tm4);
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
*/
