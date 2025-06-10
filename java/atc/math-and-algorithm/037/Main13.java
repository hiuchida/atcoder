import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int x1=sc.nextInt();
		int y1=sc.nextInt();
		int x2=sc.nextInt();
		int y2=sc.nextInt();
		int x3=sc.nextInt();
		int y3=sc.nextInt();
		int x4=sc.nextInt();
		int y4=sc.nextInt();
		long cross1, cross2, cross3, cross4;
		{
			long ax=x2-x1;
			long ay=y2-y1;
			long bx=x3-x1;
			long by=y3-y1;
			long cx=x4-x1;
			long cy=y4-y1;
			cross1=ax*by-ay*bx;
			cross2=ax*cy-ay*cx;
//			System.out.println(ax+" "+ay);
//			System.out.println(bx+" "+by);
//			System.out.println(cx+" "+cy);
//			System.out.println(cross1+" "+cross2);
		}
		{
			long ax=x4-x3;
			long ay=y4-y3;
			long bx=x1-x3;
			long by=y1-y3;
			long cx=x2-x3;
			long cy=y2-y3;
			cross3=ax*by-ay*bx;
			cross4=ax*cy-ay*cx;
//			System.out.println(ax+" "+ay);
//			System.out.println(bx+" "+by);
//			System.out.println(cx+" "+cy);
//			System.out.println(cross1+" "+cross2);
		}
		if (cross1==0 && cross2==0 && cross3==0 && cross4==0) {
			if (x1>x2) {
				int tmp=x1;
				x1=x2;
				x2=tmp;
				tmp=y1;
				y1=y2;
				y2=tmp;
			}
			if (x3>x4) {
				int tmp=x3;
				x3=x4;
				x4=tmp;
				tmp=y3;
				y3=y4;
				y4=tmp;
			}
			if (Math.max(x1, x3)<=Math.min(x2, x4)) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
			System.exit(0);
		}
		if (cross1>=0 && cross2>=0) {
			System.out.println("No");
			System.exit(0);
		} else if (cross1<=0 && cross2<=0) {
			System.out.println("No");
			System.exit(0);
		}
		if (cross3>=0 && cross4>=0) {
			System.out.println("No");
			System.exit(0);
		} else if (cross3<=0 && cross4<=0) {
			System.out.println("No");
			System.exit(0);
		}
		System.out.println("Yes");
	}
}
/*
1 1
2 2
1 2
2 1

1 2
2 2
1 1
1 3

100000001 200000000
200000000 200000000
100000000 100000000
100000000 300000000

1 1
3 3
2 2
4 4

4 1
3 2
2 3
1 4
*/
