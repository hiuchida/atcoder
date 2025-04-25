import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
		String ans="";
		int t=0;
		int a=0;
		for (int i=0; i<ary.length; i++) {
			if (ary[i]=='T') {
				t++;
				if (t>a) ans="T";
			} else {
				a++;
				if (a>t) ans="A";
			}
		}
		System.out.println(ans);
	}
}
/*
5
TTAAT

6
ATTATA

1
A
*/
