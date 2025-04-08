import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[3];
		for (int i=0; i<3; i++) {
			ary[i]=sc.nextInt();
			if (n==ary[i]) {
				System.out.println("NO");
				System.exit(0);
			}
		}
		Arrays.sort(ary);
//		if (ary[0]+1==ary[1] && ary[1]+1==ary[2]) {
//			System.out.println("NO");
//			System.exit(0);
//		}
		int cnt=0;
		while (n>0) {
			int a=n-3;
			if (Arrays.binarySearch(ary, a)<0) {
				n=a;
				cnt++;
				continue;
			}
			int b=n-2;
			if (Arrays.binarySearch(ary, b)<0) {
				n=b;
				cnt++;
				continue;
			}
			int c=n-1;
			if (Arrays.binarySearch(ary, c)<0) {
				n=c;
				cnt++;
				continue;
			}
			System.out.println("NO");
			System.exit(0);
		}
		if (cnt>100) {
			System.out.println("NO");
			System.exit(0);
		}
		System.out.println("YES");
	}
}
/*
2
1
7
15

5
1
4
2

300
57
121
244
*/
/*
10
1
10
5

5
3
2
1

1
2
3
4
*/
