import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		if (ary[0]==ary[1] && ary[1]==ary[2]) System.out.println("Won");
		else System.out.println("Lost");
	}
}
/*
SSS

WVW
*/
