import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		List<Integer> temp = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			ary[i] = x;
			temp.add(x);
		}
		Collections.sort(temp);
		int min = temp.get(n / 2 - 1);
		int max = temp.get(n / 2);
		for (int i = 0; i < n; i++) {
			if (ary[i] <= min) {
				System.out.println(max);
			} else {
				System.out.println(min);
			}
		}
	}
}
