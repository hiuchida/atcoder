import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		for (int i=0; i<ary.length; i++) {
			if (i%2==0) {
				if (!checkLower(ary[i])) {
					System.out.println("No");
					System.exit(0);
				}
			} else {
				if (!checkUpper(ary[i])) {
					System.out.println("No");
					System.exit(0);
				}
			}
		}
		System.out.println("Yes");
	}
	static boolean checkUpper(char ch) { //abc192_b,abc281_b: 半角英大文字の判定
		if ('A'<=ch && ch<='Z') return true;
		return false;
	}
	static boolean checkLower(char ch) { //abc192_b: 半角英小文字の判定
		if ('a'<=ch && ch<='z') return true;
		return false;
	}
}
/*
dIfFiCuLt

eASY

a
*/
