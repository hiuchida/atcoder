import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		if (s.charAt(n-1)=='o') System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
4
oooxoox

7
ooooooo
*/
