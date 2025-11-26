import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int mx=0;
		int my=0;
		int mt=0;
		for (int i=0; i<n; i++) {
			int t=sc.nextInt();
			int x=sc.nextInt();
			int y=sc.nextInt();
			int d=calcL1(mx, my, x, y);
			int dt=t-mt;
			if (d>dt) {
				System.out.println("No");
				System.exit(0);
			}
			dt-=d;
			if (dt%2==1) {
				System.out.println("No");
				System.exit(0);
			}
			mx=x;
			my=y;
			mt=t;
		}
		System.out.println("Yes");
	}
	static int calcL1(int x1, int y1, int x2, int y2) { //x1,y1からx2,y2までのマンハッタン距離
		int dx=x1-x2;
		int dy=y1-y2;
		return Math.abs(dx)+Math.abs(dy);
	}
}
/*
2
3 1 2
6 1 1

1
2 100 100

2
5 1 1
100 1 1
*/
