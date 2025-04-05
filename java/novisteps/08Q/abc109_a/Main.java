import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ab=a*b;
		if (ab%2==1) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
3 1

1 2

2 2
*/
