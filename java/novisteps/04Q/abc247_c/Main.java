import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String ans=func(n);
		System.out.println(ans);
	}
	static String func(int n) {
		if (n==1) {
			return "1";
		}
		String s=func(n-1);
		return s+" "+n+" "+s;
	}
}
/*
2

1

4
*/
