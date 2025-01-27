import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int idx=s.indexOf(".");
		int a=Integer.parseInt(s.substring(0, idx));
		int b=Integer.parseInt(s.substring(idx+1, idx+2));
		if (b>4) a++;
		System.out.println(a);
	}
}
/*
3.456

99.500

0.000
*/
