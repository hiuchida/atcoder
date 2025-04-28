import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n+1];
		int[] idx=new int[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=sc.nextInt();
			idx[ary[i]]=i;
		}
		int[] pre=new int[n+2];
		int[] nxt=new int[n+2];
		for (int i=0; i<=n+1; i++) {
			pre[i]=i-1;
			nxt[i]=i+1;
		}
//		System.out.println("ary: "+Arrays.toString(ary));
//		System.out.println("idx: "+Arrays.toString(idx));
		long ans=0;
		for (int i=n; i>0; i--) {
			int p=idx[i];
//			System.out.println("pre: "+Arrays.toString(pre)+" "+i+"->"+p);
//			System.out.println("nxt: "+Arrays.toString(nxt)+" "+i+"->"+p);
			ans+=(long)i*(p-pre[p])*(nxt[p]-p);
			pre[nxt[p]]=pre[p];
			nxt[pre[p]]=nxt[p];
		}
//		System.out.println("pre: "+Arrays.toString(pre));
//		System.out.println("nxt: "+Arrays.toString(nxt));
		System.out.println(ans);
	}
}
/*
3
2 1 3

4
1 3 2 4

8
5 4 8 1 2 6 7 3
*/
