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
			long dx=xx-x;
			long dy=yy-y;
			ans += Math.sqrt(dx*dx+dy*dy);
			x = xx;
			y = yy;
		}
		long xx = 0;
		long yy = 0;
		long dx=xx-x;
		long dy=yy-y;
		ans += Math.sqrt(dx*dx+dy*dy);
		System.out.println(ans);
	}
}
/*
6.06449510224597979401
6.06449510224598

384694.57587932075868509383
384694.57587932074

1414213.56237309504880168872
1414213.5623730952
*/
