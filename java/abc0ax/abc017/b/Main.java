import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		s=s.replaceAll("ch", "");
		s=s.replaceAll("o", "");
		s=s.replaceAll("k", "");
		s=s.replaceAll("u", "");
		if (s.length()==0) System.out.println("YES");
		else System.out.println("NO");
	}
}
/*
chokuou

kuccho

atcoder
*/
