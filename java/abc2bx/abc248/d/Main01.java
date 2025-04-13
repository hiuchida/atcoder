import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		Counter c=new Counter();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			ary[i]=a;
			c.add(a, i+1);
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(c);
		int q=sc.nextInt();
		for (int i=0; i<q; i++) {
			int l=sc.nextInt();
			int r=sc.nextInt();
			int x=sc.nextInt();
			List<Integer> v=c.get(x);
			int ll=search(v, l);
			int rr=search(v, r);
//			System.out.println(ll+" "+rr);
			System.out.println(rr-ll);
		}
	}
	static int search(List<Integer> v, int l) {
		int x=Collections.binarySearch(v, l);
		if (x<0) x=~x;
		return x;
	}
	static class Counter { //Counter_int_listint20250410
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
			map.put(k, v);
		}
		void remove(int k) {
			map.remove(k);
		}
		void add(int k, int idx) {
			List<Integer> v = get(k);
			v.add(idx);
			put(k, v);
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
5
3 1 4 1 5
4
1 5 1
2 4 3
1 5 2
1 3 3
*/
