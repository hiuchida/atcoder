import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int k=sc.nextInt();
		int x=sc.nextInt();
		int ans=500*k;
		if (ans>=x) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
2 900

1 501

4 2000
*/
