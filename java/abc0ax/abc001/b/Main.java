import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int m=sc.nextInt();
		int ans=0;
		if (m<100) ans=0;
		else if (m<=5000) ans=m/100;
		else if (m<=30000) ans=m/1000+50;
		else if (m<=70000) ans=(m/1000-30)/5+80;
		else ans=89;
		System.out.println(String.format("%02d", ans));
	}
}
/*
15000

75000

200
*/
