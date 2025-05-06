import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int l=sc.nextInt();
		int h=sc.nextInt();
		int n=sc.nextInt();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			int ans=0;
			if (a>h) ans=-1;
			else if (a<l) ans=l-a;
			System.out.println(ans);
		}
	}
}
/*
300 400
3
240
350
480

50 80
5
10000
50
81
80
0
*/
