import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Counter cnt=new Counter();
		int[] indeg=new int[n+1];
		for (int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			cnt.add(a, b);
			indeg[b]++;
		}
//		System.out.println(cnt);
//		System.out.println(Arrays.toString(indeg));
		MyHeapque que=new MyHeapque();
		for (int i=1; i<=n; i++) {
			if (indeg[i]==0) que.add(i);
		}
		List<Integer> list=new ArrayList<>();
		while (que.size()>0) {
			int x=que.remove();
			list.add(x);
			List<Integer> set=cnt.get(x);
			for (int nxt : set) {
				indeg[nxt]--;
				if (indeg[nxt]==0) {
					que.add(nxt);
				}
			}
		}
		if (list.size()!=n) {
			System.out.println(-1);
			System.exit(0);
		}
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
		System.out.println();
	}
	static class MyHeapque { //MyHeapque_int20250504
		int[] ary;
		int size;
		MyHeapque() {
			this(100);
		}
		MyHeapque(int n) {
			this.ary=new int[n];
			this.size=0;
		}
		int size() {
			return size;
		}
		void add(int i) {
			if (ary.length == size) ary = Arrays.copyOf(ary, size * 2);
			ary[size]=i;
			upheap(ary, size);
			size++;
		}
		int remove() {
			int x=ary[0];
			size--;
			ary[0]=ary[size];
			ary[size]=0;
			downheap(ary, size);
			return x;
		}
		private void upheap(int[] ary, int i) {
			int x=ary[i];
			int j=i;
			while (i>0) {
				j=(i-1)/2;
				if (ary[j]>x) {
					ary[i]=ary[j];
				} else {
					break;
				}
				i=j;
			}
			ary[i]=x;
		}
		private void downheap(int[] ary, int n) {
			int i=0;
			while (true) {
				int x=ary[i];
				int lt=i*2+1;
				int rt=i*2+2;
				if (lt>=n) break;
				if (rt<n && ary[lt]>ary[rt]) lt=rt;
				if (x<=ary[lt]) break;
				ary[i]=ary[lt];
				ary[lt]=x;
				i=lt;
			}
		}
		@Override
		public String toString() {
			return Arrays.toString(ary)+" "+size;
		}
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
4 3
2 1
3 4
2 4

2 3
1 2
1 2
2 1
*/
/*
9 3
3 4
4 5
5 2

9 3
1 2
1 2
1 2
*/
