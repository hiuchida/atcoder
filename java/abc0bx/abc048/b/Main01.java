import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long a=sc.nextLong();
		long b=sc.nextLong();
		long x=sc.nextLong();
		long aa=(a-1)/x;
		long bb=b/x;
//		System.out.println(aa+" "+bb);
		System.out.println(bb-aa);
	}
}
/*
4 8 2

0 5 1

9 9 2

1 1000000000000000000 3
*/
