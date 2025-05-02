import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int l=sc.nextInt();
		String[] ary=new String[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.next();
		}
		Arrays.sort(ary);
		for (String s : ary) {
			System.out.print(s);
		}
		System.out.println();
	}
}
/*
3 3
dxx
axx
cxx
*/
