import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final String[] tbl = { "and", "not", "that", "the", "you" };
		Set<String> set=new HashSet<>();
		for (String s : tbl) set.add(s);
		int n=sc.nextInt();
		for (int i=0; i<n; i++) {
			String s=sc.next();
			if (set.contains(s)) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
10
in that case you should print yes and not no

10
in diesem fall sollten sie no und nicht yes ausgeben
*/
