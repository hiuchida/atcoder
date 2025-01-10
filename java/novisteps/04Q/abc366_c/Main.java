import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static long ans = 0;
	static int n;
	static int k;
	static int[] ary;
	static TreeMap<Integer,Integer> map = new TreeMap<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
			int c = sc.nextInt();
			int x;
			switch (c) {
			case 1:
				x = sc.nextInt();
				Integer v = map.get(x);
				if (v == null) v = 0;
				v++;
				map.put(x, v);
				break;
			case 2:
				x = sc.nextInt();
				Integer vv = map.get(x);
				vv--;
				if (vv>0) map.put(x, vv);
				else map.remove(x);
				break;
			case 3:
				System.out.println(map.size());
				break;
			}
		}
	}
}
/*
8
1 3
1 1
1 4
3
2 1
3
1 5
3

8
1 2
1 2
3
2 2
1 4
1 4
2 2
3
*/
