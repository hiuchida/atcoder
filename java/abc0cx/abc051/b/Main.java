import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int k=sc.nextInt();
		int s=sc.nextInt();
		long ans=0;
		for (int x=0; x<=k; x++) {
			for (int y=0; y<=k; y++) {
				int z=s-x-y;
				if (0<=z && z<=k) ans++;
			}
		}
		System.out.println(ans);
	}
}
/*
2 2

5 15
*/
