import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ac=new int[n-1];
		int[] as=new int[n-1];
		int[] af=new int[n-1];
		for (int i=0; i<n-1; i++) {
			int c=sc.nextInt();
			int s=sc.nextInt();
			int f=sc.nextInt();
			ac[i]=c;
			as[i]=s;
			af[i]=f;
		}
		for (int i=0; i<n-1; i++) {
			long ans=as[i];
			for (int j=i; j<n-1; j++) {
//				System.out.println(i+" "+j+" "+ans);
				for (int k=0; true; k++) {
					if (ans<=as[j]+k*af[j]) {
						ans=as[j]+k*af[j];
						break;
					}
				}
				ans+=ac[j];
//				System.out.println(i+" "+j+" "+ans);
			}			
			System.out.println(ans);
		}
		System.out.println(0);
	}
}
/*
3
6 5 1
1 10 1

4
12 24 6
52 16 4
99 2 2

4
12 13 1
44 17 17
66 4096 64
*/
