import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long l=sc.nextLong();
		int n1=sc.nextInt();
		int n2=sc.nextInt();
		int[] av1=new int[n1];
		long[] al1=new long[n1];
		for (int i=0; i<n1; i++) {
			av1[i]=sc.nextInt();
			al1[i]=sc.nextLong();
		}
		int[] av2=new int[n2];
		long[] al2=new long[n2];
		for (int i=0; i<n2; i++) {
			av2[i]=sc.nextInt();
			al2[i]=sc.nextLong();
		}
		long ans=0;
		int i1=0;
		int i2=0;
		long lt1=0;
		long lt2=0;
		long rt1=lt1+al1[i1]-1;
		long rt2=lt2+al2[i2]-1;
		while (true) {
//			System.out.println(lt1+" "+rt1+" "+av1[i1]+" , "+lt2+" "+rt2+" "+av2[i2]);
			if (av1[i1]==av2[i2]) {
				long lt3=Math.max(lt1, lt2);
				long rt3=Math.min(rt1, rt2);
				ans+=rt3-lt3+1;
//				System.out.println(lt3+" "+rt3+" "+ans);
			}
			if (i1==n1-1 && i2==n2-1) break;
			if (rt1==rt2) {
				if (i1<n1-1) {
					lt1=rt1+1;
					i1++;
					rt1=lt1+al1[i1]-1;
				} else if (i2<n2-1) {
					lt2=rt2+1;
					i2++;
					rt2=lt2+al2[i2]-1;
				}
			} else if (rt1<rt2) {
				if (i1<n1-1) {
					lt1=rt1+1;
					i1++;
					rt1=lt1+al1[i1]-1;
				} else if (i2<n2-1) {
					lt2=rt2+1;
					i2++;
					rt2=lt2+al2[i2]-1;
				}
			} else if (rt1>rt2) {
				if (i2<n2-1) {
					lt2=rt2+1;
					i2++;
					rt2=lt2+al2[i2]-1;
				} else if (i1<n1-1) {
					lt1=rt1+1;
					i1++;
					rt1=lt1+al1[i1]-1;
				}
			}
		}
		System.out.println(ans);
	}
}
/*
8 4 3
1 2
3 2
2 3
3 1
1 4
2 1
3 3

10000000000 1 1
1 10000000000
1 10000000000

1000 4 7
19 79
33 463
19 178
33 280
19 255
33 92
34 25
19 96
12 11
19 490
33 31
*/
