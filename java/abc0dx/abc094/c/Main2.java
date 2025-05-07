import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			list.add(x);
		}
		List<Integer> temp = new ArrayList<>(list);
		Collections.sort(temp);
		Integer min = temp.get(n / 2 - 1);
		Integer max = temp.get(n / 2);
		for (int i = 0; i < n; i++) {
			if (list.get(i) <= min) {
				System.out.println(max);
			} else if (list.get(i) >= max) {
				System.out.println(min);
			}
		}
	}
}
