import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		String ans=s.substring(1, 3)+s.substring(0, 1);
		System.out.println(ans);
	}
}
/*
abc

aab
*/
