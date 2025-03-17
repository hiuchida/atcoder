import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		if (a==b) System.out.println(-1);
		else if (a!=1 && b!=1) System.out.println(1);
		else if (a!=2 && b!=2) System.out.println(2);
		else if (a!=3 && b!=3) System.out.println(3);
	}
}
/*
1 2

1 1

3 1
*/
