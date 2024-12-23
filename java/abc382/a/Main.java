import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int d = sc.nextInt();
		String s = sc.next();
		int c = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '@') {
				c++;
			}
		}
		System.out.println(n - c + d);
	}
}
