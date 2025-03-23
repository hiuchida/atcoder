import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		for (int i=0; i<3; i++) {
			if (s.charAt(i)!=s.charAt(i+1)) {
				System.out.println("DIFFERENT");
				System.exit(0);
			}
		}
		System.out.println("SAME");
	}
}
/*
2222

1221

0000
*/
