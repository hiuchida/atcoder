import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=3;
		int[] ary=new int[N];
		for (int i=0; i<N; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		int ans=0;
		if (ary[0]%2==0) {
			if (ary[1]%2==0) {
				if (ary[2]%2==0) {
				} else {
					ary[0]++;
					ary[1]++;
					ans++;
				}
			} else {
				if (ary[2]%2==0) {
					ary[0]++;
					ary[2]++;
					ans++;
				} else {
					ary[1]++;
					ary[2]++;
					ans++;
				}
			}
		} else {
			if (ary[1]%2==0) {
				if (ary[2]%2==0) {
					ary[1]++;
					ary[2]++;
					ans++;
				} else {
					ary[0]++;
					ary[2]++;
					ans++;
				}
			} else {
				if (ary[2]%2==0) {
					ary[0]++;
					ary[1]++;
					ans++;
				} else {
				}
			}
		}
//		System.out.println(Arrays.toString(ary));
		ans+=(ary[2]-ary[0])/2;
		ans+=(ary[2]-ary[1])/2;
		System.out.println(ans);
	}
}
/*
2 5 4

2 6 3

31 41 5
*/
/*
2 4 6
2 4 7
2 5 6
2 5 7
3 4 6
3 4 7
3 5 6
3 5 7
*/
