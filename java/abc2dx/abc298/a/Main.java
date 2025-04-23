import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
		boolean bok=false;
		for (int i=0; i<ary.length; i++) {
			if (ary[i]=='o') bok=true;
			else if (ary[i]=='x') {
				System.out.println("No");
				System.exit(0);
			}
		}
		if (bok) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
4
oo--

3
---

1
o

100
ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooox
*/
