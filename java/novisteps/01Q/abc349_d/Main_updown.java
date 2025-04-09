import java.util.*;
public class Main {
	static List<PairL> list=new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long l = sc.nextLong();
		long r = sc.nextLong();
//		if (l%2==1) {
//			list.add(new PairL(l, l+1));
//			l++;
//		}
		while (l<r) {
			PairL p1=search(l, r);
			list.add(p1);
			l=p1.ed;
		}
//		Collections.sort(list);
		System.out.println(list.size());
		for (PairL p : list) {
			System.out.println(p.st+" "+p.ed);
		}
	}
	static PairL search(long l, long r) {
		TreeSet<PairL> setl=new TreeSet<>();
		for (long v=1; v<=r; v*=2) {
			long j=l/v;
			long x=j*v;
			if (x==l) {
				long y=(j+1)*v;
				if (y<=r) {
					setl.add(new PairL(x, y));
				}
			}
		}
//		System.out.println(setl);
		return setl.last();
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
