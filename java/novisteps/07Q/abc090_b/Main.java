import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ans=0;
		for (int i=a; i<=b; i++) {
			if (check(i)) ans++;
		}
		System.out.println(ans);
	}
	static boolean check(int i) {
		int a=i/10000;
		int b=i/1000%10;
		int d=i/10%10;
		int e=i%10;
		if (a==e && b==d) return true;
		return false;
	}
}
/*
11009 11332

31415 92653
*/
