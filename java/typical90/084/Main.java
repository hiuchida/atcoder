import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
		long ans=calc(n, 2);
		MyArray mao=new MyArray(n);
		MyArray max=new MyArray(n);
		for (int i=0; i<n; i++) {
			if (ary[i]=='o') {
				int j=i;
				for ( ; j<n; j++) {
					if (ary[j]!='o') break;
				}
				mao.add(j-i);
				i=j-1;
			} else {
				int j=i;
				for ( ; j<n; j++) {
					if (ary[j]!='x') break;
				}
				max.add(j-i);
				i=j-1;
			}
		}
//		System.out.println(mao);
//		System.out.println(max);
		for (int v : mao.toArray()) ans-=calc(v, 2);
		for (int v : max.toArray()) ans-=calc(v, 2);
		System.out.println(ans);
	}
	static long calc(int a, int b) { //abc295_d,abc350_d,abc355_d,typical90_084: aCbの組み合わせ数
		long ans=1;
		for (int i=0; i<b; i++) ans*=a-i;
		for (int i=1; i<=b; i++) ans/=i;
		return ans;
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
4
ooxo

5
oxoxo

5
ooooo

7
xxoooxx
*/
