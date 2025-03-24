import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder sb=new StringBuilder();
		for (int i=1; i<=(n-1)/2; i++) sb.append("-");
		sb.append("=");
		if (n%2==0) sb.append("=");
		for (int i=1; i<=(n-1)/2; i++) sb.append("-");
		System.out.println(sb);
	}
}
/*
4

7
*/
