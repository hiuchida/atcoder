import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[8];
		int over=0;
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			a/=400;
			if (a<8) ary[a]++;
			else over++;
		}
		int ans=0;
		for (int i=0; i<ary.length; i++) {
			if (ary[i]>0) ans++;
		}
		System.out.println(ans + " "+ (ans+over));
	}
}
/*
4
2100 2500 2700 2700

5
1100 1900 2800 3200 3200

20
800 810 820 830 840 850 860 870 880 890 900 910 920 930 940 950 960 970 980 990
*/
