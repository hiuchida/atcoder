import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int x=sc.nextInt();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			if (a==x) {
				System.out.println(i+1);
				System.exit(0);
			}
		}
	}
}
/*
4 3
2 3 1 4

5 2
3 5 1 4 2

6 6
1 2 3 4 5 6
*/
