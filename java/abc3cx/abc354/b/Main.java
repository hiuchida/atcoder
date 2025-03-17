import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Pair[] ary=new Pair[n];
		int sum=0;
		for (int i=0; i<n; i++) {
			String s = sc.next();
			int c = sc.nextInt();
			ary[i]=new Pair(s);
			sum+=c;
		}
		Arrays.sort(ary);
		for (int i=0; i<n; i++) {
			ary[i].idx=i;
		}
		sum%=n;
		System.out.println(ary[sum].s);
	}
	static class Pair implements Comparable<Pair> {
		String s;
		int idx;
		Pair(String s) {
			this.s=s;
			this.idx=idx;
		}
		@Override
		public int compareTo(Main.Pair that) {
			return this.s.compareTo(that.s);
		}
		@Override
		public String toString() {
			return "(" + s + "," + idx + ")";
		}
	}
}
/*
3
takahashi 2
aoki 6
snuke 5

3
takahashi 2813
takahashixx 1086
takahashix 4229
*/
