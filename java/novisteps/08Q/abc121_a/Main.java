import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ans=(h-a)*(w-b);
		System.out.println(ans);
	}
}
/*
3 2
2 1

5 5
2 3

2 4
2 4
*/
