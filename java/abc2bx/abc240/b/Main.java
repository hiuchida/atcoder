import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		TreeSet<Integer> set=new TreeSet<>();
		for (int i=0; i<n; i++) {
			set.add(sc.nextInt());
		}
		System.out.println(set.size());
	}
}
/*
6
1 4 1 2 2 1

1
1

11
3 1 4 1 5 9 2 6 5 3 5
*/
