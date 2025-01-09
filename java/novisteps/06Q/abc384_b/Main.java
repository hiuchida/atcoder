import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int d = sc.nextInt();
			int a = sc.nextInt();
			if (d == 1) {
				if (1600 <= r && r <= 2799) r += a;
			} else {
				if (1200 <= r && r <= 2399) r += a;
			}
		}
		System.out.println(r);
	}
}
/*
4 1255
2 900
1 521
2 600
1 52

2 3031
1 1000
2 -1000

15 2352
2 -889
2 420
2 -275
1 957
1 -411
1 -363
1 151
2 -193
2 289
2 -770
2 109
1 345
2 551
1 -702
1 355
*/
