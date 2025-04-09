import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int lt=s.indexOf('A');
		int rt=s.lastIndexOf('Z');
		int ans=rt-lt+1;
		System.out.println(ans);
	}
}
/*
QWERTYASDFZXCV

ZABCZ

HASFJGHOGAKZZFEGA
*/
