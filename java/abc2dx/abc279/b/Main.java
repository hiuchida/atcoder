import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		String t=sc.next();
		int idx=s.indexOf(t);
		if (idx>=0) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
voltage
tag

atcoder
ace

gorilla
gorillagorillagorilla

toyotasystems
toyotasystems
*/
