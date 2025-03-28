import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] cnt=new int[8];
		int high=0;
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			int b=a/400;
			if (b<=7) cnt[b]++;
			else high++;
		}
//		System.out.println(Arrays.toString(cnt));
//		System.out.println(high);
		int min=0;
		for (int i=0; i<cnt.length; i++) {
			if (cnt[i]>0) min++;
		}
		int max=min+high;
		if (min==0 && high>0) min=1;
		System.out.println(min+" "+max);
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
