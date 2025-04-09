import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int n=3;
		final String tbl="ABC";
		String[] ary=new String[n];
		for (int i=0; i<n; i++) {
			String s=sc.next();
			ary[i]=s;
		}
		int[] pos=new int[n];
		int x=0;
		while (true) {
			int p=pos[x];
			if (p==ary[x].length()) {
				System.out.println(tbl.charAt(x));
				System.exit(0);
			}
			char ch=ary[x].charAt(p);
			pos[x]++;
			x=ch-'a';
		}
	}
}
/*
aca
accc
ca

abcb
aacb
bccc
*/
