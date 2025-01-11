import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char[] ary = s.toCharArray();
		Arrays.sort(ary);
		if (ary[0]=='a' && ary[1]=='b' && ary[2]=='c') System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
bac

bab

abc

aaa
*/
