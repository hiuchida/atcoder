import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] tbl={ "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
		String s = sc.next();
		for (int i=0; i<tbl.length; i++) {
			if (s.equals(tbl[i])) {
				System.out.println(5-i);
				System.exit(0);
			}
		}
	}
}
/*
Wednesday

Monday
*/
