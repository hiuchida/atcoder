import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int n=s.length();
		char ch=s.charAt(0);
		if (!checkUpper(ch)) ng();
		for (int i=1; i<n-1; i++) {
			ch=s.charAt(i);
			if (!checkDigit(ch)) ng();
		}
		String ss=s.substring(1, n-1);
//		System.out.println(ss);
		int v=Integer.parseInt(ss);
		if (v<100000 || 999999<v) ng();
		ch=s.charAt(n-1);
		if (!checkUpper(ch)) ng();
		System.out.println("Yes");
	}
	static boolean checkDigit(char ch) { //abc281_b: 半角数字の判定
		if ('0'<=ch && ch<='9') return true;
		return false;
	}
	static boolean checkUpper(char ch) { //abc192_b,abc281_b,abc291_a: 半角英大文字の判定
		if ('A'<=ch && ch<='Z') return true;
		return false;
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
}
/*
Q142857Z

AB912278C

X900000

K012345K
*/
