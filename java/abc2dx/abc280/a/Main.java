import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h=sc.nextInt();
		int w=sc.nextInt();
		long ans=0;
		for (int y=0; y<h; y++) {
			String s=sc.next();
			for (int x=0; x<w; x++) {
				if (s.charAt(x)=='#') ans++;
			}
		}
		System.out.println(ans);
	}
}
/*
3 5
#....
.....
.##..

1 10
..........

6 5
#.#.#
....#
..##.
####.
..#..
#####
*/
