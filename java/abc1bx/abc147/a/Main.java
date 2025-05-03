import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int sum=0;
		for (int i=0; i<3; i++) {
			sum+=sc.nextInt();
		}
		if (sum<=21) System.out.println("win");
		else System.out.println("bust");
	}
}
/*
5 7 9

13 7 2
*/
