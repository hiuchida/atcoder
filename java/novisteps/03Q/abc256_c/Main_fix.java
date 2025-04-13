import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=3;
		int[] ah=new int[n];
		int[] aw=new int[n];
		for (int i=0; i<n; i++) {
			ah[i]=sc.nextInt();
		}
		for (int i=0; i<n; i++) {
			aw[i]=sc.nextInt();
		}
		long ans=0;
		for (int a1=1; a1<ah[0]; a1++) {
			for (int b1=1; b1<ah[0]; b1++) {
				int c1=ah[0]-a1-b1;
				if (c1<1) break;
				for (int a2=1; a2<ah[1]; a2++) {
					for (int b2=1; b2<ah[1]; b2++) {
						int c2=ah[1]-a2-b2;
						if (c2<1) break;
						int a3=aw[0]-a1-a2;
						if (a3<1) break;
						int b3=aw[1]-b1-b2;
						if (b3<1) break;
						int c3=aw[2]-c1-c2;
						if (c3<1) continue;
						if (a3+b3+c3!=ah[2]) break;
						ans++;
//						System.out.println(a1+" "+b1+" "+c1);
//						System.out.println(a2+" "+b2+" "+c2);
//						System.out.println(a3+" "+b3+" "+c3);
//						System.out.println();
					}
				}
			}
		}
		System.out.println(ans);
	}
}
/*
3 4 6 3 3 7

3 4 5 6 7 8

5 13 10 6 13 9

20 25 30 22 29 24
*/
