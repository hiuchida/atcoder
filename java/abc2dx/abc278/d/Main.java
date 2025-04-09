import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		TreeMap<Integer,Bean> map=new TreeMap<>();
		for (int i=0; i<n; i++) {
			map.put(i+1, new Bean(ary[i]));
		}
		int def=-1;
//		System.out.println(map+" "+def);
		int q=sc.nextInt();
		for (int qq=0; qq<q; qq++) {
			int c=sc.nextInt();
			int x;
			int i;
			Bean b;
			switch (c) {
			case 1:
				x=sc.nextInt();
				def=x;
				map.clear();
//				System.out.println(map+" "+def);
				break;
			case 2:
				i=sc.nextInt();
				x=sc.nextInt();
				b=map.get(i);
				if (b==null) {
					b=new Bean(def+x);
					map.put(i, b);
				} else {
					b.add(x);
				}
//				System.out.println(map+" "+def);
				break;
			case 3:
				i=sc.nextInt();
				b=map.get(i);
				if (b==null) {
					System.out.println(def);
				} else {
					System.out.println(b.v);
				}
				break;
			}
		}
	}
	static class Bean { //Bean_long20250409
		long v;
		Bean(long v) {
			this.v=v;
		}
		void add(long n) {
			v+=n;
		}
		@Override
		public String toString() {
			return "(" + v + ")";
		}
	}
}
/*
5
3 1 4 1 5
6
3 2
2 3 4
3 3
1 1
2 3 4
3 3

1
1000000000
8
2 1 1000000000
2 1 1000000000
2 1 1000000000
2 1 1000000000
2 1 1000000000
2 1 1000000000
2 1 1000000000
3 1

10
1 8 4 15 7 5 7 5 8 0
20
2 7 0
3 7
3 8
1 7
3 3
2 4 4
2 4 9
2 10 5
1 10
2 4 2
1 10
2 3 1
2 8 11
2 3 14
2 1 9
3 8
3 8
3 1
2 6 5
3 7
*/
