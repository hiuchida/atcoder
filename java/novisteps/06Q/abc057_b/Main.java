import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] anx = new int[n];
		int[] any = new int[n];
		for (int i=0; i<n; i++) {
			anx[i] = sc.nextInt();
			any[i] = sc.nextInt();
		}
		int[] amx = new int[m];
		int[] amy = new int[m];
		for (int i=0; i<m; i++) {
			amx[i] = sc.nextInt();
			amy[i] = sc.nextInt();
		}
		for (int i=0; i<n; i++) {
			int ans = 0;
			int min = Integer.MAX_VALUE;
			for (int j=0; j<m; j++) {
				int d=calcL1(anx[i], any[i], amx[j], amy[j]);
				if (min>d) {
					min=d;
					ans=j+1;
				}
			}
			System.out.println(ans);
		}
	}
	static int calcL1(int x1, int y1, int x2, int y2) { //x1,y1からx2,y2までのマンハッタン距離
		int dx=x1-x2;
		int dy=y1-y2;
		return Math.abs(dx)+Math.abs(dy);
	}
}
/*
2 2
2 0
0 0
-1 0
1 0

3 4
10 10
-10 -10
3 3
1 2
2 3
3 5
3 5

5 5
-100000000 -100000000
-100000000 100000000
100000000 -100000000
100000000 100000000
0 0
0 0
100000000 100000000
100000000 -100000000
-100000000 100000000
-100000000 -100000000
*/
