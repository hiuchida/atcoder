import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int[] ans=new int[n];
		int r=1;
		while (true) {
			int max=0;
			for (int j=0; j<n; j++) {
				if (ary[j]>0 && max<ary[j]) max=ary[j];
			}
			if (max==0) break;
			int cnt=0;
			for (int j=0; j<n; j++) {
				if (ary[j]==max) {
					ary[j]=0;
					ans[j]=r;
					cnt++;
				}
			}
			r+=cnt;
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(ans));
		for (int i=0; i<n; i++) {
			System.out.println(ans[i]);
		}
	}
}
/*
4
3 12 9 9

3
3 9 6

4
100 100 100 100

8
87 87 87 88 41 38 41 38
*/
