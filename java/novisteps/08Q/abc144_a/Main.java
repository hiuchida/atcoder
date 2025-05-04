import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int ans=a*b;
		if (a<10 && b<10) System.out.println(ans);
		else System.out.println(-1);
	}
}
/*
2 5

5 10

9 9
*/
