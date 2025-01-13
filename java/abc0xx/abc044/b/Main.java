import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int[] ary = new int[256];
		for (int i=0; i<s.length(); i++) {
			char ch=s.charAt(i);
			ary[ch]++;
		}
		for (int i='a'; i<='z'; i++) {
			if (ary[i]%2==1) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
}
/*
abaccaba

hthth
*/
