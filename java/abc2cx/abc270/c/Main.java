import java.util.*;
public class Main {
	static int y;
	static Counter cnt=new Counter();
	static List<Integer> list=new ArrayList<>();
	static boolean[] flag;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		y = sc.nextInt();
		for (int i=0; i<n-1; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			cnt.add(u, v);
			cnt.add(v, u);
		}
//		System.out.println(cnt);
		flag=new boolean[n+1];
		dfs(x);
	}
	static void dfs(int i) {
		list.add(i);
		flag[i]=true;
		if (i==y) {
			String s=list.toString();
			s = s.replace("[", "").replace("]", "").replaceAll(",", "");
			System.out.println(s);
			System.exit(0);
		}
		List<Integer> v=cnt.get(i);
		for (int nxt : v) {
			if (flag[nxt]) continue;
			dfs(nxt);
		}
		list.remove(list.size()-1);
		flag[i]=false;
	}
	static class Counter {
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
		void add(int k, int idx) {
			List<Integer> v = get(k);
			v.add(idx);
			put(k, v);
		}
		void remove(int k) {
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
5 2 5
1 2
1 3
3 4
3 5

6 1 2
3 1
2 5
1 2
4 1
2 6
*/
