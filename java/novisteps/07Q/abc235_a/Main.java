import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x=sc.nextInt();
		int a = x/100;
		int b = x/10%10;
		int c = x%10;
		int abc=a+b+c;
		int ans=100*abc+10*abc+abc;
		System.out.println(ans);
	}
}
/*
123

999
*/
