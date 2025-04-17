import java.util.*;
public class Main {
	static class Edge {
		final int u;
		final int v;
		final long w;
		Edge(int u, int v, int w) {
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
	static Map<Integer, List<Edge>> mapu = new TreeMap<>();
	static Map<Integer, List<Edge>> mapv = new TreeMap<>();
	static List<Edge> getu(int i) {
		List<Edge> list = mapu.get(i);
		if (list == null) {
			list = new ArrayList<>();
			mapu.put(i, list);
		}
		return list;
	}
	static List<Edge> getv(int i) {
		List<Edge> list = mapv.get(i);
		if (list == null) {
			list = new ArrayList<>();
			mapv.put(i, list);
		}
		return list;
	}
	static void addu(Edge edge) {
		List<Edge> list = getu(edge.u);
		list.add(edge);
	}
	static void addv(Edge edge) {
		List<Edge> list = getv(edge.v);
		list.add(edge);
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
//		System.out.println(mapu);
//		System.out.println(mapv);
		ax = new long[n];
		ab = new boolean[n];
//		ax[0] = 200401298;
		for (int i=0; i<n; i++) {
			if (ab[i]) continue;
			dfs(i);
		}
//		System.out.println(Arrays.toString(ab));
		String s = Arrays.toString(ax);
		s = conv(s);
		System.out.println(s);
	}
	static void dfs(int i) {
		if (ab[i]) return;
		ab[i] = true;
		List<Edge> list;
		list = getu(i);
		for (Edge e : list) {
			if (ab[e.v]) continue;
			ax[e.v] = ax[e.u] + e.w;
//			ab[e.v] = true; 
			dfs(e.v);
		}
		list = getv(i);
		for (Edge e : list) {
			if (ab[e.u]) continue;
			ax[e.u] = ax[e.v] - e.w;
//			ab[e.u] = true; 
			dfs(e.u);
		}
	}
	static String conv(String s) { //abc270_c,abc293_b,abc367_c,abc368_a,abc373_d,abc388_d: List等のtoString()の文字列からカッコとカンマを省く
		return s.replace("[", "").replace("]", "").replaceAll(",", "");
	}
}
