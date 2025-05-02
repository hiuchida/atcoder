import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int q=sc.nextInt();
		boolean[] flag=new boolean[n+1];
		Counter cnt=new Counter();
		for (int qq=0; qq<q; qq++) {
			int cmd=sc.nextInt();
			int x=sc.nextInt();
			if (cmd==1) {
				int y=sc.nextInt();
				cnt.add(x, y);
			} else if (cmd==2) {
				flag[x]=true;
			} else {
				int y=sc.nextInt();
				if (flag[x]) System.out.println("Yes");
				else if (cnt.get(x).contains(y)) System.out.println("Yes");
				else System.out.println("No");
			}
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
2 3 5
1 1 2
3 1 1
3 1 2
2 2
3 2 3

5 5 10
2 2
3 4 4
1 1 1
1 4 1
1 4 2
1 4 4
1 2 4
3 3 2
3 5 4
3 2 1
*/
