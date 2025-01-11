import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ab = a+b;
		int ans=ab/2+ab%2;
		System.out.println(ans);
	}
}
/*
1 3

7 4

5 5
*/
