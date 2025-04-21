import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int r=sc.nextInt();
		int d=sc.nextInt();
		long x=sc.nextInt();
		for (int i=1; i<=10; i++) {
			long ans=r*x-d;
			System.out.println(ans);
			x=ans;
		}
	}
}
/*
2 10 20

4 40 60
*/
