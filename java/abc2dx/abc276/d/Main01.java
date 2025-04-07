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
		int min=ary[0];
		long ans=0;
		for (int i=n-1; i>0; i--) {
			while (ary[i]>min) {
				boolean bdone=true;
				if (ary[i]%2==0 && ary[i]/2>=min) {
					ary[i]/=2;
					ans++;
					bdone=false;
				}
				if (ary[i]%3==0 && ary[i]/3>=min) {
					ary[i]/=3;
					ans++;
					bdone=false;
				}
				if (bdone) break;
			}
			if (ary[i]!=min) {
				ans=-1;
				break;
			}
		}
		System.out.println(ans);
	}
}
/*
3
1 4 3

3
2 7 6

6
1 1 1 1 1 1
*/
