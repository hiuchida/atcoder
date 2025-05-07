import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=3;
		final int M=10;
		int[][] ary=new int[N][N];
		long m=1;
		for (int i=0; i<N*N; i++) m*=M;
		for (long x=0; x<m; x++) {
			long xx=x;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					ary[i][j]=(int)(xx%M);
					xx/=M;
				}
			}
			boolean b1=solve1(ary);
			boolean b2=solve2(ary);
			if (b1!=b2) {
				System.out.println(x+" NG");
			}
			if (x%1000==0) System.out.println(x+" "+m);
		}
//		for (int i=0; i<N; i++) {
//		System.out.println(Arrays.toString(ary[i]));
//	}
	}
	static boolean solve2(int[][] ary) {
		for (int a1=0; a1<=100; a1++) {
			int b1=ary[0][0]-a1;
			if (b1<0) break;
			int a2=ary[1][0]-b1;
			int a3=ary[2][0]-b1;
			if (a2<0 || a3<0) continue;
//			System.out.println(a1+" "+a2+" "+a3+" , "+b1);
			int b2=ary[0][1]-a1;
			int b3=ary[0][2]-a1;
			if (b2<0 || b3<0) continue;
//			System.out.println(a1+" "+a2+" "+a3+" , "+b1+" "+b2+" "+b3);
			if (a2+b2!=ary[1][1]) continue;
			if (a3+b2!=ary[2][1]) continue;
			if (a2+b3!=ary[1][2]) continue;
			if (a3+b3!=ary[2][2]) continue;
//			System.out.println("Yes");
//			System.exit(0);
			return true;
		}
//		System.out.println("No");
		return false;
	}
	static boolean solve1(int[][] map) {
		final int N=3;
		int[][] delta = new int[N+1][N];
		for (int i = 0; i < N; i++) {
			delta[0][i] = map[i][1] - map[i][0];
			delta[1][i] = map[i][2] - map[i][1];
			delta[2][i] = map[1][i] - map[0][i];
			delta[3][i] = map[2][i] - map[1][i];
		}
		for (int j = 0; j < N+1; j++) {
			for (int i = 0; i < 2; i++) {
				if (delta[j][i] != delta[j][i + 1]) {
//					pln("No");
					return false;
				}
			}
		}
//		pln("Yes");
		return true;
	}
}
/*
1 0 1
2 1 2
1 0 1

2 2 2
2 1 2
2 2 2

0 8 8
0 8 8
0 8 8

1 8 6
2 9 7
0 7 7
*/
