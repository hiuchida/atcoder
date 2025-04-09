import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Label[] ary=new Label[n];
		for (int i=0; i<n; i++) {
			String s=sc.next();
			int p=sc.nextInt();
			ary[i]=new Label(s, p, i+1);
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		for (int i=0; i<n; i++) {
			System.out.println(ary[i].idx);
		}
		System.out.println();
	}
	static class Label implements Comparable<Label> { //Label20250409
		final String s;
		final int v;
		final int idx;
		Label(String s, int v, int idx) {
			this.s = s;
			this.v = v;
			this.idx = idx;
		}
		@Override
		public int compareTo(Label that) {
			int cmp = 0;
			cmp = this.s.compareTo(that.s);
			if (cmp != 0) return cmp;
			cmp = Integer.compare(this.v, that.v);
			return -cmp;
//			return cmp;
		}
		@Override
		public int hashCode() {
			return Objects.hash(s, v);
		}
		@Override
		public boolean equals(Object obj) {
			Label that = (Label) obj;
			return this.s == that.s && this.v == that.v;
		}
		@Override
		public String toString() {
			return "(" + s + "," + v + "," + idx + ")";
		}
	}
}
/*
6
khabarovsk 20
moscow 10
kazan 50
kazan 35
moscow 60
khabarovsk 40

10
yakutsk 10
yakutsk 20
yakutsk 30
yakutsk 40
yakutsk 50
yakutsk 60
yakutsk 70
yakutsk 80
yakutsk 90
yakutsk 100
*/
