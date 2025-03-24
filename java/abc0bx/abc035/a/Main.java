import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int h = sc.nextInt();
		if (w*3==h*4) System.out.println("4:3");
		else System.out.println("16:9");
	}
}
/*
4 3

16 9

28 21
*/
