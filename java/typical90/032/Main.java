import java.util.*;
public class Main {
	static int n;
	static int[][] ary;
	static Counter cnt;
	static long ans=Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		ary=new int[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				ary[i][j]=sc.nextInt();
			}
		}
		int m=sc.nextInt();
		cnt=new Counter();
		for (int i=0; i<m; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			cnt.add(x, y);
			cnt.add(y, x);
		}
//		System.out.println(cnt);
		boolean[] flag=new boolean[n+1];
		MyArray ma=new MyArray(10);
		dfs(0, 0, flag, ma);
		if (ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
	}
	static void dfs(int i, long v, boolean[] flag, MyArray ma) {
//		System.out.println(i+" "+v+" "+ma);
		if (i==n) {
			ans=Math.min(ans, v);
			return;
		}
		TreeSet<Integer> set=new TreeSet<>();
		if (ma.size()>0) {
			int pre=ma.peek();
			set=cnt.get(pre+1);
		}
		for (int nxt=0; nxt<n; nxt++) {
			if (flag[nxt]) continue;
			if (set.contains(nxt+1)) continue;
			flag[nxt]=true;
			ma.add(nxt);
			dfs(i+1, v+ary[nxt][i], flag, ma);
			flag[nxt]=false;
			ma.remove();
		}
	}
	static class MyArray { //MyArray_int20250501
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
	static class Counter { //Counter_int_setint250414
		Map<Integer, TreeSet<Integer>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		TreeSet<Integer> get(int k) {
			TreeSet<Integer> v = map.get(k);
			if (v == null) v = new TreeSet<>();
			return v;
		}
		void put(int k, TreeSet<Integer> v) {
			map.put(k, v);
		}
		void remove(int k) {
			map.remove(k);
		}
		boolean is(int k, int idx) {
			TreeSet<Integer> v = get(k);
			return v.contains(idx);
		}
		void add(int k, int idx) {
			TreeSet<Integer> v = get(k);
			v.add(idx);
			put(k, v);
		}
		void del(int k, int idx) {
			TreeSet<Integer> v = get(k);
			v.remove(idx);
			put(k, v);
		}
		NavigableSet<Integer> keySet() {
			return (NavigableSet<Integer>) map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
}
/*
3
1 10 100
10 1 100
100 10 1
1
1 2

4
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
3
1 2
1 3
2 3

3
1 10 100
10 1 100
100 10 1
0
*/
