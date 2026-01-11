import java.util.*;
public class Main {
	public static void main(String[] args) {
		main1(args);
		main2(1);
//		mainMyArray(4);
//		mainMyArray(1);
//		mainArrayList(1);
	}
	public static void main1(String[] args) {
		int[] ary={ 3, 2, 1, 2, 3, 1 };
		MyArray ma=new MyArray(ary.length);
		for (int v : ary) ma.add(v);
		System.out.println(ma);
		ma.sort();
		System.out.println(ma);
		ma.unique();
		System.out.println(ma);
	}
	public static void main2(int n) {
		final int N=n*10*1000*1000;
		final int C=1024*1024;
		Random r=new Random();
		int[] ary=new int[N];
		for (int i=0; i<N; i++) {
			ary[i]=r.nextInt(C);
		}
		long st1=System.currentTimeMillis();
		MyArray ma=new MyArray(N);
		for (int v : ary) ma.add(v);
		ma.sort();
		ma.unique();
		long st2=System.currentTimeMillis();
		Set<Integer> set1=new TreeSet<>();
		for (int v : ary) set1.add(v);
		long st3=System.currentTimeMillis();
		Set<Integer> set2=new HashSet<>();
		for (int v : ary) set2.add(v);
		long st4=System.currentTimeMillis();
		if (ma.size()!=set1.size()) {
			System.out.println(ma.size()+" "+set1.size()+" "+set2.size());
		}
		for (int v : ma.toArray()) {
			if (!set1.contains(v)) {
				System.out.println(v+" "+"false");
			}
			if (!set2.contains(v)) {
				System.out.println(v+" "+"false");
			}
		}
		for (int v : set2) {
			if (ma.binarySearch(v)<0) {
				System.out.println(v+" "+"<0");
			}
		}
		long tm2=st2-st1;
		long tm3=st3-st2;
		long tm4=st4-st3;
		System.out.println("end of main2 "+tm2+" "+tm3+" "+tm4);
	}
	public static void mainMyArray(int n) {
		final int N=n*100*1000*1000;
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
		System.out.println("end of mainMyArray "+tm2+" "+tm3);
	}
	public static void mainArrayList(int n) {
		final int N=n*100*1000*1000;
		long st1=System.currentTimeMillis();
		ArrayList<Integer> mia1=new ArrayList<>();
		for (int i=0; i<N; i++) {
			mia1.add(i);
//			System.out.println(i+" "+Arrays.toString(mia1.array)+" "+mia1.size);
		}
		long st2=System.currentTimeMillis();
		System.out.println(""+mia1.size());
		ArrayList<Integer> mia2=new ArrayList<>(N);
		for (int i=0; i<N; i++) {
			mia2.add(i);
//			System.out.println(i+" "+Arrays.toString(mia2.array)+" "+mia2.size);
		}
		long st3=System.currentTimeMillis();
		System.out.println(""+mia2.size());
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of mainArrayList "+tm2+" "+tm3);
	}
	static class MyArray { //MyArray_int20250505
		int[] array;
		int size=0;
		MyArray() {
			this(100);
		}
		MyArray(int n) {
			this.array = new int[n + 1];
		}
		int size() {
			return size;
		}
		int get(int idx) {
			return array[idx];
		}
		int peek() {
			return array[size-1];
		}
		void add(int v) {
			if (array.length == size) array = Arrays.copyOf(array, size * 2);
			array[size++] = v;
		}
		int remove() {
			if (size == 0) return -1;
			int x=array[--size];
			array[size]=0;
			return x;
		}
		void sort() {
			Arrays.sort(array, 0, size);
		}
		void unique() {
			if (size==0) return;
			int idx=1;
			for (int i=1; i<size; i++) {
				if (array[i-1]!=array[i]) array[idx++]=array[i];
			}
			for (int i=idx; i<size; i++) array[i]=0;
			size=idx;
		}
		int binarySearch(int key) {
			return Arrays.binarySearch(array, 0, size, key);
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
end of main2 7276 44217 11310

423624704 400000000
400000001 400000000
end of mainMyArray 1929 617
105906176 100000000
100000001 100000000
end of mainMyArray 238 85
100000000
100000000
end of mainArrayList 2154 2653
*/
