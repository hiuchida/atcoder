import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long k = sc.nextLong();
		String s="";
		while (k>0) {
			if (k%2==0) s="0"+s;
			else s="2"+s;
			k/=2;
		}
		System.out.println(s);
	}
}
/*
3

11

923423423420220108
*/
