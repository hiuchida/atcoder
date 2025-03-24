import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int k = sc.nextInt();
		TreeSet<String> set=new TreeSet<>();
		for (int i=0; i+k<=s.length(); i++) {
			String ss=s.substring(i, i+k);
			if (!set.contains(ss)) set.add(ss);
		}
		System.out.println(set.size());
	}
}
/*
abcabc
2

aaaaa
1

hello
10
*/
