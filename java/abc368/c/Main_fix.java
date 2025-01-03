import java.util.*;
public class Main {
	static void DEBUG(String x) {
//		System.out.println(x);
	}
	static void DEBUG(long x) {
		DEBUG(""+x);
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		DEBUG(Arrays.toString(ary));
		long ans = 0;
		int tt = 1;
		for (int i=0; i<n; i++) {
			int h=ary[i];
			DEBUG(i + ": " + ans + " " + tt + " " + h + " entry");
			int num=h/5;
			h -= num*5;
			ans += num*3;
			DEBUG(i + ": " + ans + " " + tt + " " + h + " skip");
			while (h>0) {
				switch (tt) {
				case 0:
					h-=3;
					break;
				case 1:
				case 2:
					h--;
					break;
				}
				ans++;
				tt=(tt+1)%3;
				DEBUG(i + ": " + ans + " " + tt + " " + h + " once");
			}
		}
		System.out.println(ans);
	}
}
