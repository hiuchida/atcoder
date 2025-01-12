import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
//		int w1=s.length()/t.length();
//		int w2=(s.length()+t.length()-1)/t.length();
		for (int i=1; i<=s.length(); i++) {
			boolean rc=check(s, t, i);
			if (rc) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
	static boolean check(String s, String t, int w) {
		if (s.length()<=w) return false;
		for (int c=0; c<w; c++) {
			boolean rc=true;
			for (int i=c,j=0; true; i+=w,j++) {
				if (i>=s.length()) {
					if (j>=t.length()) {
						break;
					}	
					rc=false;
					break;
				}
				if (j>=t.length()) {
					rc=false;
					break;
				}	
				char chs = s.charAt(i);
				char cht = t.charAt(j);
//				System.out.println(w+" "+i+" "+chs+" "+cht);
				if (chs!=cht) {
					rc=false;
					break;
				}
			}
			if (rc) return true;
		}
		return false;
	}
}
/*
atcoder toe

beginner r

verticalreading agh
*/
