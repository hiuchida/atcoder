import java.util.*;
public class Main {
	static int q=20;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n+1];
		Arrays.fill(ary, -1);
		int lt=1;
		int rt=n;
		ary[lt]=0;
		ary[rt]=1;
		while (lt<rt) {
			int mid=(lt+rt)/2;
			int rc=query(sc, mid);
			ary[mid]=rc;
			if (rc==0) lt=mid;
			else rt=mid;
		}
		if (ary[lt]<0) ary[lt]=query(sc, lt);
		if (ary[rt]<0) ary[rt]=query(sc, rt);
		if (ary[lt]!=ary[lt+1]) {
			System.out.println("! "+lt);
			System.exit(0);
		}
		while (true) ;
//		new RuntimeException();
	}
	static int query(Scanner sc, int n) {
		if (q==0) new RuntimeException();
		q--;
		System.out.println("? "+n);
		System.out.flush();
		int rc=sc.nextInt();
		return rc;
	}
}
/*



*/
