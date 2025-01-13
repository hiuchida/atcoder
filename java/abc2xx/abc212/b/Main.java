import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int a=x/1000;
		int b=x/100%10;
		int c=x/10%10;
		int d=x%10;
		if (a==b && b==c && c==d) System.out.println("Weak");
		else if (weak(a,b) && weak(b,c) && weak(c,d)) System.out.println("Weak");
		else System.out.println("Strong");
	}
	static boolean weak(int a, int b) {
		if (a==9 && b==0) return true;
		if (a+1==b) return true;
		return false;
	}
}
/*
*/
