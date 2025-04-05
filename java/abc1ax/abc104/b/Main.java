import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		if (ary[0]!='A') {
			System.out.println("WA");
			System.exit(0);
		}
		int cnt=0;
		for (int i=3; i<=s.length()-1; i++) {
			if (ary[i-1]=='C') cnt++;
			else if ('A'<=ary[i-1] && ary[i-1]<='Z') {
				System.out.println("WA");
				System.exit(0);
			}
		}
		if (cnt==0 || cnt>1) {
			System.out.println("WA");
			System.exit(0);
		}
		if ('A'<=ary[1] && ary[1]<='Z') {
			System.out.println("WA");
			System.exit(0);
		}
		if ('A'<=ary[s.length()-1] && ary[s.length()-1]<='Z') {
			System.out.println("WA");
			System.exit(0);
		}
		System.out.println("AC");
	}
}
/*
AtCoder

ACoder

AcycliC

AtCoCo

Atcoder
*/
