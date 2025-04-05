import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=400/a;
		if (a*b==400) System.out.println(b);
		else System.out.println(-1);
	}
}
/*
10

11

400
*/
