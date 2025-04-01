import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char[] ary=s.toCharArray();
		Arrays.sort(ary);
		if (ary[0]!=ary[1]) System.out.println(ary[0]);
		else if (ary[1]!=ary[2]) System.out.println(ary[1]);
		else System.out.println(-1);
	}
}
/*
pop

abc

xxx
*/
