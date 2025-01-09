import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int ab = a+b;
		int ac = a+c;
		int bc = b+c;
		if (a == b && b == c) System.out.println("Yes");
		else if (a == bc || b == ac || c == ab) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
3 8 5

2 2 2

1 2 4
*/
