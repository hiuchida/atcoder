import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=sc.nextInt();
		}
		int q=sc.nextInt();
		for (int qq=0; qq<q; qq++) {
			int cmd=sc.nextInt();
			int k=sc.nextInt();
			if (cmd==1) {
				int x=sc.nextInt();
				ary[k]=x;
			} else {
				System.out.println(ary[k]);
			}
		}
	}
}
/*
3
1 3 5
7
2 2
2 3
1 3 0
2 3
1 2 8
2 2
2 1

5
22 2 16 7 30
10
1 4 0
1 5 0
2 2
2 3
2 4
2 5
1 4 100
1 5 100
2 3
2 4

7
478 369 466 343 541 42 165
20
2 1
1 7 729
1 6 61
1 6 838
1 3 319
1 4 317
2 4
1 1 673
1 3 176
1 5 250
1 1 468
2 6
1 7 478
1 5 595
2 6
1 6 599
1 6 505
2 3
2 5
2 1
*/
