import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ab=a*b;
		if (ab%2==0) System.out.println("Even");
		else System.out.println("Odd");
	}
}
/*
3 4

1 21
*/
