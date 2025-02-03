import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n+1];
		int[] b = new int[n+1];
		for (int i=1; i<=n; i++) {
			a[i]=i;
			b[i]=1;
		}
		int ans=0;
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
			int c = sc.nextInt();
			if (c==1) {
				int p = sc.nextInt();
				int h = sc.nextInt();
				int pre=a[p];
				b[pre]--;
				if (b[pre] == 1) ans--;
				a[p]=h;
				b[h]++;
				if (b[h] == 2) ans++;
			} else {
				System.out.println(ans);
			}
		}
	}
}
/*
4 7
2
1 1 2
2
1 3 2
2
1 3 4
2

5 10
2
1 4 3
1 4 5
2
1 3 1
2
1 2 3
1 2 5
1 1 3
2
*/
