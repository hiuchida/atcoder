import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		int ans=0;
		int[] tbl=new int[4];
		for (int i=0; i<n; i++) {
			tbl[0]=1;
			for (int j=3; j>=0; j--) {
				if (tbl[j]>0) {
					tbl[j]=0;
					int nxt=j+ary[i];
					if (nxt>3) ans++;
					else tbl[nxt]=1;
				}
			}
//			System.out.println(Arrays.toString(tbl));
		}
		System.out.println(ans);
	}
}
/*
4
1 1 3 2

3
1 1 1

10
2 2 4 1 1 1 4 2 2 1
*/
