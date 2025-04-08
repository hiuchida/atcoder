import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int[] ary=new int[4];
		for (int i=0; i<ary.length; i++) {
			ary[i]=s.charAt(i)-'0';
		}
		for (int i=0; i < 1<<3; i++) {
			String ans=""+ary[0];
			int x=ary[0];
			for (int j=0; j<3; j++) {
				int mask=1<<j;
				if ((i&mask)>0) {
					ans=ans+"+"+ary[j+1];
					x+=ary[j+1];
				} else {
					ans=ans+"-"+ary[j+1];
					x-=ary[j+1];
				}
			}
			if (x==7) {
				ans+="=7";
				System.out.println(ans);
				System.exit(0);
			}
		}
	}
}
/*
1222

0290

3242
*/
