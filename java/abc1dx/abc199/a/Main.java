import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		int aa=a*a;
		int bb=b*b;
		int cc=c*c;
		if (aa+bb<cc) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
2 2 4

10 10 10

3 4 5
*/
