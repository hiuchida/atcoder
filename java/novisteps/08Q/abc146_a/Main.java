import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final String[] tbl= { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT", };
		String s=sc.next();
		int ans=0;
		for (int i=0; i<tbl.length; i++) {
			if (s.equals(tbl[i])) {
				ans=7-i;
				break;
			}
		}
		System.out.println(ans);
	}
}
/*
SAT

SUN
*/
