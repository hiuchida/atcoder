import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		while (n>0) {
			if (n%10==7) {
				System.out.println("Yes");
				System.exit(0);
			}
			n/=10;
		}
		System.out.println("No");
	}
}
/*
117

123

777
*/
