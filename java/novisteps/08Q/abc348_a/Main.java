import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder sb=new StringBuilder();
		for (int i=1; i<=n; i++) {
			if (i%3==0) sb.append("x");
			else sb.append("o");
		}
		System.out.println(sb);
	}
}
/*
7

9
*/
