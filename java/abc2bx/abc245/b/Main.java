import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		TreeSet<Integer> set=new TreeSet<>();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			set.add(a);
		}
		List<Integer> list=new ArrayList<>(set);
		int i=0;
		for (; i<list.size(); i++) {
			if (list.get(i)!=i) {
				System.out.println(i);
				System.exit(0);
			}
		}
		System.out.println(i);
	}
}
/*
8
0 3 2 6 2 1 0 0

3
2000 2000 2000
*/
