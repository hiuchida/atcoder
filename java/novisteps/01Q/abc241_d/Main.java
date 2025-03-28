import java.util.*;
public class Main {
	static TreeMap<Long,Integer> map=new TreeMap<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
			int c = sc.nextInt();
			long x = sc.nextLong();
			int k;
			switch (c) {
			case 1:
				add(x);
//				System.out.println(map);
				break;
			case 2:
				k = sc.nextInt();
				low(x, k);
				break;
			case 3:
				k = sc.nextInt();
				high(x, k);
				break;
			}
		}
	}
	static void add(long x) {
		Integer v=map.get(x);
		if (v==null) {
			map.put(x, 1);
		} else {
			v++;
			map.put(x, v);
		}
	}
	static void low(long x, int k) {
		Integer val0=map.get(x);
		if (val0!=null) {
			k-=val0;
			if (k<=0) {
				System.out.println(x);
				return;
			}
		}
		while (true) {
			Long key=map.lowerKey(x);
//			System.out.println("low1 "+x+" "+key+" "+k);
			if (key==null) {
				System.out.println(-1);
				return;
			}
			int val=map.get(key);
			k-=val;
//			System.out.println("low2 "+key+" "+val+" "+k);
			if (k<=0) {
				System.out.println(key);
				return;
			}
			x=key;
		}
	}
	static void high(long x, int k) {
		Integer val0=map.get(x);
		if (val0!=null) {
			k-=val0;
			if (k<=0) {
				System.out.println(x);
				return;
			}
		}
		while (true) {
			Long key=map.higherKey(x);
//			System.out.println("high1 "+x+" "+key+" "+k);
			if (key==null) {
				System.out.println(-1);
				return;
			}
			int val=map.get(key);
			k-=val;
//			System.out.println("high2 "+key+" "+val+" "+k);
			if (k<=0) {
				System.out.println(key);
				return;
			}
			x=key;
		}
	}
}
/*
11
1 20
1 10
1 30
1 20
3 15 1
3 15 2
3 15 3
3 15 4
2 100 5
1 1
2 100 5
*/
/*
9
1 20
1 10
1 30
2 20 1
2 20 2
2 20 3
3 20 1
3 20 2
3 20 3
*/
