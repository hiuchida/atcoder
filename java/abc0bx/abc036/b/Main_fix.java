import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String[] ary=new String[n];
		for (int y=0; y<n; y++) {
			ary[y]=sc.next();
		}
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				int x2=y;
				int y2=n-1-x;
				char ch=ary[y2].charAt(x2);
				System.out.print(ch);
			}
			System.out.println();
		}
	}
}
/*
4
ooxx
xoox
xxxx
xxxx
*/
