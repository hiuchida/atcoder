import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int ans=0;
		for (int i=0; i<s.length(); i++) {
			char ch=s.charAt(i);
			if (ch=='v') ans++;
			else ans+=2;
		}
		System.out.println(ans);
	}
}
/*
vvwvw

v

wwwvvvvvv
*/
