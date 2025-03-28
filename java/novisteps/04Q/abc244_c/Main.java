import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		TreeSet<Integer> set=new TreeSet<>();
		for (int i=1; i<=2*n+1; i++) {
			set.add(i);
		}
		while (true) {
			int x=set.first();
			set.remove(x);
			System.out.println(x);
			System.out.flush();
			x = sc.nextInt();
			if (x==0) break;
			set.remove(x);
		}
	}
}
/*

*/
