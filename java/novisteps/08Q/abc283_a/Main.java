import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int ans=a;
		for (int i=1; i<b; i++) ans*=a;
		System.out.println(ans);
	}
}
/*
4 3

5 5

8 1
*/
