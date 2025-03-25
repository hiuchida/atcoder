import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		String[] ary=new String[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.next();
		}
		TreeSet<String> set=new TreeSet<>();
		for (int i=0; i<m; i++) {
			set.add(sc.next());
		}
		for (int i=0; i<n; i++) {
			if (set.contains(ary[i])) System.out.println("Yes");
			else System.out.println("No");
		}
	}
}
/*
5 3
tokyo kanda akiba okachi ueno
tokyo akiba ueno

7 7
a t c o d e r
a t c o d e r
*/
