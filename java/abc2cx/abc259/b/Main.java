import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int d = sc.nextInt();
		double r=(double)d/360*2*Math.PI;
		double x=a*Math.cos(r)-b*Math.sin(r);
		double y=a*Math.sin(r)+b*Math.cos(r);
		System.out.println(x+" "+y);
	}
}
/*
2 2 180

5 0 120

0 0 11

15 5 360

-505 191 278
*/
