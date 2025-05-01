import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int d=sc.nextInt();
		Counter cnt=new Counter();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			cnt.inc(a);
		}
//		System.out.println(cnt);
		List<Integer> list=new ArrayList<>(cnt.keySet());
		long ans=0;
		for (int key : list) {
			int v1=cnt.get(key);
			if (v1==0) continue;
//			System.out.println(cnt+" "+key+" ans="+ans);
			cnt.put(key, 0);
			List<Integer> lst=new ArrayList<>();
			lst.add(v1);
			for (int i=1; true; i++) {
				int v2=cnt.get(key+i*d);
				if (v2==0) break;
				cnt.put(key+i*d, 0);
				lst.add(v2);
			}
//			System.out.println(lst);
			ans+=calc(lst);
		}
		System.out.println(ans);
	}
	static long calc(List<Integer> lst) {
		int[][] dp=new int[2][lst.size()+1];
		Arrays.fill(dp[0], Integer.MAX_VALUE);
		Arrays.fill(dp[1], Integer.MAX_VALUE);
		dp[0][0]=(int)1e8;
		dp[1][0]=0;
		for (int i=0; i<lst.size(); i++) {
			dp[0][i+1]=Math.min(dp[0][i+1], dp[1][i]);
			dp[1][i+1]=Math.min(dp[1][i+1], dp[0][i]+lst.get(i));
			dp[1][i+1]=Math.min(dp[1][i+1], dp[1][i]+lst.get(i));
		}
//		for (int i=0; i<2; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		long ans=Math.min(dp[0][lst.size()], dp[1][lst.size()]);
//		System.out.println(ans);
		return ans;
	}
	static class Counter { //Counter_int_int20250416
		Map<Integer, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(int k) {
			Integer v = map.get(k);
			if (v == null) v = 0;
			return v;
		}
		void put(int k, int v) {
			if (v==0) map.remove(k);
			else map.put(k, v);
		}
		void inc(int k) {
			int v = get(k);
			v++;
			put(k, v);
		}
		void dec(int k) {
			int v = get(k);
			v--;
			put(k, v);
		}
		void add(int k, int x) {
			int v = get(k);
			v += x;
			put(k, v);
		}
		void sub(int k, int x) {
			int v = get(k);
			v -= x;
			put(k, v);
		}
		NavigableSet<Integer> keySet() {
			return (NavigableSet<Integer>) map.keySet();
		}
		@Override
		public int hashCode() {
			return Objects.hash(map);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			Counter other = (Counter) obj;
			return Objects.equals(map, other.map);
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
}
/*
5 2
3 1 4 1 5

4 3
1 6 1 8

10 3
1 6 2 10 2 3 2 10 6 4
*/
/*
6 2
1 3 3 5 5 7

6 2
1 1 3 5 7 7

7 2
1 3 3 3 5 5 7

7 2
1 3 3 5 5 5 7
*/
