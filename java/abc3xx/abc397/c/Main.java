import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		Counter c1=new Counter();
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i]=a;
			c1.add(a);
		}
		Counter c2=new Counter();
		int ans=0;
		for (int i=0; i<n; i++) {
			int a=ary[i];
			c2.add(a);
			c1.del(a);
			ans=Math.max(ans, c1.size()+c2.size());
		}
		System.out.println(ans);
	}
	static class Counter {
		Map<Integer, Integer> map=new TreeMap<>();
		int size() {
			return map.size();
		}
		void add(int a) {
			Integer v=map.get(a);
			if (v==null) {
				v=1;
			} else {
				v++;
			}
			map.put(a, v);
		}
		void del(int a) {
			Integer v=map.get(a);
			if (v!=null) {
				v--;
			} else {
				v=0;
			}
			if (v==0) {
				map.remove(a);
			} else {
				map.put(a, v);
			}
		}
	}
}
/*
5
3 1 4 1 5

10
2 5 6 5 2 1 7 9 7 2
*/
