import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
		int p1=-1;
		int p2=-1;
		int a1=-1;
		for (int i=0; i<ary.length; i++) {
			if (ary[i]=='|') {
				if (p1<0) p1=i;
				else p2=i;
			}
			if (ary[i]=='*') a1=i;
		}
		if (p1<a1 && a1<p2) System.out.println("in");
		else System.out.println("out");
	}
}
/*
10
.|..*...|.

10
.|..|.*...

3
|*|
*/
