import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
		long ans=-1;
		for (int i=0; i<n; i++) {
			if (ary[i]=='o') {
				boolean blt=false;
				if (i>0 && ary[i-1]=='-') blt=true;
				int j;
				for (j=i+1; j<n; j++) {
					if (ary[j]!='o') break;
				}
				boolean brt=false;
				if (j<n && ary[j]=='-') brt=true;
				if (blt || brt) {
					ans=Math.max(ans, j-i);
//					System.out.println(i+" "+j+" "+ans);
				}
				i=j-1;
			}
		}
		System.out.println(ans);
	}
}
/*
10
o-oooo---o

1
-

30
-o-o-oooo-oo-o-ooooooo--oooo-o
*/
