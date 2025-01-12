import java.util.*;
public class Main {
	static class Counter {
		Map<Integer, Integer> map = new TreeMap<>();
		int get(int c) {
			Integer v = map.get(c);
			if (v == null) v = 0;
			return v;
		}
		void inc(int c) {
			int v = get(c);
			v++;
			map.put(c, v);
		}
		Set<Integer> keySet() {
			return map.keySet();
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n+1];
		for (int i=1; i<=n; i++) {
			int a = sc.nextInt();
			ary[i]=a;
		}
		int[] dif = new int[n+2];
		dif[n+1] = Integer.MAX_VALUE;
		for (int i=1; i<=n; i++) {
			dif[i]=ary[i]-ary[i-1];
		}
		Counter cnt = new Counter();
		for (int i=2; i<n+1; i++) {
			if (dif[i]==dif[i+1]) {
				int c=0;
				int j=i+1;
				for (; j<=n; j++) {
					if (dif[i]==dif[j])
						c=j-i+1;
					else
						break;
				}
				i=j-1;
				cnt.inc(c);
			} else {
				cnt.inc(1);
			}
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(dif));
//		System.out.println(map);
		long ans = 0;
		ans += n;
		for (int c : cnt.keySet()) {
			int v=cnt.get(c);
			long nc = (long)c*(c+1)/2;
			ans += nc*v;
		}
		System.out.println(ans);
	}
}
/*
1
2

2
2 4

2
1 4

3
2 4 6
*/
