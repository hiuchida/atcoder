import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		long m=998244353;
		int[] cnt=new int[10];
		cnt[ary[0]]=1;
		int[] cnt2=new int[10];
		for (int i=1; i<n; i++) {
			Arrays.fill(cnt2, 0);
			for (int j=0; j<10; j++) {
				if (cnt[j]>0) {
					int a=(j+ary[i])%10;
					int b=(j*ary[i])%10;
					cnt2[a]+=cnt[j];
					cnt2[b]+=cnt[j];
					if (cnt2[a]>m) cnt2[a]-=m;
					if (cnt2[b]>m) cnt2[b]-=m;
				}
			}
			int[] tmp=cnt;
			cnt=cnt2;
			cnt2=tmp;
		}
		for (int j=0; j<10; j++) {
			System.out.println(cnt[j]);
		}
	}
}
/*
3
2 7 6

5
0 1 2 3 4
*/
