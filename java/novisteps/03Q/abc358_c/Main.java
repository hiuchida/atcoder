import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		boolean[][] ary=new boolean[n][m];
		for (int i=0; i<n; i++) {
			String s = sc.next();
			for (int j=0; j<m; j++) {
				ary[i][j]=s.charAt(j)=='o';
			}
		}
//		for (int i=0; i<n; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		int ans=Integer.MAX_VALUE;
		for (int i=0; i<1 << n; i++) {
			int cnt=0;
			boolean[] line=new boolean[m];
			for (int j=0; j<n; j++) {
				int mask=1 << j;
				if ((i&mask)>0) {
					cnt++;
					for (int k=0; k<m; k++) {
						line[k]|=ary[j][k];
					}
				}
			}
			boolean flg=true;
			for (int k=0; k<m; k++) {
				if (!line[k]) flg=false;
			}
			if (flg) {
				ans=Math.min(ans, cnt);
			}
		}
		System.out.println(ans);
	}
}
/*
3 5
oooxx
xooox
xxooo

3 2
oo
ox
xo

8 6
xxoxxo
xxoxxx
xoxxxx
xxxoxx
xxoooo
xxxxox
xoxxox
oxoxxo
*/
