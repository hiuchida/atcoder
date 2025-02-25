import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] ary=new String[51];
		for (int i=0; i<n; i++) {
			String s = sc.next();
			ary[s.length()]=s;
		}
		for (int i=0; i<ary.length; i++) {
			String s=ary[i];
			if (s!=null) System.out.print(s);
		}
		System.out.println();
	}
}
/*
3
tc
oder
a

4
cat
enate
on
c
*/
