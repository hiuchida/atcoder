import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		long ans = 1;
		for ( ; b<a; b++) ans*=32;
		System.out.println(ans);
	}
}
/*
6 4

5 5
*/
