import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] ary=new String[n];
		for (int i=0; i<n; i++) {
			String s = sc.next();
			ary[i]=s;
		}
		for (int i=n-1; i>=0; i--) {
			System.out.println(ary[i]);
		}
	}
}
/*
3
Takahashi
Aoki
Snuke

4
2023
Year
New
Happy
*/
