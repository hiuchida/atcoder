import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		List<Integer> la=new ArrayList<>();
		List<Integer> lb=new ArrayList<>();
		for (int i=0; i<n; i++) {
			int w=sc.nextInt();
			if (s.charAt(i)=='0') la.add(w);
			else lb.add(w);
		}
		Collections.sort(la);
		Collections.sort(lb);
//		System.out.println(la);
//		System.out.println(lb);
		int ans=0;
		if (lb.size()==0) {
			System.out.println(n);
			System.exit(0);
		}
		for (int i=0; i<lb.size(); i++) {
			int x=lb.get(i);
			int idx=Collections.binarySearch(la, x);
			if (idx<0) idx=~idx-1;
			while (idx>=0 && la.get(idx)==x) idx--;
			int cnt=idx+1+lb.size()-i;
			ans=Math.max(ans, cnt);
		}
		System.out.println(ans);
	}
}
/*
5
10101
60 45 30 40 80

3
000
1 2 3

5
10101
60 50 50 50 60
*/
/*
3
111
1 2 3
*/
