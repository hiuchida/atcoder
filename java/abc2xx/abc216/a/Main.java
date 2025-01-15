import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		int x = sc.nextInt();
//		int y = sc.nextInt();
		String s = sc.next();
		String[] cols=s.split("\\.");
		int x=Integer.parseInt(cols[0]);
		int y=Integer.parseInt(cols[1]);
		if (y<=2) System.out.println(x + "-");
		else if (y<=6) System.out.println(x);
		else System.out.println(x+"+");
	}
}
/*
15.8

1.0

12.5
*/
