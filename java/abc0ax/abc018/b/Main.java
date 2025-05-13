import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		int n=sc.nextInt();
		for (int i=0; i<n; i++) {
			int l=sc.nextInt();
			int r=sc.nextInt();
			char[] ary2=Arrays.copyOf(ary, s.length());
			for (int j=l; j<=r; j++) {
				int i2=r-j+l;
				ary2[j-1]=ary[i2-1];
			}
//			System.out.println(i+" "+Arrays.toString(ary2));
			ary=ary2;
		}
		String ans=new String(ary);
		System.out.println(ans);
	}
}
/*
abcdef
2
3 5
1 4

redcoat
3
1 7
1 2
3 4
*/
