package arc026.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			long n = sc.nextLong();
			List<Long> list = divisor(n);
			long ans = 0;
			list.remove(list.size() - 1);
			for (long v : list) {
				ans += v;
			}
			if (n == ans)
				System.out.println("Perfect");
			else if (n > ans)
				System.out.println("Deficient");
			else
				System.out.println("Abundant");
		}
	}

	public static List<Long> divisor(long x) {
		List<Long> list = new ArrayList<>();
		for (long i = 1; i * i <= x; i++) {
			if (x % i == 0) {
				list.add(i);
				if (i * i != x)
					list.add(x / i);
			}
		}
		Collections.sort(list);
		return list;
	}

}
