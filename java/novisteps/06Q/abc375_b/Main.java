import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		double ans = 0;
		long x = 0;
		long y = 0;
		for (int i=0; i<n; i++) {
			long xx = sc.nextInt();
			long yy = sc.nextInt();
			ans += calc(x, y, xx, yy);
			x = xx;
			y = yy;
		}
		ans += calc(x, y, 0, 0);
		System.out.println(ans);
	}
	static double calc(long x1, long y1, long x2, long y2) { //abc375_b: x1,y1からx2,y2までのユークリッド距離
		long dx=x1-x2;
		long dy=y1-y2;
		return Math.sqrt(dx*dx+dy*dy);
	}
}
/*
2
1 2
-1 0

7
-14142 13562
-17320 50807
-22360 67977
24494 89742
-26457 51311
28284 27124
31622 77660

5
-100000 100000
100000 -100000
-100000 100000
100000 -100000
-100000 100000
*/
/*
6.06449510224597979401
6.06449510224598

384694.57587932075868509383
384694.57587932074

1414213.56237309504880168872
1414213.5623730952
*/
