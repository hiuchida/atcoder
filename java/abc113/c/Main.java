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
			int m = sc.nextInt();
			List<Set<Integer>> setlist = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				setlist.add(new TreeSet<>());
			}
			List<Pair> pairlist = new ArrayList<>();
			for (int i = 0; i < m; i++) {
				int p = sc.nextInt();
				int y = sc.nextInt();
				pairlist.add(new Pair(p, y));
				setlist.get(p - 1).add(y);
			}
			List<List<Integer>> idxlist = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				idxlist.add(new ArrayList<>(setlist.get(i)));
			}
			for (Pair p : pairlist) {
				int idx = Collections.binarySearch(idxlist.get(p.p - 1), p.y);
				System.out.printf("%06d%06d", p.p, idx + 1);
				System.out.println();
			}
		}
	}

	public static class Pair {
		int p;
		int y;

		Pair(int p, int y) {
			this.p = p;
			this.y = y;
		}
	}

}
