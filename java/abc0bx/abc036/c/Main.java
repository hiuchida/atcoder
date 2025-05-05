package abc036.c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int n = sc.nextInt();
			int[] ary = new int[n];
			Set<Integer> set = new TreeSet<>();
			for (int i = 0; i < n; i++) {
				int a = sc.nextInt();
				ary[i] = a;
				set.add(a);
			}
			List<Integer> list = new ArrayList<>(set);
			for (int i = 0; i < n; i++) {
				int ans = Collections.binarySearch(list, ary[i]);
				System.out.println(ans);
			}
		}
	}

}
