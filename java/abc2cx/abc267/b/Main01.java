import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int[] ary=new int[s.length()+1];
		for (int i=0; i<s.length(); i++) {
			ary[i+1]=s.charAt(i)=='1' ? 1 : 0;
		}
		if (ary[1]>0) {
			System.out.println("No");
			System.exit(0);
		}
		int[] cnt=new int[7];
		cnt[0]=ary[7];
		cnt[1]=ary[4];
		cnt[2]=ary[2]+ary[8];
		cnt[3]=ary[1]+ary[5];
		cnt[4]=ary[3]+ary[9];
		cnt[5]=ary[6];
		cnt[6]=ary[10];
		for (int i=0; i<6; i++) {
			for (int j=i+2; j<6; j++) {
				if (cnt[i]>0 && cnt[j]>0) {
					boolean bhit=false;
					for (int k=i+1; k<j; k++) {
						if (cnt[k]>0) {
							bhit=true;
						}
					}
					if (!bhit) {
						System.out.println("Yes");
						System.exit(0);
					}
				}
			}
		}
		System.out.println("No");
	}
}
/*
0101110101

0100101001

0000100110

1101110101
*/
