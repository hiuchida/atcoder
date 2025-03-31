import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		int xx=(x-1)/n;
		char ch=(char)(xx+'A');
		System.out.println(ch);
	}
}
/*
1 3

2 12
*/
