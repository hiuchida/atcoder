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
		while (true) {
			if (lt==rt) {
				System.out.println("! "+lt);
				System.exit(0);
			}
			int mid=(lt+rt)/2;
			int rc=query(sc, mid);
			ary[mid]=rc;
			if (rc==0) lt=mid;
			else rt=mid;
		}
	}
	static int query(Scanner sc, int n) {
		q--;
		System.out.println("? "+n);
		System.out.flush();
		int rc=sc.nextInt();
		return rc;
	}
}
/*



*/
