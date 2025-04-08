import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Set<String> set=new HashSet<>();
		for (int i=1; i<=n; i++) {
			String s=sc.next();
			check(s);
			if (set.contains(s)) {
				System.out.println("No");
				System.exit(0);
			}
			set.add(s);
		}
		System.out.println("Yes");
	}
	static void check(String s) {
		char ch1=s.charAt(0);
		char ch2=s.charAt(1);
		if (ch1=='H' || ch1=='D' || ch1=='C' || ch1=='S') {
			if (ch2=='A' || ch2=='T' || ch2=='J' || ch2=='Q' || ch2=='K') return;
			if ('2'<=ch2 && ch2<='9') return;
		}
//		System.out.println(s+" "+ch1+" "+ch2);
		System.out.println("No");
		System.exit(0);
	}
}
/*
4
H3
DA
D3
SK

5
H3
DA
CK
H3
S7

4
3H
AD
3D
KS

5
00
AA
XX
YY
ZZ
*/
