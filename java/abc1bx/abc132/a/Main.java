import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		Arrays.sort(ary);
		if (ary[0]==ary[1] && ary[1]!=ary[2] && ary[2]==ary[3]) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
ASSA

STOP

FFEE

FREE
*/
/*
AAAA
*/
