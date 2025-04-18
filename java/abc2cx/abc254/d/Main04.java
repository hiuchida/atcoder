import java.util.*;
public class Main {
	static Prime pr;
	//200000: 1935ms 1927ms 1923ms
	//200000: 1944ms 1941ms 1971ms //List<Integer> list = new ArrayList<>();
	//200000: 1860ms 1897ms 1890ms else break;
	//200000: 1609ms 1681ms 1596ms List<Integer> lst=new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
//		int n=200000;
		long st1=System.currentTimeMillis();
		pr=new Prime(n+1);
		long ans=0;
		for (int x=1; x<=n; x++) {
			int[] ap1=calc(x);
			int[] ap=calc2(n, x, ap1);
			long xx=(long)x*x;
			for (int i : ap) {
				long j=xx/i;
				if (j>n) continue;
				ans++;
//				System.out.println(i+","+j+" "+x+" "+xx+" ans="+ans);
			}
		}
		System.out.println(ans);
		long st2=System.currentTimeMillis();
		long tm1=st2-st1;
//		System.out.println(tm1+"ms");
	}
	static int[] calc2(int n, int x, int[] ap) {
//		List<Integer> list=new ArrayList<>();
		Set<Integer> set=new TreeSet<>();
		for (int v : ap) {
			for (int v2 : ap) {
				long v3=(long)v*v2;
//				if (v3<=n) list.add((int)v3);
				if (v3<=n) set.add((int)v3);
				else break;
			}
		}
//		System.out.println(x*x+": "+list);
//		System.out.println(x*x+": "+set);
//		int[] ans=new int[list.size()];
		int[] ans=new int[set.size()];
		int idx=0;
//		for (int v : list) {
		for (int v : set) {
			ans[idx]=v;
			idx++;
		}
		return ans;
	}
	static int[] calc(int n) {
		List<Integer> list=new ArrayList<>();
		list.add(1);
		int nn=n;
		while (n>1) {
			List<Integer> lst=new ArrayList<>();
//			Set<Integer> set=new HashSet<>(list);
			int nxt=pr.minf[n];
			int val=nxt;
			while (n%nxt==0) {
				for (int v : list) {
					lst.add(v*val);
//					set.add(v*val);
				}
				n/=nxt;
				val*=nxt;
			}
//			list=new ArrayList<>(set);
			list.addAll(lst);
		}
		Collections.sort(list);
//		System.out.println(nn+": "+list);
		int[] ans=new int[list.size()];
		for (int i=0; i<list.size(); i++) {
			ans[i]=list.get(i);
		}
		return ans;
	}
	//1,000,000,000 16678ms
	//  100,000,000  1537ms
	//   10,000,000    98ms
	//    1,000,000    17ms
	static class Prime { //Prime20250419
		int n;
		boolean[] isp;
		int[] minf;
//		List<Integer> list = new ArrayList<>();
		Prime(int n) {
			this.n = n;
			this.isp = new boolean[n];
			this.minf = new int[n];
			init();
		}
		void init() {
			isp[0] = false;
			isp[1] = false;
			for (int i=2; i<n; i++) isp[i] = true;
			minf[1] = 1;
			for (int i=2; i<n; i++) {
				if (isp[i]) {
					minf[i] = i;
//					list.add(i);
					for (int j=i*2; j<n; j+=i) {
						isp[j] = false;
						if (minf[j] == 0) minf[j] = i;
					}
				}
			}
		}
//		boolean check(long x) {
//			if (x>(long)n*n) throw new RuntimeException();
//			if (x<n) return isp[(int)x];
//			for (int i : list) {
//				if (x%i==0) {
//					return false;
//				}
//			}
//			return true;
//		}
		long count(int n) {
			long ans=1;
			while (n>1) {
				int nxt=minf[n];
				int cnt=1;
				while (n%nxt==0) {
					cnt++;
					n/=nxt;
				}
				ans*=cnt;
			}
			return ans;
		}
	}
}
/*
4

254
*/
/*
200000
*/
