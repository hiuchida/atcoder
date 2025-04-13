import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
		boolean bdq=false;
		for (int i=0; i<ary.length; i++) {
			if (ary[i]=='\"') bdq=!bdq;
			else if (ary[i]==',') {
				if (!bdq) ary[i]='.';
			}
		}
		String ans=new String(ary);
		System.out.println(ans);
	}
}
/*
8
"a,b"c,d

5
,,,,,

20
a,"t,"c,"o,"d,"e,"r,
*/
