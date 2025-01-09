import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		Set<Integer> set = new HashSet<Integer>();
		set.add(a);
		set.add(b);
		set.add(c);
		System.out.println(set.size());
	}
}
/*
3 1 4

3 3 33
*/
