import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int n=s.length();
		char[] ary=s.toCharArray();
		int[] cnt=new int[n];
		int[] cnt2=new int[n];
		Arrays.fill(cnt, 1);
		int loop=n;
		if (loop%2==1) loop++;
		for (int j=0; j<loop; j++) {
			boolean bdone=true;
			Arrays.fill(cnt2, 0);
			for (int i=0; i<n; i++) {
				if (cnt[i]>0) {
					if (ary[i]=='L') cnt2[i-1]+=cnt[i];
					else cnt2[i+1]+=cnt[i];
					bdone=false;
				}
			}
			System.arraycopy(cnt2, 0, cnt, 0, n);
//			System.out.println(Arrays.toString(cnt));
			if (bdone) break;
		}
		for (int i=0; i<n; i++) {
			System.out.print(cnt[i]+" ");
		}
		System.out.println();
	}
}
/*
RRLRL

RRLLLLRLRRLL

RRRLLRLLRRRLLLLL
*/
