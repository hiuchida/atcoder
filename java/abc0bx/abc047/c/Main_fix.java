import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int n=s.length();
		char[] ary=s.toCharArray();
		if (n==1) {
			System.out.println(0);
			System.exit(0);
		}
		int cb=0;
		int cw=0;
		for (int i=1; i<ary.length; i++) {
			if (ary[i-1]!=ary[i]) {
				if (ary[i-1]=='B') cb++;
				else cw++;
			}
		}
//		System.out.println(cb+" "+cw);
		int ans=cb+cw;
		System.out.println(ans);
	}
}
/*
BBBWW

WWWWWW

WBWBWBWBWB
*/
