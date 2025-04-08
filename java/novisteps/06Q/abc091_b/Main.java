import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Counter cntn=new Counter();
		for (int i=0; i<n; i++) {
			String s=sc.next();
			cntn.inc(s);
		}
		int m=sc.nextInt();
		Counter cntm=new Counter();
		for (int i=0; i<m; i++) {
			String s=sc.next();
			cntm.inc(s);
		}
//		System.out.println(cntn);
//		System.out.println(cntm);
		long ans=0;
		for (String s : cntn.keySet()) {
			int a=cntn.get(s);
			int b=cntm.get(s);
			ans=Math.max(ans, a-b);
		}
		System.out.println(ans);
	}
	static class Counter { //Counter_s
		Map<String, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(String k) {
			Integer v = map.get(k);
			if (v == null) v = 0;
			return v;
		}
		void put(String k, int v) {
			if (v==0) map.remove(k);
			else map.put(k, v);
		}
		void inc(String k) {
			int v = get(k);
			v++;
			put(k, v);
		}
		void dec(String k) {
			int v = get(k);
			v--;
			put(k, v);
		}
		void add(String k, int x) {
			int v = get(k);
			v += x;
			put(k, v);
		}
		void sub(String k, int x) {
			int v = get(k);
			v -= x;
			put(k, v);
		}
		Set<String> keySet() {
			return map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
}
/*
3
apple
orange
apple
1
grape

3
apple
orange
apple
5
apple
apple
apple
apple
apple

1
voldemort
10
voldemort
voldemort
voldemort
voldemort
voldemort
voldemort
voldemort
voldemort
voldemort
voldemort

6
red
red
blue
yellow
yellow
red
5
red
red
yellow
green
blue
*/
