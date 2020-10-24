import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
//			int a = sc.nextInt();
//			int b = sc.nextInt();
//			int c = sc.nextInt();
//			int d = sc.nextInt();
//			int k = sc.nextInt();
//			int m = sc.nextInt();
//			long l = sc.nextLong();
//			int n = sc.nextInt();
//			String s = sc.nextLine();
//			int x = sc.nextInt();
//			int y = sc.nextInt();
//			int z = sc.nextInt();
			int n = 1;
			int[] ary = new int[n];
			List<String> list = new ArrayList<>();
			Deque<Integer> que = new ArrayDeque<>();
			Set<Integer> set = new HashSet<>();
			Set<Integer> treeset = new TreeSet<>();
			long ans = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					ary[i] = 0;
					list.add("");
					que.offerFirst(i);
					que.offerLast(i);
					set.add(i);
					treeset.add(i);
				}
			}
			Arrays.sort(ary);
			Collections.sort(list);
			System.out.println(ans);
			System.out.println("Yes");
			System.out.println("No");
		}
	}

}
