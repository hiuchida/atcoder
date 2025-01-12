import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int h=calc(n);
		if (n%h==0) System.out.println("Yes");
		else System.out.println("No");
	}
	static int calc(int n) {
		int v=0;
		while (n>0) {
			v+=n%10;
			n/=10;
		}
		return v;
	}
}
/*
12

57

148
*/
