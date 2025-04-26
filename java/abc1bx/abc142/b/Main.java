import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		long ans=0;
		for (int i=0; i<n; i++) {
			int h=sc.nextInt();
			if (h>=k) ans++;
		}
		System.out.println(ans);
	}
}
/*
4 150
150 140 100 200

1 500
499

5 1
100 200 300 400 500
*/
