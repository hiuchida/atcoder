import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		Test[] tests=new Test[m];
		for (int i=0; i<m; i++) {
			Test t=new Test();
			int c = sc.nextInt();
			for (int j=0; j<c; j++) {
				int a = sc.nextInt();
				a--;
				t.set.add(a);
			}
			String r = sc.next();
			t.res=r;
			tests[i]=t;
		}
		int ans=0;
		for (int h=0; h< 1<<n; h++) {
			boolean bOk=false;
			boolean bNg=false;
			for (int i=0; i<m; i++) {
				Test t=tests[i];
				int cnt=0;
				for (int a : t.set) {
					int mask=1 << a;
					if ((h&mask)>0) {
						cnt++;
					}
				}
				String r = t.res;
//				System.out.println(h+": "+cnt+" "+r);
				if (cnt>=k) {
					if ("o".equals(r)) bOk=true;
					else bNg=true;
				} else {
					if ("x".equals(r)) bOk=true;
					else bNg=true;
				}
//				System.out.println(h+": "+cnt+" "+bOk+" "+bNg);
			}
			if (bOk && !bNg) ans++;
//			System.out.println(h+": "+bOk+" "+bNg);
		}
		System.out.println(ans);
	}
	static class Test {
		TreeSet<Integer> set=new TreeSet<>();
		String res;
	}
}
/*
3 2 2
3 1 2 3 o
2 2 3 x

4 5 3
3 1 2 3 o
3 2 3 4 o
3 3 4 1 o
3 4 1 2 o
4 1 2 3 4 x

11 4 9
10 1 2 3 4 5 6 7 8 9 10 o
11 1 2 3 4 5 6 7 8 9 10 11 o
10 11 10 9 8 7 6 5 4 3 2 x
10 11 9 1 4 3 7 5 6 2 10 x
*/
