import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int pos=0;
		for (int i=0; i<n; i++) {
			String s = sc.next();
			int d = sc.nextInt();
			if (d<a) d=a;
			else if (d>b) d=b;
			if ("East".equals(s)) pos+=d;
			else pos-=d;
		}
		if (pos>0) System.out.println("East "+pos);
		else if (pos<0) System.out.println("West "+(-pos));
		else System.out.println(0);
	}
}
/*
3 5 10
East 7
West 3
West 11

3 3 8
West 6
East 3
East 1

5 25 25
East 1
East 1
West 1
East 100
West 1
*/
