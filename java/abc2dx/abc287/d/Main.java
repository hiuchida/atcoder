import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] as=s.toCharArray();
		String t=sc.next();
		int n=t.length();
		char[] at=t.toCharArray();
//		System.out.println(Arrays.toString(as));
//		System.out.println(Arrays.toString(at));
		int[] idx=new int[n];
		int ngcnt=0;
		boolean bng=false;
		for (int i=0; i<n; i++) {
			idx[i]=s.length()-1-(n-1-i);
			if (!check(as[idx[i]], at[i])) {
				bng=true;
				ngcnt++;
			}
		}
//		System.out.println(Arrays.toString(idx));
		if (!bng) System.out.println("Yes");
		else System.out.println("No");
		for (int i=1; i<=n; i++) {
			int j=i-1;
			bng=false;
			if (!check(as[idx[j]], at[j])) {
				bng=true;
			}
			idx[j]=j;
			if (!check(as[idx[j]], at[j])) {
				if (!bng) ngcnt++;
			} else {
				if (bng) ngcnt--;
			}
//			System.out.println(Arrays.toString(idx));
			if (ngcnt==0) System.out.println("Yes");
			else System.out.println("No");
		}
	}
	static boolean check(char c1, char c2) {
		if (c1=='?' || c2=='?') return true;
		return c1==c2;
	}
}
/*
a?c
b?

atcoder
?????

beginner
contest
*/
