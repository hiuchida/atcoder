import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int[] ary = new int[256];
		for (int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			ary[ch]++;
		}
		for (int i='a'; i<='z'; i++) {
			if (ary[i]>1) {
				System.out.println("no");
				System.exit(0);
			}
		}
		System.out.println("yes");
	}
}
/*
uncopyrightable

different

no
*/
