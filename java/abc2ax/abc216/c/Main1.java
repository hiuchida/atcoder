import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		while (n>0) {
			if (n%2==1) {
				sb.append("A");
				n--;
			} else {
				sb.append("B");
				n/=2;
			}
		}
		sb.reverse();
		System.out.println(sb);
	}
}
/*
5

14
*/
