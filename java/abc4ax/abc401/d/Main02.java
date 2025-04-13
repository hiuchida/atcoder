import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
//		System.out.println(Arrays.toString(ary));
		int c=0;
		for (int i=0; i<n; i++) {
			if (ary[i]=='o') {
				c++;
				if (i>0 && ary[i-1]=='?') ary[i-1]='.';
				if (i<n-1 && ary[i+1]=='?') ary[i+1]='.';
			}
		}
		int c0=0;
		for (int i=0; i<n; i++) {
			if (ary[i]=='?') c0++;
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(c+" "+c0);
		if (c+c0==k) {
			for (int i=0; i<n; i++) {
				if (ary[i]=='?') ary[i]='o';
			}
		} else if (c==k) {
			for (int i=0; i<n; i++) {
				if (ary[i]=='?') ary[i]='.';
			}
		}
//		System.out.println(Arrays.toString(ary));
		String ans=new String(ary);
		System.out.println(ans);
	}
}
/*
4 2
o???

5 2
?????

7 3
.o???o.
*/
/*
7 3
o?.???o

4 2
oo??
*/
