import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();
		if ("H".equals(a)) System.out.println(b);
		else {
			if ("H".equals(b)) System.out.println("D");
			else System.out.println("H");
		}
	}
}
/*
H H

D H

D D
*/
