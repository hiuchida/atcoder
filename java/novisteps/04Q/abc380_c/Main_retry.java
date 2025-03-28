import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		String s = sc.next();
		int p0=0;
		int p1=0;
		int p2=0;
		int c=0;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)=='1') {
				int j=i+1;
				for (; j<s.length(); j++) {
					if (s.charAt(j)!='1') break;
				}
				j--;
				c++;
				if (c==k-1) {
					p0=j;
				}
				else if (c==k) {
					p1=i;
					p2=j;
				}
				i=j+1;
			}
		}
//		System.out.println(p0+" "+p1);
		for (int i=0; i<=p0; i++) {
			System.out.print(s.charAt(i));
		}
		for (int i=p1; i<=p2; i++) {
			System.out.print(s.charAt(i));
		}
		for (int i=p0+1; i<p1; i++) {
			System.out.print(s.charAt(i));
		}
		for (int i=p2+1; i<s.length(); i++) {
			System.out.print(s.charAt(i));
		}
		System.out.println();
	}
}
/*
15 3
010011100011001

10 2
1011111111
*/
