import java.util.*;
public class Main {
	static TreeMap<Integer, TreeSet<Integer>> mapx = new TreeMap<>();
	static TreeMap<Integer, TreeSet<Integer>> mapy = new TreeMap<>();
	static void addx(int x, int y) {
		TreeSet<Integer> set = mapx.get(x);
		if (set==null) set=new TreeSet<>();
		set.add(y);
		mapx.put(x, set);
	}
	static void addy(int x, int y) {
		TreeSet<Integer> set = mapy.get(y);
		if (set==null) set=new TreeSet<>();
		set.add(x);
		mapy.put(y, set);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ax = new int[n];
		int[] ay = new int[n];
		for (int i=0; i<n; i++) {
			ax[i] = sc.nextInt();
			ay[i] = sc.nextInt();
			addx(ax[i], ay[i]);
			addy(ax[i], ay[i]);
		}
		List<Integer> delx = new ArrayList<>();
		for (int kx : mapx.keySet()) {
			TreeSet<Integer> set = mapx.get(kx);
			if (set.size()==1) {
				int ky=set.first();
				set.remove(ky);
				delx.add(kx);
				mapy.get(ky).remove(kx);
			}
		}
		for (int kx : delx) {
			mapx.remove(kx);
		}
		List<Integer> dely = new ArrayList<>();
		for (int ky : mapy.keySet()) {
			TreeSet<Integer> set = mapy.get(ky);
			if (set.size()==1) {
				int kx=set.first();
				set.remove(kx);
				dely.add(ky);
				mapx.get(kx).remove(ky);
			}
		}
		for (int ky : dely) {
			mapy.remove(ky);
		}
		System.out.println(mapx);
		System.out.println(mapy);
		int[] cy=new int[n];
		for (int kx : mapx.keySet()) {
			for (int ky : mapx.get(kx)) {
				cy[ky]++;
			}
		}
		int[] cx=new int[n];
		for (int ky : mapy.keySet()) {
			for (int kx : mapy.get(ky)) {
				cx[kx]++;
			}
		}
		System.out.println(Arrays.toString(cy));
		System.out.println(Arrays.toString(cx));
	}
}
/*
6
0 0
0 1
1 0
1 1
2 0
2 1

4
0 1
1 2
2 3
3 4

7
0 1
1 0
2 0
2 1
2 2
3 0
3 2
*/
/*
9
0 3
0 4
0 5
1 3
1 4
1 5
2 3
2 4
2 5
*/
