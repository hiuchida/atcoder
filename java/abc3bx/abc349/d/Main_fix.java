import java.util.*;
public class Main {
	static List<PairL> list=new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long l = sc.nextLong();
		long r = sc.nextLong();
		long lt=0;
		long rt=1L << 60;
		dfs(lt, rt, l, r, 1);
		Collections.sort(list);
		System.out.println(list.size());
		for (PairL p : list) {
			System.out.println(p.st+" "+p.ed);
		}
	}
	static void dfs(long lt, long rt, long l, long r, int depth) {
		if (l<=lt && rt<=r) {
			list.add(new PairL(lt, rt));
//			System.out.println(lt+" "+rt+" , "+l+" "+r+" : "+depth+"  add");
			return;
		}
//		System.out.println(lt+" "+rt+" , "+l+" "+r+" : "+depth);
		long m=(lt+rt)/2;
		if (r<=m) {
			dfs(lt, m, l, r, depth+1);
			return;
		}
		if (m<=l) {
			dfs(m, rt, l, r, depth+1);
			return;
		}
		dfs(lt, m, l, r, depth+1);
		dfs(m, rt, l, r, depth+1);
	}
	static class PairL implements Comparable<PairL> {
		final long st;
		final long ed;
		PairL(long st, long ed) {
			this.st = st;
			this.ed = ed;
		}
		static Comparator<PairL> newComparator1() {
			return new Comparator<PairL>() {
				@Override
				public int compare(PairL o1, PairL o2) {
					int cmp = Long.compare(o1.st, o2.st);
					return cmp;
				}
			};
		}
		static Comparator<PairL> newComparator2() {
			return new Comparator<PairL>() {
				@Override
				public int compare(PairL o1, PairL o2) {
					int cmp = Long.compare(o1.ed, o2.ed);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(PairL that) {
			int cmp = Long.compare(this.st, that.st);
			if (cmp != 0) return cmp;
			return Long.compare(this.ed, that.ed);
		}
		@Override
		public int hashCode() {
			return Objects.hash(st, ed);
		}
		@Override
		public boolean equals(Object obj) {
			PairL that = (PairL) obj;
			return this.st == that.st && this.ed == that.ed;
		}
		@Override
		public String toString() {
			return "(" + st + "," + ed + ")";
		}
	}
}
/*
3 19

0 1024

3940649673945088 11549545024454656
*/
