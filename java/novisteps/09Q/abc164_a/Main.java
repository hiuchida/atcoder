import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int s=sc.nextInt();
		int w=sc.nextInt();
		if (s<=w) System.out.println("unsafe");
		else System.out.println("safe");
	}
}
/*
4 5

100 2

10 10
*/
