import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long at=0;
		long aa=0;
		for (int i=0; i<n; i++) {
			int t=sc.nextInt();
			int a=sc.nextInt();
			if (at==0) {
				at=t;
				aa=a;
//				System.out.println(i+" : "+at+" "+aa);
			} else {
				long nt=at/t;
				long na=aa/a;
				long nm=Math.max(nt, na);
				for (long j=nm; true; j++) {
					long tt=j*t;
					long ta=j*a;
					if (tt>=at && ta>=aa) {
						at=tt;
						aa=ta;
						break;
					}
				}
//				System.out.println(i+" : "+at+" "+aa+" , "+nt+" "+na+" "+nm);
			}
		}
		long ans=at+aa;
		System.out.println(ans);
	}
}
/*
3
2 3
1 1
3 2

4
1 1
1 1
1 5
1 100

5
3 10
48 17
31 199
231 23
3 2
*/
