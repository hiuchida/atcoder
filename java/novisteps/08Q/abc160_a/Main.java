import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		if (ary[2]==ary[3] && ary[4]==ary[5]) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
sippuu

iphone

coffee
*/
