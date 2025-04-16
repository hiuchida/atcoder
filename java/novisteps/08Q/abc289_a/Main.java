import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		for (int i=0; i<ary.length; i++) {
			char ch=ary[i]=='0' ? '1' : '0';
			System.out.print(ch);
		}
		System.out.println();
	}
}
/*
01

1011

100100001
*/
