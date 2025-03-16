import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		TreeSet<Integer> set1=new TreeSet<>();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			if (s.charAt(i) == '1') {
				ary[i]=1;
				set1.add(i);
			}
		}
		int lt=set1.first();
		int rt=set1.last();
		Deque<Integer> que0=new ArrayDeque<>();
		for (int i=0; i<n; i++) {
			if (lt<=i && i<=rt) {
				if (ary[i] == 0) {
					que0.offer(i);
				}
			}
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(set1);
//		System.out.println(que0);
		long ans = 0;
		while (que0.size() > 0) {
			int xlt=que0.peekFirst();
			int xrt=que0.peekLast();
			lt=set1.first();
			rt=set1.last();
			int dlt=0;
			int drt=0;
			if (lt<xlt) {
				dlt=xlt-lt;
			} else {
				que0.removeFirst();
			}
			if (xrt<rt) {
				drt=rt-xrt;
			} else {
				que0.removeLast();
			}
			if (dlt>0 || drt>0) {
				if (dlt<=drt) {
					que0.removeFirst();
					set1.remove(lt);
					set1.add(xlt);
					ary[lt]=0;
					ary[xlt]=1;
					ans+=dlt;
				} else {
					que0.removeLast();
					set1.remove(rt);
					set1.add(xrt);
					ary[rt]=0;
					ary[xrt]=1;
					ans+=drt;
				}
			}
//			System.out.println(Arrays.toString(ary));
//			System.out.println(set1);
//			System.out.println(que0);
		}
		System.out.println(ans);
	}
}
/*
7
0101001

3
100

10
0101001001
*/
/*
10
0101001101

5
01010
*/
