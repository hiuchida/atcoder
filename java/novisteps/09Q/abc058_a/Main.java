import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int ba=b-a;
		int cb=c-b;
		if (ba==cb) System.out.println("YES");
		else System.out.println("NO");
	}
}
/*
2 4 6

2 5 6

3 2 1
*/
