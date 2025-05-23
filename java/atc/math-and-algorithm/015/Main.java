import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		while (a>=1 && b>=1) {
			if (a>b) a%=b;
			else b%=a;
		}
		if (a>=1) System.out.println(a);
		else System.out.println(b);
	}
}
/*
33 88

123 777
*/
