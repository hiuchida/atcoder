import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		String[] ary=new String[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.next();
		}
//		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		long ans=0;
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				String s1=ary[i];
				String s2=ary[j];
				boolean bok=true;
				for (int k=0; k<s1.length(); k++) {
					if (s1.charAt(k)=='x' && s2.charAt(k)=='x') bok=false;
				}
				if (bok) ans++;
			}
		}
		System.out.println(ans);
	}
}
/*
5 5
ooooo
oooxx
xxooo
oxoxo
xxxxx

3 2
ox
xo
xx

2 4
xxxx
oxox
*/
