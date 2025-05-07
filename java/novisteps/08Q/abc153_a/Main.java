import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h=sc.nextInt();
		int a=sc.nextInt();
		int ans=h/a;
		h-=ans*a;
		if (h>0) ans++;
		System.out.println(ans);
	}
}
/*
10 4

1 10000

10000 1
*/
