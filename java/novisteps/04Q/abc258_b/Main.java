import java.util.*;
public class Main {
	static final int[] DY = { -1, 1, 0, 0, -1,-1, 1, 1, }; //U,D,L,R, UL,UR,DL,DR
	static final int[] DX = {  0, 0,-1, 1, -1, 1,-1, 1, }; //U,D,L,R, UL,UR,DL,DR
	
	static int n;
	static int[][] ary;
	static String ans="";
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		String[] as=new String[n];
		for (int i=0; i<n; i++) {
			as[i]=sc.next();
		}
		ary=new int[n][n];
		int max=0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				int x=as[i].charAt(j)-'0';
				ary[i][j]=x;
				max=Math.max(max, x);
			}
		}
//		for (int i=0; i<n; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		for (int i=0; i<n; i++) {
			ans+="0";
		}
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (ary[i][j]==max) {
//					System.out.println(i+" "+j+" "+max);
					search(i, j);
				}
			}
		}
		System.out.println(ans);
	}
	static void search(int i, int j) {
		char[] ca=new char[n];
		ca[0]=(char)(ary[i][j]+'0');
		for (int d=0; d<DY.length; d++) {
			int ii=i;
			int jj=j;
			for (int h=1; h<n; h++) {
				ii+=DY[d]+n;
				jj+=DX[d]+n;
				ii%=n;
				jj%=n;
				ca[h]=(char)(ary[ii][jj]+'0');
			}
			String s=new String(ca);
			if (ans.compareTo(s)<0) ans=s;
//			System.out.println(s+" "+ans);
		}
	}
}
/*
4
1161
1119
7111
1811

10
1111111111
1111111111
1111111111
1111111111
1111111111
1111111111
1111111111
1111111111
1111111111
1111111111
*/
