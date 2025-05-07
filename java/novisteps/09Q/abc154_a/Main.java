import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		String t=sc.next();
		int a=sc.nextInt();
		int b=sc.nextInt();
		String u=sc.next();
		if (s.equals(u)) a--;
		else b--;
		System.out.println(a+" "+b);
	}
}
/*
red blue
3 4
red

red blue
5 5
blue
*/
