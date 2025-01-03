import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
//		System.out.println(Arrays.toString(ary));
		long ans = 0;
		long t = 1;
		for (int i=0; i<n; i++) {
//			System.out.println(i + " " + ans + " " + t);
			int h=ary[i];
//			if (t==0) {
//				h--;
//				ans++;
//				t++;
//			}
//			System.out.println(i + " " + ans + " " + t + " " + h);
			while (h>=5000000) {
				h -= 5000000;
				ans += 3000000;
				t += 3000000;
			}
			while (h>=50000) {
				h -= 50000;
				ans += 30000;
				t += 30000;
			}
			while (h>=500) {
				h -= 500;
				ans += 300;
				t += 300;
			}
			while (h>=5) {
				h -= 5;
				ans += 3;
				t += 3;
			}
//			System.out.println(i + " " + ans + " " + t + " " + h);
			int tt=(int)(t%3);
			while (h>0) {
				switch (tt) {
				case 0:
					h-=3;
					break;
				case 1:
					h--;
					break;
				case 2:
					h--;
					break;
				}
//				System.out.println(i + ": " + ans + " " + t + " " + h);
				ans++;
				t++;
				tt=(int)(t%3);
			}
		}
		System.out.println(ans);
	}
}
