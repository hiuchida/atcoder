import java.util.*;
public class Main {
	static int h;
	static int w;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		h=sc.nextInt();
		w=sc.nextInt();
		String[] aa=new String[h];
		String[] ab=new String[h];
		for (int y=0; y<h; y++) {
			String s=sc.next();
			aa[y]=s;
		}
		for (int y=0; y<h; y++) {
			String s=sc.next();
			ab[y]=s;
		}
		for (int s=0; s<h; s++) {
			for (int t=0; t<w; t++) {
				String[] ac=shift(aa, s, t);
//				for (int y=0; y<h; y++) {
//					System.out.println(ac[y]);
//				}
//				System.out.println();
				if (comp(ac, ab)) {
					System.out.println("Yes");
					System.exit(0);
				}
			}
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println();
//		System.out.println("Yes");
		System.out.println("No");
	}
	static String[] shift(String[] ary, int sy, int sx) {
		String[] ans=new String[h];
		for (int y=0; y<h; y++) {
			String s=ary[(y+sy)%h];
			char[] ac=new char[w];
			for (int x=0; x<w; x++) {
				ac[x]=s.charAt((x+sx)%w);
			}
			ans[y]=new String(ac);
		}
		return ans;
	}
	static boolean comp(String[] aa, String[] ab) {
		for (int y=0; y<h; y++) {
			if (!aa[y].equals(ab[y])) return false;
		}
		return true;
	}
}
/*
4 3
..#
...
.#.
...
#..
...
.#.
...

3 2
##
##
#.
..
#.
#.

4 5
#####
.#...
.##..
..##.
...##
#...#
#####
...#.

10 30
..........##########..........
..........####....###.....##..
.....##....##......##...#####.
....####...##..#####...##...##
...##..##..##......##..##....#
#.##....##....##...##..##.....
..##....##.##..#####...##...##
..###..###..............##.##.
.#..####..#..............###..
#..........##.................
................#..........##.
######....................####
....###.....##............####
.....##...#####......##....##.
.#####...##...##....####...##.
.....##..##....#...##..##..##.
##...##..##.....#.##....##....
.#####...##...##..##....##.##.
..........##.##...###..###....
...........###...#..####..#...
*/
