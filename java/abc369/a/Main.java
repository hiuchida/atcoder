import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ab = Math.abs(a-b);
		if (a == b)
			System.out.println(1);
		else if (ab%2 == 1)
			System.out.println(2);
		else
			System.out.println(3);
	}
}
