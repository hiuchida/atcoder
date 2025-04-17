import java.util.*;
public class Main {
	static int h;
	static int w;
	static int[][] ary;
	static long ans=0;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		h=sc.nextInt();
		w=sc.nextInt();
		ary=new int[h][w];
		for (int y=0; y<h; y++) {
			for (int x=0; x<w; x++) {
				ary[y][x]=sc.nextInt();
			}
		}
//		for (int y=0; y<h; y++) {
//			System.out.println(Arrays.toString(ary[y]));
//		}
		Set<Integer> set=new HashSet<>();
		dfs(0, 0, set);
		System.out.println(ans);
	}
	static void dfs(int y, int x, Set<Integer> set) {
		int v=ary[y][x];
		if (set.contains(v)) {
			return;
		}
		if (y==h-1 && x==w-1) {
			ans++;
			return;
		}
		set.add(v);
		if (x+1<w) dfs(y, x+1, set);
		if (y+1<h) dfs(y+1, x, set);
		set.remove(v);
	}
}
/*
3 3
3 2 2
2 1 3
1 5 4

10 10
1 2 3 4 5 6 7 8 9 10
11 12 13 14 15 16 17 18 19 20
21 22 23 24 25 26 27 28 29 30
31 32 33 34 35 36 37 38 39 40
41 42 43 44 45 46 47 48 49 50
51 52 53 54 55 56 57 58 59 60
61 62 63 64 65 66 67 68 69 70
71 72 73 74 75 76 77 78 79 80
81 82 83 84 85 86 87 88 89 90
91 92 93 94 95 96 97 98 99 100
*/
