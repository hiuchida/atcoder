import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int idx=s.indexOf(".");
		if (idx>=0) s=s.substring(0, idx);
		System.out.println(s);
	}
}
/*
5.90

0

84939825309432908832902189.9092309409809091329
*/
