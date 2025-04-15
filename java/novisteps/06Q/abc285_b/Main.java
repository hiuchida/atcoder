import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
		for (int i=1; i<n; i++) {
			int j=0;
			for (j=0; i+j<n; j++) {
				if (ary[j]==ary[i+j]) break;
			}
			System.out.println(j);
		}
	}
}
/*
6
abcbac
*/
