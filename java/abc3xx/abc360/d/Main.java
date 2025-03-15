import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int t = sc.nextInt();
		String s = sc.next();
		TreeSet<Pair> set0=new TreeSet<>();
		TreeSet<Integer> set1=new TreeSet<>();
		for (int i=0; i<n; i++) {
			int x = sc.nextInt();
			if (s.charAt(i)=='0') set0.add(new Pair(x));
			else set1.add(x);
		}
		int idx=0;
		for (Pair p : set0) {
			p.idx=idx;
			idx++;
		}
//		System.out.println(set0);
//		System.out.println(set1);
		long ans=0;
		for (Integer x : set1) {
			Pair lt=set0.higher(new Pair(x));
			Pair rt=set0.floor(new Pair(x+2L*t));
//			System.out.println(x+" "+lt+" "+(x+2*t)+" "+rt);
			if (lt!=null && rt!=null && rt.idx>=lt.idx) {
				ans += rt.idx-lt.idx+1;
			}
		}
		System.out.println(ans);
	}
	static class Pair implements Comparable<Pair> {
		long v;
		int idx;
		Pair(long v) {
			this.v=v;
		}
		@Override
		public int compareTo(Main.Pair that) {
			return Long.compare(this.v, that.v);
		}
		@Override
		public String toString() {
			return "(" + v + "," + idx + ")";
		}
	}
}
/*
6 3
101010
-5 -1 0 1 2 4

13 656320850
0100110011101
-900549713 -713494784 -713078652 -687818593 -517374932 -498415009 -472742091 -390030458 -379340552 -237481538 -44636942 352721061 695864366
*/
/*
4 3
1010
10 0 20 1

4 3
1010
0 10 1 20

4 3
1010
10 0 11 40
*/
