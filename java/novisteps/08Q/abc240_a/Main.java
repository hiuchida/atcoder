import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		if (a==1 && b==10) System.out.println("Yes");
		else if (a+1==b) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
4 5

3 5

1 10
*/
