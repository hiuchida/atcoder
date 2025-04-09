import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 5;
		TreeSet<Integer> set=new TreeSet<>();
		for (int i=0; i<n; i++) {
			set.add(sc.nextInt());
		}
		System.out.println(set.size());
	}
}
/*
31 9 24 31 24

0 0 0 0 0
*/
