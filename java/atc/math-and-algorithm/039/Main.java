import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int q=sc.nextInt();
		int[] ary=new int[n+2];
		for (int qq=0; qq<q; qq++) {
			int l=sc.nextInt();
			int r=sc.nextInt();
			int x=sc.nextInt();
			ary[l]+=x;
			ary[r+1]-=x;
		}
//		System.out.println(Arrays.toString(ary));
		for (int i=2; i<=n; i++) {
			if (ary[i]>0) System.out.print("<");
			else if (ary[i]<0) System.out.print(">");
			else System.out.print("=");
		}
		System.out.println();
	}
}
/*
5 3
1 2 3
2 5 4
2 4 1

10 10
1 1 1
6 7 28
3 5 4096
6 10 2000
1 10 10000
2 8 2
10 10 2
1 2 8000
6 7 20
6 8 2048
*/
