import java.util.*;
public class Main {
	static int n;
	static String[] ary;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		int m=sc.nextInt();
		ary=new String[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.next();
		}
		boolean[] flag=new boolean[n+1];
		MyArray ma=new MyArray(8);
		dfs(0, flag, ma);
		System.out.println("No");
	}
	static void dfs(int i, boolean[] flag, MyArray ma) {
//		System.out.println(i+" "+ma);
		if (i==n) {
			System.out.println("Yes");
			System.exit(0);
		}
		for (int nxt=0; nxt<n; nxt++) {
			if (flag[nxt]) continue;
			if (ma.size()>0) {
				String pre=ma.peek();
				if (!check(pre, ary[nxt])) continue;
			}
			flag[nxt]=true;
			ma.add(ary[nxt]);
			dfs(i+1, flag, ma);
			flag[nxt]=false;
			ma.remove();
		}
	}
	static boolean check(String a, String b) {
		int cnt=0;
		for (int i=0; i<a.length(); i++) {
			if (a.charAt(i)!=b.charAt(i)) cnt++;
		}
		return cnt<=1;
	}
	static class MyArray { //MyArray_str20250501
		String[] array;
		int size=0;
		MyArray() {
			this(100);
		}
		MyArray(int n) {
			this.array = new String[n + 1];
		}
		int size() {
			return size;
		}
		String get(int idx) {
			return array[idx];
		}
		String peek() {
			return array[size-1];
		}
		void add(String v) {
			if (array.length == size) array = Arrays.copyOf(array, size * 2);
			array[size++] = v;
		}
		String remove() {
			if (size == 0) return null;
			String x=array[--size];
			array[size]=null;
			return x;
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
4 4
bbed
abcd
abed
fbed

2 5
abcde
abced

8 4
fast
face
cast
race
fact
rice
nice
case
*/
