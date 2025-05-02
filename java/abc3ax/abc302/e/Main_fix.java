import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String s1=in.readLine();
		String[] f1=s1.split(" ");
		int n=Integer.parseInt(f1[0]);
		int q=Integer.parseInt(f1[1]);
		Counter cnt=new Counter();
		for (int qq=0; qq<q; qq++) {
			String s2=in.readLine();
			String[] f2=s2.split(" ");
			int c=Integer.parseInt(f2[0]);
			if (c==1) {
				int u=Integer.parseInt(f2[1]);
				int v=Integer.parseInt(f2[2]);
				cnt.add(u, v);
				cnt.add(v, u);
//				System.out.println(cnt);
			} else {
				int v=Integer.parseInt(f2[1]);
				for (int u : cnt.get(v)) {
					cnt.del(u, v);
					if (cnt.size(u)==0) cnt.remove(u);
				}
				cnt.remove(v);
//				System.out.println(cnt);
			}
			int ans=n-cnt.size();
			System.out.println(ans);
		}
	}
	static class Counter { //Counter_int_setint250502
		Map<Integer, TreeSet<Integer>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int size(int k) {
			TreeSet<Integer> v = map.get(k);
			if (v == null) return 0;
			return v.size();
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
3 7
1 1 2
1 1 3
1 2 3
2 1
1 1 2
2 2
1 1 2

2 1
2 1
*/
