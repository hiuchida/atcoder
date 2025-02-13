import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String[] a = { "N", "E", "W", "S", "NE", "NW", "SE", "SW" };
		String[] b = { "S", "W", "E", "N", "SW", "SE", "NW", "NE" };
		for (int i=0; i<a.length; i++) {
			if (s.equals(a[i])) {
				System.out.println(b[i]);
				break;
			}
		}
	}
}
/*
N

SE
*/
