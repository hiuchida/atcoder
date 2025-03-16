import java.util.*;
public class Main {
	static class Edge {
		final int u;
		final int v;
		final long w;
		Edge(int u, int v, long w) {
			this.u = u-1;
			this.v = v-1;
			this.w = w;
		}
		@Override
		public String toString() {
			return "(" + u + ", " + v + ", " + w + ")";
		}
	}
	static Edge[] edge;
	static Map<Integer, List<Edge>> map = new TreeMap<>();
	static List<Edge> get(int i) {
		List<Edge> list = map.get(i);
		if (list == null) {
			list = new ArrayList<>();
			map.put(i, list);
		}
		return list;
	}
	static void addu(Edge edge) {
		List<Edge> list = get(edge.u);
		list.add(edge);
	}
	static void addv(Edge edge) {
		Edge edge2 = new Edge(edge.v+1, edge.u+1, -edge.w);
		addu(edge2);
	}
	static long[] ax;
	static boolean[] ab;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		edge = new Edge[m];
		for (int i=0; i<m; i++) {
			edge[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
			addu(edge[i]);
			addv(edge[i]);
		}
//		System.out.println(Arrays.toString(edge));
//		System.out.println(map);
		ax = new long[n];
		ab = new boolean[n];
//		ax[0] = 200401298;
		for (int i=0; i<n; i++) {
			if (ab[i]) continue;
			dfs(i);
		}
//		System.out.println(Arrays.toString(ab));
		String s = Arrays.toString(ax);
		s = s.replace("[", "").replace("]", "").replaceAll(",", "");
		System.out.println(s);
	}
	static void dfs(int i) {
		if (ab[i]) return;
		ab[i] = true;
		List<Edge> list;
		list = get(i);
		for (Edge e : list) {
			if (ab[e.v]) continue;
			ax[e.v] = ax[e.u] + e.w;
//			ab[e.v] = true; 
			dfs(e.v);
		}
	}
}
