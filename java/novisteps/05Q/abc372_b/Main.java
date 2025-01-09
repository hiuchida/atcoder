import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		List<Integer> list = new ArrayList<>();
		while (m > 0) {
			for (int i=10; i>=0; i--) {
				int x = (int)Math.pow(3, i);
				if (x <= m) {
					list.add(i);
					m -= x;
//					System.out.println(list + " " + m);
					break;
				}
			}
		}
		List<String> ls = new ArrayList<>();
		for (int i : list) {
			ls.add(""+i);
		}
		System.out.println(ls.size());
		System.out.println(String.join(" ", ls));
	}
}
/*
6

100

59048
*/
