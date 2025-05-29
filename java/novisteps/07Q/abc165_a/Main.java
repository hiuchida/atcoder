import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int k=sc.nextInt();
		int a=sc.nextInt();
		int b=sc.nextInt();
		int ak=a/k;
		if (a%k!=0) ak++;
		int bk=b/k;
//		System.out.println(ak+" "+bk);
		if (ak>bk) {
			System.out.println("NG");
			System.exit(0);
		}
		System.out.println("OK");
	}
}
/*
7
500 600

4
5 7

1
11 11
*/
/*
4
1 3
*/
