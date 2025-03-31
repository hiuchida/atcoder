import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		char[] cs=s.toCharArray();
		char[] ct=t.toCharArray();
		int is=0;
		int it=0;
		while (is<cs.length && it<ct.length) {
			char chs=cs[is];
			char cht=ct[it];
			if (chs!=cht) {
				System.out.println("No");
				System.exit(0);
			}
			int js=is+1;
			for (; js<cs.length; js++) {
				if (cs[js]!=chs) break;
			}
			int jt=it+1;
			for (; jt<ct.length; jt++) {
				if (ct[jt]!=cht) break;
			}
			int lens=js-is;
			int lent=jt-it;
			if (lens!=lent) {
				if (lens==1 || lens>lent) {
					System.out.println("No");
					System.exit(0);
				}
			}
			is=js;
			it=jt;
		}
		if (is==cs.length && it==ct.length) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
abbaac
abbbbaaac

xyzz
xyyzz
*/
