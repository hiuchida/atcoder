import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long a=sc.nextLong();
		int b=sc.nextInt();
		int c=sc.nextInt();
		//p=log2 a   2^p=a
		//q=log2 c^b 2^q=c^b
		long x=1;
		for (int i=0; i<b; i++) x*=c;
//		System.out.println(a+" "+x);
		if (a<x) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
4 3 2

16 3 2

8 3 2
*/
/*
9000000000000000000 17 13
*/
