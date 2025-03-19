import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		double v=Double.parseDouble(s);
		int ans=0;
		if (v>=38.0) ans=1;
		else if (v>=37.5) ans=2;
		else ans=3;
		System.out.println(ans);
	}
}
/*
40.0

37.7

36.6
*/
