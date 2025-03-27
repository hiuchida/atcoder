import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char[] ary=s.toCharArray();
		Arrays.sort(ary);
		for (int i=0; i<s.length(); i++) {
			char ch=(char)('0'+i);
			if (ary[i]!=ch) {
				System.out.println(i);
				System.exit(0);
			}
		}
		System.out.println(9);
	}
}
/*
023456789

459230781
*/
