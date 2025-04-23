import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		MyArray ma=new MyArray();
		ma.add(ary[0]);
		for (int i=1; i<n; i++) {
			if (Math.abs(ary[i]-ary[i-1])>1) {
				int d=0;
				if (ary[i]>ary[i-1]) d=1;
				else d=-1;
				for (int j=ary[i-1]+d; j!=ary[i]; j+=d) {
					ma.add(j);
				}
				ma.add(ary[i]);
			} else {
				ma.add(ary[i]);
			}
		}
		String ans=ma.join(" ");
		System.out.println(ans);
	}
	static class MyArray { //MyArray_int20250423
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
	}
}
/*
4
2 5 1 2

6
3 4 5 6 5 4
*/
