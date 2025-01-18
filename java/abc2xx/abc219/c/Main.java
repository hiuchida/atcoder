import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String x = sc.next();
		char[] ary = new char[26];
		for (int i=0; i<26; i++) {
			ary[x.charAt(i)-'a']=(char)('A'+i);
		}
		int n = sc.nextInt();
		List<Pair> list = new ArrayList<>();
		for (int i=0; i<n; i++) {
			String s = sc.next();
			StringBuilder sb = new StringBuilder();
			for (int j=0; j<s.length(); j++) {
				int ch=s.charAt(j)-'a';
				char k=ary[ch];
				sb.append(k);
			}
			list.add(new Pair(sb.toString(), s));
		}
		Collections.sort(list);
		for (Pair p : list) {
			System.out.println(p.ed);
		}
	}
	static class Pair implements Comparable<Pair> {
		final String st;
		final String ed;
		Pair(String st, String ed) {
			this.st = st;
			this.ed = ed;
		}
		@Override
		public int compareTo(Pair that) {
			int cmp = this.st.compareTo(that.st);
			return cmp;
		}
		@Override
		public int hashCode() {
			return Objects.hash(st, ed);
		}
		@Override
		public String toString() {
			return "(" + st + "," + ed + ")";
		}
	}
}
/*
bacdefghijklmnopqrstuvwxzy
4
abx
bzz
bzy
caa

zyxwvutsrqponmlkjihgfedcba
5
a
ab
abc
ac
b
*/
