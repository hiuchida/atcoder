import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int r=sc.nextInt();
		int ans=r;
		if (n<10) {
			ans=r+100*(10-n);
		}
		System.out.println(ans);
	}
}
/*
2 2919

22 3051
*/
