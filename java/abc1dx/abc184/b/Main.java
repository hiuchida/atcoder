import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int x=sc.nextInt();
		String s=sc.next();
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)=='o') x++;
			else if (x>0) x--;
		}
		int ans=x;
		System.out.println(ans);
	}
}
/*
3 0
xox

20 199999
oooooooooxoooooooooo

20 10
xxxxxxxxxxxxxxxxxxxx
*/
