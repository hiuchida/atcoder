import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		String s = sc.next();
		int sum = a + b + c;
		if (sum < 10) System.out.println(sum + " " + s);
		else System.out.println("error");
	}
}
/*
1
2 3
test

3
4 5
test2
*/
