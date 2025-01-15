import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TreeSet<String> set = new TreeSet<>();
		set.add(sc.next());
		set.add(sc.next());
		set.add(sc.next());
		String[] tbl= {"ABC","ARC","AGC","AHC"};
		for (String s : tbl) {
			if (!set.contains(s)) {
				System.out.println(s);
				System.exit(0);
			}
		}
	}
}
/*
ARC
AGC
AHC

AGC
ABC
ARC
*/
