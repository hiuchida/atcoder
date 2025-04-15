import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		if (ary[0]==ary[1] || ary[1]==ary[2] || ary[2]==ary[3]) System.out.println("Bad");
		else System.out.println("Good");
	}
}
/*
3776

8080

1333

0024
*/
