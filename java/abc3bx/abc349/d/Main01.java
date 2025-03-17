import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long l = sc.nextLong();
		long r = sc.nextLong();
		long lt=0;
		long rt=1 << 60;
		List<PairL> list=dfs(lt, rt, l, r, 1);
		Collections.sort(list);
		System.out.println(list.size());
		for (PairL p : list) {
			System.out.println(p.st+" "+p.ed);
		}
	}
	static List<PairL> dfs(long lt, long rt, long l, long r, int depth) {
		List<PairL> list=new ArrayList<>();
		if (l<=lt && rt<=r) {
			list.add(new PairL(lt, rt));
//			System.out.println(lt+" "+rt+" , "+l+" "+r+" : "+depth+"  add");
			return list;
		}
//		System.out.println(lt+" "+rt+" , "+l+" "+r+" : "+depth);
		long m=(lt+rt)/2;
		if (r<=m) {
			return dfs(lt, m, l, r, depth+1);
		}
		if (m<=l) {
			return dfs(m, rt, l, r, depth+1);
		}
		List<PairL> list1=dfs(lt, m, l, r, depth+1);
		List<PairL> list2=dfs(m, rt, l, r, depth+1);
		list.addAll(list1);
		list.addAll(list2);
		return list;
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
