import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		boolean blogin=false;
		int ans=0;
		for (int i=0; i<n; i++) {
			String s=sc.next();
			switch (s) {
			case "login":
				blogin=true;
				break;
			case "logout":
				blogin=false;
				break;
			case "public":
				break;
			case "private":
				if (!blogin) ans++;
				break;
			}
		}
		System.out.println(ans);
	}
}
/*
6
login
private
public
logout
private
public

4
private
private
private
logout

20
private
login
private
logout
public
logout
logout
logout
logout
private
login
login
private
login
private
login
public
private
logout
private
*/
