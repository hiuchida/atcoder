import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int x=sc.nextInt();
		int y=sc.nextInt();
		int z=sc.nextInt();
		int ans=(x-z)/(y+z);
		System.out.println(ans);
	}
}
/*
13 3 1

12 3 1

100000 1 1

64146 123 456

64145 123 456
*/
