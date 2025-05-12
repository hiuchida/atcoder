import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int r=sc.nextInt();
		int x=sc.nextInt();
		if (x==1) {
			if (1600<=r && r<=2999) System.out.println("Yes");
			else System.out.println("No");
		} else {
			if (1200<=r && r<=2399) System.out.println("Yes");
			else System.out.println("No");
		}
	}
}
/*
2000 1

1000 1

1500 2

2800 2
*/
