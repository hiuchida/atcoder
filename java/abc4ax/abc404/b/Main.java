import java.util.*;
public class Main {
	static int n;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		String[] as=new String[n];
		for (int i=0; i<n; i++) {
			as[i]=sc.next();
		}
		String[] at=new String[n];
		for (int i=0; i<n; i++) {
			at[i]=sc.next();
		}
		long ans=Integer.MAX_VALUE;
		for (int r=0; r<4; r++) {
			long x=check(as, at);
			ans=Math.min(ans, r+x);
			as=rotate(as);
//			System.out.println(r);
//			for (int i=0; i<n; i++) {
//				System.out.println(as[i]);
//			}
		}
		System.out.println(ans);
	}
	static long check(String[] as, String[] at) {
		long ans=0;
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (as[y].charAt(x)!=at[y].charAt(x)) ans++;
			}
		}
		return ans;
	}
	static String[] rotate(String[] as) {
		char[][] ary=new char[n][n];
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				ary[x][n-1-y]=as[y].charAt(x);
			}
		}
		String[] ans=new String[n];
		for (int y=0; y<n; y++) {
			ans[y]=new String(ary[y]);
		}
		return ans;
	}
}
/*
4
###.
..#.
..#.
..#.
...#
...#
###.
....

13
.#..###..##..
#.#.#..#.#.#.
#.#.###..#...
###.#..#.#.#.
#.#.###..##..
.............
..#...#....#.
.##..#.#..##.
#.#..#.#.#.#.
####.#.#.####
..#..#.#...#.
..#...#....#.
.............
.............
.#....#...#..
.#...#.#..#..
####.#.#.####
.#.#.###..#.#
.##....#..##.
.#....#...#..
.............
..##..###.#.#
.#.#.#..#.###
.#.#..###.#.#
.#.#.#..#.#.#
..##..###..#.
*/
