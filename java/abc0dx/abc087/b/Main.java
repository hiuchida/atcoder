import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		int x=sc.nextInt();
		long ans=0;
		for (int i=0; i<=a; i++) {
			int aa=i*500;
			if (aa>x) break;
			for (int j=0; j<=b; j++) {
				int bb=j*100;
				if (aa+bb>x) break;
				for (int k=0; k<=c; k++) {
					int cc=k*50;
					if (aa+bb+cc==x) ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
/*
2
2
2
100

5
1
0
150

30
40
50
6000
*/
