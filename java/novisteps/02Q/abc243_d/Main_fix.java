import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long x = sc.nextLong();
		String s = sc.next();
		char[] ary=new char[s.length()];
		int sp=0;
		for (int i=0; i<s.length(); i++) {
			char ch=s.charAt(i);
			if (ch!='U') {
				ary[sp++]=ch;
			} else if (sp>0 && ary[sp-1]!='U'){
				ary[--sp]=0;
			} else {
				ary[sp++]=ch;
			}
		}
//		System.out.println(Arrays.toString(ary)+" "+sp);
		for (int i=0; i<sp; i++) {
			switch (ary[i]) {
			case 'U':
				x/=2;
				break;
			case 'L':
				x=2*x;
				break;
			case 'R':
				x=2*x+1;
				break;
			}
		}
		System.out.println(x);
	}
}
/*
3 2
URL

4 500000000000000000
RRUU

30 123456789
LRULURLURLULULRURRLRULRRRUURRU
*/
/*
3 2
RUL
*/
