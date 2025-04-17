import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		for (int i=0; i<ary.length; i++) {
			if (checkUpper(ary[i])) {
				System.out.println(i+1);
				System.exit(0);
			}
		}
	}
	static boolean checkUpper(char ch) { //abc192_b,abc281_b,abc291_a: 半角英大文字の判定
		if ('A'<=ch && ch<='Z') return true;
		return false;
	}
}
/*
aBc

xxxxxxXxxx

Zz
*/
