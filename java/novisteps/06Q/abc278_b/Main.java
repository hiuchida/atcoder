import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h=sc.nextInt();
		int m=sc.nextInt();
		while (true) {
			int a=h/10;
			int b=h%10;
			int c=m/10;
			int d=m%10;
			int hh=a*10+c;
			int mm=b*10+d;
			if (0<=hh && hh<=23 && 0<=mm && mm<=59) {
				System.out.println(h+" "+m);
				System.exit(0);
			}
			m++;
			if (m>59) {
				m=0;
				h++;
				if (h>23) h=0;
			}
		}
	}
}
/*
1 23

19 57

20 40
*/
