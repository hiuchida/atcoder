import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		TreeMap<Integer, Integer> map = new TreeMap<>();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			Integer x = map.get(a);
			if (x != null) list.add(""+x);
			else list.add("-1");
			map.put(a, i+1);
		}
		System.out.println(String.join(" ", list));
	}
}
/*
5
1 2 1 1 3

4
1 1000000000 1000000000 1
*/
