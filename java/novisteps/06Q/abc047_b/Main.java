import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int h = sc.nextInt();
		int n = sc.nextInt();
		int lt=0;
		int rt=w;
		int bm=0;
		int tp=h;
		for (int i=0; i<n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int a = sc.nextInt();
			switch (a) {
			case 1:
				lt=Math.max(lt, x);
				break;
			case 2:
				rt=Math.min(rt, x);
				break;
			case 3:
				bm=Math.max(bm, y);
				break;
			case 4:
				tp=Math.min(tp, y);
				break;
			}
		}
		if (lt>rt || bm>tp) System.out.println(0);
		else {
			int ans=(rt-lt)*(tp-bm);
			System.out.println(ans);
		}
	}
}
/*
5 4 2
2 1 1
3 3 4

5 4 3
2 1 1
3 3 4
1 4 2

10 10 5
1 6 1
4 1 3
6 9 4
9 4 2
3 1 3
*/
