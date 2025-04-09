import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int[] ary=new int[w];
		for (int i=0; i<h; i++) {
			String s = sc.next();
			for (int j=0; j<w; j++) {
				if (s.charAt(j)=='#') ary[j]++;
			}
		}
		for (int j=0; j<w; j++) {
			System.out.print(ary[j]+" ");
		}
		System.out.println();
	}
}
/*
3 4
#..#
.#.#
.#.#

3 7
.......
.......
.......

8 3
.#.
###
.#.
.#.
.##
..#
##.
.##

5 47
.#..#..#####..#...#..#####..#...#...###...#####
.#.#...#.......#.#...#......##..#..#...#..#....
.##....#####....#....#####..#.#.#..#......#####
.#.#...#........#....#......#..##..#...#..#....
.#..#..#####....#....#####..#...#...###...#####
*/
