import java.util.*;
public class Main {
	public static void main(String[] args) {
		final int N=4*100*1000*1000;
		long st1=System.currentTimeMillis();
		MyArray mia1=new MyArray();
		for (int i=0; i<N; i++) {
			mia1.add(i);
//			System.out.println(i+" "+Arrays.toString(mia1.array)+" "+mia1.size);
		}
		long st2=System.currentTimeMillis();
		System.out.println(mia1.array.length+" "+mia1.size);
		MyArray mia2=new MyArray(N);
		for (int i=0; i<N; i++) {
			mia2.add(i);
//			System.out.println(i+" "+Arrays.toString(mia2.array)+" "+mia2.size);
		}
		long st3=System.currentTimeMillis();
		System.out.println(mia2.array.length+" "+mia2.size);
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of check "+tm2+" "+tm3);
	}
	static class MyArray { //MyArray_int20250424
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
}
/*
419430400 400000000
400000001 400000000
end of check 2036 562
*/
