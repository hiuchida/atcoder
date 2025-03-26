import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int f1=calc(t);
		int f2=calc(f1+t);
		int f3=calc(f1);
		int f4=calc(f2+f3);
		System.out.println(f4);
	}
	static int calc(int x) {
		return x*x+2*x+3;
	}
}
/*
0

3

10
*/
