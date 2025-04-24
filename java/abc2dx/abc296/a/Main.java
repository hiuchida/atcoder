import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
		for (int i=1; i<n; i++) {
			if (ary[i-1]==ary[i]) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
}
/*
6
MFMFMF

9
FMFMMFMFM

1
F
*/
