import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int n=5;
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(ary));
		int min=10;
		int sum=0;
		for (int i=0; i<n; i++) {
			if (ary[i]%10!=0) min=Math.min(min, ary[i]%10);
			sum+=ary[i];
			if (ary[i]%10!=0) sum+=10-ary[i]%10;
		}
//		System.out.println(sum+" "+min);
		int ans=sum-10+min;
		System.out.println(ans);
	}
}
/*
29
20
7
35
120

101
86
119
108
57

123
123
123
123
123
*/
/*
10
20
30
40
50
*/
