import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for (int i=n; true; i++) {
			if (check(i)) {
				System.out.println(i);
				System.exit(0);
			}
		}
	}
	static boolean check(int n) {
		int a=n/100;
		int b=n/10%10;
		int c=n%10;
		return a==b && b==c;
	}
}
/*
111

112

750
*/
