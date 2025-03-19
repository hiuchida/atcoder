import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Map<Integer, Integer> map=new TreeMap<>();
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			int c = sc.nextInt();
			Integer v=map.get(c);
			if (v==null) {
				v=a;
			} else {
				v=Math.min(v, a);
			}
			map.put(c, v);
		}
		int ans=0;
		for (int v : map.values()) {
			ans=Math.max(ans, v);
		}
		System.out.println(ans);
	}
}
/*
4
100 1
20 5
30 5
40 1

10
68 3
17 2
99 2
92 4
82 4
10 3
100 2
78 1
3 1
35 4
*/
