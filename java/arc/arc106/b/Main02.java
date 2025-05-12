import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	static Node[] nodes;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			long[] aa = new long[n + 1];
			for (int i = 1; i <= n; i++) {
				aa[i] = sc.nextInt();
			}
			long[] bb = new long[n + 1];
			for (int i = 1; i <= n; i++) {
				bb[i] = sc.nextInt();
			}
			nodes = new Node[n + 1];
			for (int i = 1; i <= n; i++) {
				nodes[i] = new Node(i);
			}
			for (int i = 1; i <= m; i++) {
				int c = sc.nextInt();
				int d = sc.nextInt();
				nodes[c].add(d);
				nodes[c].dif = bb[c] - aa[c];
				nodes[d].add(c);
				nodes[d].dif = bb[d] - aa[d];
			}
			Set<Integer> set = new HashSet<>();
			for (int i = 1; i <= n; i++) {
				long diff = bb[i] - aa[i];
//				System.out.println(i + ", " + nodes[i].to.size());
				if (nodes[i].to.size() == 0) {
					if (diff != 0) {
						System.out.println("No");
						return;
					}
				} else if (nodes[i].to.size() == 1) {
					int you = nodes[i].to.first();
					if (diff != 0) {
						aa[i] += diff;
						aa[you] -= diff;
					}
					nodes[i].to.clear();
					nodes[you].to.remove(i);
					cut(you, aa, bb);
				} else {
					set.add(i);
				}
			}
//			System.out.println(set);
			while (set.size() > 0) {
				Set<Integer> set2 = new HashSet<>();
				for (int i : set) {
					long diff = bb[i] - aa[i];
					if (nodes[i].to.size() == 0) {
						if (diff != 0) {
							System.out.println("No");
							return;
						}
					} else if (nodes[i].to.size() == 1) {
						int you = nodes[i].to.first();
						if (diff != 0) {
							aa[i] += diff;
							aa[you] -= diff;
						}
						nodes[i].to.clear();
						nodes[you].to.remove(i);
					} else {
						set2.add(i);
					}
				}
				set = set2;
			}
			System.out.println("Yes");
		}
	}

	static void cut(int i, long[] aa, long[] bb) {
		long diff = bb[i] - aa[i];
		if (nodes[i].to.size() == 0) {
		} else if (nodes[i].to.size() == 1) {
			int you = nodes[i].to.first();
			if (diff != 0) {
				aa[i] += diff;
				aa[you] -= diff;
			}
			nodes[i].to.clear();
			nodes[you].to.remove(i);
			cut(you, aa, bb);
		} else {
		}
	}

	static class Node {
		int from;
		TreeSet<Integer> to = new TreeSet<>();
		long dif;

		Node(int a) {
			from = a;
		}

		void add(int b) {
			to.add(b);
		}

	}

}
