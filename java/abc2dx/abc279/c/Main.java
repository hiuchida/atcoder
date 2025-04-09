import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h=sc.nextInt();
		int w=sc.nextInt();
		String[] as=new String[h];
		for (int i=0; i<h; i++) {
			String s=sc.next();
			as[i]=s;
		}
		String[] as2=convert(h, w, as);
		String[] at=new String[h];
		for (int i=0; i<h; i++) {
			String s=sc.next();
			at[i]=s;
		}
		String[] at2=convert(h, w, at);
		Arrays.sort(as2);
		Arrays.sort(at2);
//		System.out.println(Arrays.toString(as));
//		System.out.println(Arrays.toString(at));
//		System.out.println(Arrays.toString(as2));
//		System.out.println(Arrays.toString(at2));
		for (int i=0; i<as2.length; i++) {
			if (!as2[i].equals(at2[i])) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
	static String[] convert(int h, int w, String[] ary) { //abc279_c: ary[h][w]を縦横変換して、ans[w][h]を返す
		String[] ans=new String[w];
		for (int x=0; x<w; x++) {
			StringBuilder sb=new StringBuilder();
			for (int y=0; y<h; y++) {
				sb.append(ary[y].charAt(x));
			}
			ans[x]=sb.toString();
		}
		return ans;
	}
}
/*
3 4
##.#
##..
#...
.###
..##
...#

3 3
#.#
.#.
#.#
##.
##.
.#.

2 1
#
.
#
.

8 7
#..#..#
.##.##.
#..#..#
.##.##.
#..#..#
.##.##.
#..#..#
.##.##.
....###
####...
....###
####...
....###
####...
....###
####...
*/
