import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		String t=sc.next();
		char[] as=s.toCharArray();
		char[] at=t.toCharArray();
		int n=as.length;
		for (int i=0; i<n; i++) {
//			System.out.println(i);
			boolean bok=true;
			for (int j=0; j<n; j++) {
				if (as[(n-i+j)%n]!=at[j]) bok=false;
			}
			if (bok) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
kyoto
tokyo

abc
arc

aaaaaaaaaaaaaaab
aaaaaaaaaaaaaaab
*/
