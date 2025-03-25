import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(ary));
		int[] cut=new int[n+1];
		int cnt=1;
		for (int i=0; i<n; i++) {
//			System.out.println(cnt+" "+Arrays.toString(cut));
			int a=ary[i];
			for (int j=0; j<cnt; j++) {
				cut[j]+=a;
				cut[j]%=360;
			}
			cnt++;
		}
//		System.out.println(cnt+" "+Arrays.toString(cut));
		Arrays.sort(cut);
//		System.out.println(Arrays.toString(cut));
		int ans=0;
		for (int i=0; i<n; i++) {
			int d=cut[i+1]-cut[i];
			ans=Math.max(ans, d);
		}
		ans=Math.max(ans, 360-cut[n]);
		System.out.println(ans);
	}
}
/*
4
90 180 45 195

1
1

10
215 137 320 339 341 41 44 18 241 149
*/
