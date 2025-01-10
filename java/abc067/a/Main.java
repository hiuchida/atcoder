import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ab=a+b;
		if (a%3==0) System.out.println("Possible");
		else if (b%3==0) System.out.println("Possible");
		else if (ab%3==0) System.out.println("Possible");
		else System.out.println("Impossible");
	}
}
/*
4 5

1 1
*/
