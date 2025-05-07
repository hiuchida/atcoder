import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=3;
		int[][] ary=new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				ary[i][j]=sc.nextInt();
			}
		}
//		for (int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		int a1=0;
		int b1=ary[0][0]-a1;
		int a2=ary[1][0]-b1;
		int a3=ary[2][0]-b1;
		int b2=ary[0][1]-a1;
		int b3=ary[0][2]-a1;
//		System.out.println(a1+" "+a2+" "+a3+" , "+b1+" "+b2+" "+b3);
		if (a2+b2!=ary[1][1] || a3+b2!=ary[2][1] || a2+b3!=ary[1][2] || a3+b3!=ary[2][2]) System.out.println("No");
		else System.out.println("Yes");
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
