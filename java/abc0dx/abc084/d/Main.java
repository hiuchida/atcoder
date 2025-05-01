import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int M=(int)1e5;
		PrimeMini pr=new PrimeMini(M+1);
		int[] ary=new int[M+1];
		int[] sum=new int[M+1];
		for (int i=2; i<=M; i++) {
			int j=(i+1)/2;
			if (pr.isp[i] && pr.isp[j]) ary[i]=1;
			sum[i]=sum[i-1]+ary[i];
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(sum));
		int q=sc.nextInt();
		for (int qq=0; qq<q; qq++) {
			int l=sc.nextInt();
			int r=sc.nextInt();
			int ans=sum[r]-sum[l-1];
			System.out.println(ans);
		}
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
1
3 7

4
13 13
7 11
7 11
2017 2017

6
1 53
13 91
37 55
19 51
73 91
13 49
*/
