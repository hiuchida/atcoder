import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		int b1=-1;
		int b2=-1;
		int r1=-1;
		int r2=-1;
		int k1=-1;
		for (int i=0; i<ary.length; i++) {
			if (ary[i]=='B') {
				if (b1<0) b1=i;
				else b2=i;
			}
			if (ary[i]=='R') {
				if (r1<0) r1=i;
				else r2=i;
			}
			if (ary[i]=='K') k1=i;
		}
//		System.out.println(b1+" "+b2);
		if (b1%2==b2%2) {
			System.out.println("No");
			System.exit(0);
		}
//		System.out.println(r1+" "+k1+" "+r2);
		if (r1<k1 && k1<r2) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
RNBQKBNR

KRRBBNNQ

BRKRBQNN
*/
