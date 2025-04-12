import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ax=new int[n];
		int[] ay=new int[n];
		for (int i=0; i<n; i++) {
			ax[i]=sc.nextInt();
			ay[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(ax));
//		System.out.println(Arrays.toString(ay));
		long ans=0;
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				int dx=Math.abs(ax[i]-ax[j]);
				int dy=Math.abs(ay[i]-ay[j]);
				if (dx>=dy) ans++;
			}
		}
		System.out.println(ans);
	}
}
/*
3
0 0
1 2
2 1

1
-691 273

10
-31 -35
8 -36
22 64
5 73
-14 8
18 -58
-41 -85
1 -88
-21 -85
-11 82
*/
