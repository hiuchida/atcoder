import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Counter cnt=new Counter();
		int[] ans=new int[n];
		for (int i=0; i<n; i++) {
			int a=ary[i];
			Integer key=cnt.set.ceiling(a);
			if (key==null) {
				if (k==1) {
					ans[a-1]=i+1;
				} else {
					cnt.add(a, a);
				}
			} else {
				List<Integer> lst=cnt.get(key);
				lst.add(a);
				if (lst.size()==k) {
					for (int v : lst) {
						ans[v-1]=i+1;
					}
					cnt.remove(key);
				} else {
					cnt.remove(key);
					cnt.put(a, lst);
				}
			}
//			System.out.println(i+" "+cnt);
//			System.out.println(i+" "+Arrays.toString(ans));
		}
		for (int i=0; i<n; i++) {
			if (ans[i]==0) System.out.println(-1);
			else System.out.println(ans[i]);
		}
	}
	static class Counter { //unused Counter_int_listint with TreeSet<Integer> set
		TreeSet<Integer> set = new TreeSet<>();
		Map<Integer, List<Integer>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		List<Integer> get(int k) {
			List<Integer> v = map.get(k);
			if (v == null) v = new ArrayList<>();
			return v;
		}
		void put(int k, List<Integer> v) {
			set.add(k);
			map.put(k, v);
		}
		void add(int k, int idx) {
			List<Integer> v = get(k);
			v.add(idx);
			put(k, v);
		}
		void remove(int k) {
			set.remove(k);
			map.remove(k);
		}
		Set<Integer> keySet() {
			return map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
}
/*
5 2
3 5 2 1 4

5 1
1 2 3 4 5

15 3
3 14 15 9 2 6 5 13 1 7 10 11 8 12 4
*/
