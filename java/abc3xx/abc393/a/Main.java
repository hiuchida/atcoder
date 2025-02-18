import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s2 = sc.next();
		boolean b1 = s1.equals("sick");
		boolean b2 = s2.equals("sick");
		int ans = 0;
		if (b1 && b2) ans=1;
		else if (b1) ans=2;
		else if (b2) ans=3;
		else ans=4;
		System.out.println(ans);
	}
}
/*
sick fine

fine fine
*/
