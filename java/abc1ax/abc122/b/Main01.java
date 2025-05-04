import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		int n=ary.length;
		int ans=0;
		for (int lt=0; lt<n; lt++) {
			for (int rt=lt; rt<n; rt++) {
				if (check(ary, lt, rt)) {
					ans=Math.max(ans, rt-lt);
				}
			}
		}
		System.out.println(ans);
	}
	static boolean check(char[] ary, int lt, int rt) {
		for (int i=lt; i<rt; i++) {
			if (ary[i]!='A' && ary[i]!='C' && ary[i]!='G' && ary[i]!='T') return false;
		}
		return true;
	}
}
/*
ATCODER

HATAGAYA

SHINJUKU
*/
