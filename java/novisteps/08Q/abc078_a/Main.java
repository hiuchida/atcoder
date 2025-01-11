import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String x = sc.next();
		String y = sc.next();
		char cx=x.charAt(0);
		char cy=y.charAt(0);
		if (cx<cy) System.out.println("<");
		else if (cx>cy) System.out.println(">");
		else System.out.println("=");
	}
}
/*
A B

E C

F F
*/
