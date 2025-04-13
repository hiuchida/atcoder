import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String s=sc.next();
		for (int i=0; i<n; i++) {
			if (s.charAt(i)=='1') {
				if (i%2==0) System.out.println("Takahashi");
				else System.out.println("Aoki");
				System.exit(0);
			}
		}
	}
}
/*
5
00101

3
010
*/
