import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] ary=new String[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.next();
		}
		int ans=Integer.MAX_VALUE;
		for (int i=0; i<=9; i++) {
			int x=calc(ary, i);
			ans=Math.min(ans, x);
		}
		System.out.println(ans);
	}
	static int calc(String[] ary, int i) {
		char ch=(char)(i+'0');
		TreeSet<Integer> set=new TreeSet<>();
		for (String s : ary) {
			int idx=s.indexOf(ch);
			while (set.contains(idx)) {
				idx+=10;
			}
			set.add(idx);
		}
		return set.last();
	}
}
/*
3
1937458062
8124690357
2385760149

5
0123456789
0123456789
0123456789
0123456789
0123456789
*/
