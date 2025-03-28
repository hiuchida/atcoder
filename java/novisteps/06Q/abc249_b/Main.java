import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		TreeSet<Integer> setl=new TreeSet<>();
		TreeSet<Integer> setu=new TreeSet<>();
		for (int i=0; i<s.length(); i++) {
			char ch=s.charAt(i);
			if ('a'<=ch && ch<='z') {
				if (setl.contains((int)ch)) {
					System.out.println("No");
					System.exit(0);
				}
				setl.add((int)ch);
			} else {
				if (setu.contains((int)ch)) {
					System.out.println("No");
					System.exit(0);
				}
				setu.add((int)ch);
			}
		}
		if (setl.size()==0 || setu.size()==0) {
			System.out.println("No");
			System.exit(0);
		}
		System.out.println("Yes");
	}
}
/*
AtCoder

Aa

atcoder

Perfect
*/
