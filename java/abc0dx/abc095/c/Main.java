import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		int x=sc.nextInt();
		int y=sc.nextInt();
		long ans=Long.MAX_VALUE;
		for (int ab=0; ab<=Math.max(x, y)*2; ab+=2) {
			long a1=(long)ab*c;
			int x1=x-ab/2;
			int y1=y-ab/2;
			if (x1<0) x1=0;
			if (y1<0) y1=0;
			long a2=(long)a*x1;
			long a3=(long)b*y1;
			long a4=a1+a2+a3;
//			System.out.println(ab+" "+x1+" "+y1+" "+a1+" "+a2+" "+a3+" "+a4);
			ans=Math.min(ans, a4);
		}
		System.out.println(ans);
	}
}
/*
1500 2000 1600 3 2

1500 2000 1900 3 2

1500 2000 500 90000 100000
*/
