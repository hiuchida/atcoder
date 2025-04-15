import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int mid=(a+b)/2;
		if (Math.abs(a-mid)!=Math.abs(b-mid)) System.out.println("IMPOSSIBLE");
		else System.out.println(mid);
	}
}
/*
2 16

0 3

998244353 99824435
*/
