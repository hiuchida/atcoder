import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int q=sc.nextInt();
		int[] ary=new int[n+1];
		for (int qq=0; qq<q; qq++) {
			int c=sc.nextInt();
			int x=sc.nextInt();
			switch (c) {
			case 1:
				ary[x]++;
				break;
			case 2:
				ary[x]+=2;
				break;
			case 3:
				if (ary[x]>=2) System.out.println("Yes");
				else System.out.println("No");
				break;
			}
		}
//		System.out.println(Arrays.toString(ary));
	}
}
/*
3 9
3 1
3 2
1 2
2 1
3 1
3 2
1 2
3 2
3 3
*/
