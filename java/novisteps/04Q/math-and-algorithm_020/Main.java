import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		long ans=0;
		for (int a=0; a<n; a++) {
			for (int b=a+1; b<n; b++) {
				for (int c=b+1; c<n; c++) {
					for (int d=c+1; d<n; d++) {
						for (int e=d+1; e<n; e++) {
							int sum=ary[a]+ary[b]+ary[c]+ary[d]+ary[e];
							if (sum==1000) ans++;
						}
					}
				}
			}
		}			
		System.out.println(ans);
	}
}
/*
5
100 150 200 250 300

13
243 156 104 280 142 286 196 132 128 195 265 300 130
*/
