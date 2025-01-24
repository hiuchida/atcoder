import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int[][] ary = new int[h][w];
		for (int y=0; y<h; y++) {
			for (int x=0; x<w; x++) {
				ary[y][x] = sc.nextInt();
			}
		}
		for (int y1=0; y1<h; y1++) {
			for (int y2=y1+1; y2<h; y2++) {
				for (int x1=0; x1<w; x1++) {
					for (int x2=x1+1; x2<w; x2++) {
						if (ary[y1][x1]+ary[y2][x2]<=ary[y2][x1]+ary[y1][x2]) {
							;
						} else {
							System.out.println("No");
							System.exit(0);
						}
					}
				}
			}
		}
		System.out.println("Yes");
	}
}
/*
3 3
2 1 4
3 1 3
6 4 1

2 4
4 3 2 1
5 6 7 8
*/
