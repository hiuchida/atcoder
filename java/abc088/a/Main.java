import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a = sc.nextInt();
		n%=500;
		if (n<=a) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
2018
218

2763
0

37
514
*/
