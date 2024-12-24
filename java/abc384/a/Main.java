import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String a = sc.next();
		String b = sc.next();
		String s = sc.next();
		for (char ch : s.toCharArray()) {
			if (ch == a.charAt(0)) {
				System.out.print(ch);
			} else {
				System.out.print(b);
			}
		}
		System.out.println();
	}
}
