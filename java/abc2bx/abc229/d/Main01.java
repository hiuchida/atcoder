import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int k = sc.nextInt();
		List<Integer> la=new ArrayList<>();
		List<Integer> lb=new ArrayList<>();
		if (s.charAt(0)=='X') la.add(0);
		for (int i=0; i<s.length(); i++) {
			int j=i+1;
			for (; j<s.length(); j++) {
				if (s.charAt(i)!=s.charAt(j)) break;
			}
			if (s.charAt(i)=='.') la.add(j-i);
			else lb.add(j-i);
			i=j-1;
		}
		System.out.println(la);
		System.out.println(lb);
		System.out.println();
	}
}
/*
XX...X.X.X.
2

XXXX
200000
*/
