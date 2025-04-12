import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int x=sc.nextInt();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			if (a!=x) System.out.print(a+" ");
		}
		System.out.println();
	}
}
/*
5 5
3 5 6 5 4

3 3
3 3 3
*/
