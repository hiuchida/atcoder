import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n+1];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i]=a;
		}
//		System.out.println(Arrays.toString(ary));
		int[] dif=new int[n+1];
		int[] sum=new int[n+1];
		for (int i=0; i<n; i++) {
//			System.out.println(Arrays.toString(ary));
			if (ary[i]==0) {
				;
			} else if (ary[i]<n-i-1) {
				dif[i+1]++;
				dif[i+1+ary[i]]--;
				ary[i]=0;
			} else {
				dif[i+1]++;
				dif[n]--;
				ary[i]-=n-i-1;
			}
			sum[i+1]=sum[i]+dif[i+1];
			ary[i+1]=ary[i+1]+sum[i+1];
//			System.out.println(Arrays.toString(dif));
//			System.out.println(Arrays.toString(sum));
//			System.out.println(Arrays.toString(ary));
//			System.out.println();
		}
		for (int i=0; i<n; i++) {
			System.out.print(ary[i]+" ");
		}
		System.out.println();
//		String s=Arrays.toString(ary);
//		s = s.replace("[", "").replace("]", "").replaceAll(",", "");
//		System.out.println(s);
	}
}
/*
4
5 0 9 3

5
4 6 7 2 5

10
2 9 1 2 0 4 6 7 1 5
*/
