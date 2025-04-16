import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int fcnt=0;
		int acnt=0;
		for (int i=0; i<n; i++) {
			String s=sc.next();
			if ("For".equals(s)) fcnt++;
			else acnt++;
		}
		if (fcnt>acnt) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
3
For
Against
For

5
Against
Against
For
Against
For

1
For
*/
