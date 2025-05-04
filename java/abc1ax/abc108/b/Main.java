import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int x1=sc.nextInt();
		int y1=sc.nextInt();
		int x2=sc.nextInt();
		int y2=sc.nextInt();
		int dx=x2-x1;
		int dy=y2-y1;
		int x3=x2-dy;
		int y3=y2+dx;
		int x4=x3-dx;
		int y4=y3-dy;
		System.out.println(x3+" "+y3+" "+x4+" "+y4);
	}
}
/*
0 0 0 1

2 3 6 6

31 -41 -59 26
*/
