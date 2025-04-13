import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=4;
		Set<String> set=new HashSet<>();
		for (int i=0; i<n; i++) {
			set.add(sc.next());
		}
		if (set.size()==4) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
3B
HR
2B
H

2B
3B
HR
3B
*/
