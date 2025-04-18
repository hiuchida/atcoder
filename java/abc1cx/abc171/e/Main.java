import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		int sum=0;
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			sum ^= ary[i];
		}
		for (int i=0; i<n; i++) {
			int v=sum ^ ary[i];
			System.out.print(v+" ");
		}
		System.out.println();
	}
}
/*
4
20 11 9 24
*/
