import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int rs = sc.nextInt();
		int cs = sc.nextInt();
		int n = sc.nextInt();
		Counter cntx=new Counter();
		Counter cnty=new Counter();
		for (int i=0; i<n; i++) {
			int r=sc.nextInt();
			int c = sc.nextInt();
			cntx.add(c, r);
			cnty.add(r, c);
		}
//		System.out.println(cntx);
//		System.out.println(cnty);
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
			String d = sc.next();
			int l = sc.nextInt();
			TreeSet<Integer> v;
			Integer vv;
			int ll;
			switch (d.charAt(0)) {
			case 'U':
				v=cntx.get(cs);
				vv=v.lower(rs);
				if (vv==null) vv=1;
				else vv++;
				ll=rs-vv;
				rs-=Math.min(l, ll);
				break;
			case 'D':
				v=cntx.get(cs);
				vv=v.higher(rs);
				if (vv==null) vv=w;
				else vv--;
				ll=vv-rs;
				rs+=Math.min(l, ll);
				break;
			case 'L':
				v=cnty.get(rs);
				vv=v.lower(cs);
				if (vv==null) vv=1;
				else vv++;
				ll=cs-vv;
				cs-=Math.min(l, ll);
				break;
			case 'R':
				v=cnty.get(rs);
				vv=v.higher(cs);
				if (vv==null) vv=h;
				else vv--;
				ll=vv-cs;
				cs+=Math.min(l, ll);
				break;
			}
			System.out.println(rs+" "+cs);
		}
	}
	static class Counter {
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
		void add(int k, int idx) {
			TreeSet<Integer> v = get(k);
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
5 5 4 4
3
5 3
2 2
1 4
4
L 2
U 3
L 2
R 4

6 6 6 3
7
3 1
4 3
2 6
3 4
5 5
1 1
3 2
10
D 3
U 3
L 2
D 2
U 3
D 3
U 3
R 3
L 3
D 1
*/
