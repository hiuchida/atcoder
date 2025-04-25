import java.util.*;
public class Main {
	static int q=20;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for (int i=1; i<=1; i++) {
			int rc=query(sc, i+1);
			if (rc==1) {
				System.out.println("! "+i);
				System.exit(0);
			}
			rc=query(sc, n-i);
			if (rc==0) {
				System.out.println("! "+(n-i));
				System.exit(0);
			}
		}
		Random r=new Random();
		while (q>1) {
			int idx=r.nextInt(n-3)+2;
			int rc1=query(sc, idx);
			int rc2=query(sc, idx+1);
			if (rc1!=rc2) {
				System.out.println("! "+idx);
				System.exit(0);
			}
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
