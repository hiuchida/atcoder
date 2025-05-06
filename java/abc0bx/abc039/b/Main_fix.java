import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int x=sc.nextInt();
		for (int ans=1; true; ans++) {
			if (ans*ans*ans*ans==x) {
				System.out.println(ans);
				System.exit(0);
			}
		}
	}
}
/*
1

981506241

390625
*/
