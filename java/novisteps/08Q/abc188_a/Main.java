import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int x=sc.nextInt();
		int y=sc.nextInt();
		if (x>y) {
			int t=x;
			x=y;
			y=t;
		}
		if (x+3>y) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
3 5

16 2

12 15
*/
