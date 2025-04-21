import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
		for (int i=0; i<n; i++) {
			if (k>0 && ary[i]=='o') {
				System.out.print('o');
				k--;
			} else {
				System.out.print('x');
			}
		}
		System.out.println();
	}
}
/*
10 3
oxxoxooxox
*/
