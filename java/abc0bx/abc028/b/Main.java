import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=6;
		int[] ary=new int[N];
		String s=sc.next();
		for (int i=0; i<s.length(); i++) {
			char ch=s.charAt(i);
			ary[ch-'A']++;
		}
		for (int i=0; i<N; i++) {
			if (i>0) System.out.print(" ");
			System.out.print(ary[i]);
		}
		System.out.println();
	}
}
/*
BEAF

DECADE

ABBCCCDDDDEEEEEFFFFFF
*/
