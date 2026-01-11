import java.util.*;
public class Main {
	public static void main(String[] args) {
//		main0(100);
		main1(1);
		main1(10);
		main1(20);
	}
	public static void main0(int k) {
		int n = 14;
		int[] ary=new int[k*n];
		for (int i=0; i<n; i++) {
//			int v=(i * 3) % n + 1;
			int v=i+1;
			ary[k*i]=v;
		}
		System.out.println(Arrays.toString(ary));
		SegmentTree st = new SegmentTree(k*n, 0, 0);
		System.out.println(SegmentTree.nil);
		for (int i=0; i<n; i++) {
			st.update(k*i, ary[k*i]);
		}
		for (int i=0; i<n; i++) {
			int v=st.get(k*i);
			if (v!=ary[k*i]) {
				System.out.println(k*i+" "+v+" "+ary[k*i]);
			}
		}
		System.out.println(st);
		for (int j=0; j<n; j++) {
			for (int i=j; i<=n; i++) {
				int v=st.query(k*j, k*i);
				int sum=0;
				for (int a=k*j; a<k*i; a++) sum+=ary[a];
				if (v!=sum) {
					System.out.println(k*j+" "+k*i+" "+v+" "+sum);
				}
			}
		}
		System.out.println("end of main0");
	}
	public static void main1(int n) {
		final int N=n*1024*1024;
		final int C=2048/n;
		final int Q=1000;
		Random r=new Random();
		int[][] query=new int[Q][2];
		for (int i=0; i<Q; i++) {
			while (true) {
				int p=r.nextInt(N);
				int q=r.nextInt(N);
				if (p<q) {
					query[i][0]=p;
					query[i][1]=q;
					break;
				}
			}
		}
		long seed=System.currentTimeMillis();
		int[] ans1a=main1Array(N, C, query, seed);
		int[] ans1s=main1Segment(N, C, query, seed);
		for (int i=0; i<ans1a.length; i++) {
			if (ans1a[i]!=ans1s[i]) {
				System.out.println(i+" "+ans1a[i]+" "+ans1s[i]);
			}
		}
	}
	public static int[] main1Array(final int N, final int C, int[][] query, long seed) {
		Random r=new Random(seed);
		long st1=System.currentTimeMillis();
		int[] ary=new int[N];
		for (int i=0; i<N; i++) {
			ary[i]=r.nextInt(C);
		}
		long st2=System.currentTimeMillis();
		int[] ans=new int[query.length];
		for (int i=0; i<query.length; i++) {
			int sum=0;
			for (int j=query[i][0]; j<query[i][1]; j++) {
				sum=sum + ary[j];
			}
			ans[i]=sum;
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of main1Array "+tm2+" "+tm3);
		return ans;
	}
	public static int[] main1Segment(final int N, final int C, int[][] query, long seed) {
		Random r=new Random(seed);
		long st1=System.currentTimeMillis();
		int[] ary=new int[N];
		for (int i=0; i<N; i++) {
			ary[i]=r.nextInt(C);
		}
		SegmentTree st=new SegmentTree(N, 0, 0);
		for (int i=0; i<N; i++) {
			st.update(i, ary[i]);
		}
		long st2=System.currentTimeMillis();
		int[] ans=new int[query.length];
		for (int i=0; i<query.length; i++) {
			ans[i]=st.query(query[i][0], query[i][1]);
		}
		long st3=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		System.out.println("end of main1Segment "+tm2+" "+tm3);
		return ans;
	}
	static class SegmentTreeNode { //SegmentTree_sumtree20250504
		String id;
		int value;
		SegmentTreeNode left;
		SegmentTreeNode right;
		SegmentTreeNode(String id, int value) {
			this.id=id;
			this.value=value;
			this.left=SegmentTree.nil;
			this.right=SegmentTree.nil;
		}
		@Override
		public String toString() {
			String l=(left!=null) ? left.id : "null";
			String r=(right!=null) ? right.id : "null";
			return "("+value+" : "+id+","+l+","+r+")";
		}
	}
	static class SegmentTree { //SegmentTree_sumtree20250504
		static final SegmentTreeNode nil=new SegmentTreeNode("nil", 0);
		SegmentTreeNode root;
		List<SegmentTreeNode> list=new ArrayList<>();
		int siz;
		int def;
		int inf;
		SegmentTree(int n, int def, int inf) {
			this.root = nil;
			this.siz = n;
			this.def = def;
			this.inf = inf;
		}
		int add(int a, int b) {
			return a+b;
		}
		void update(int i, int value) {
			root=update(i, value, root, 0, siz);
		}
		int get(int i) {
			return get(i, root, 0, siz);
		}
		int query(int lt, int rt) {
			return query(lt, rt, root, 0, siz);
		}
		SegmentTreeNode update(int i, int value, SegmentTreeNode node, int a, int b) {
			if (node==nil) {
				node=new SegmentTreeNode(""+a+"_"+b, def);
				list.add(node);
			}
			if (a+1==b) {
				node.value=value;
				return node;
			}
			int mid=(a+b)/2;
			if (i<mid) {
				node.left=update(i, value, node.left, a, mid);
			} else {
				node.right=update(i, value, node.right, mid, b);
			}
			int vl=def;
			int vr=def;
			if (node.left!=null) vl=node.left.value;
			if (node.right!=null) vr=node.right.value;
			node.value=add(vl, vr);
			return node;
		}
		int get(int i, SegmentTreeNode node, int a, int b) {
			if (node==nil) return def;
			if (a+1==b) return node.value;
			int mid=(a+b)/2;
			if (i<mid) {
				return get(i, node.left, a, mid);
			} else {
				return get(i, node.right, mid, b);
			}
		}
		int query(int lt, int rt, SegmentTreeNode node, int a, int b) {
			if (node==nil || b <= lt || rt <= a) {
				return inf;
			}
			if (lt <= a && b <= rt) {
				return node.value;
			}
			int mid=(a+b)/2;
			int vl=query(lt, rt, node.left, a, mid);
			int vr=query(lt, rt, node.right, mid, b);
			return add(vl, vr);
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (SegmentTreeNode node : list) {
				sb.append(node).append(System.getProperty("line.separator"));
			}
			return sb.toString();
		}
	}
}
/*
(0 : nil,null,null)
(105 : 0_1400,0_700,700_1400)
(28 : 0_700,0_350,350_700)
(10 : 0_350,0_175,175_350)
(3 : 0_175,0_87,87_175)
(1 : 0_87,0_43,nil)
(1 : 0_43,0_21,nil)
(1 : 0_21,0_10,nil)
(1 : 0_10,0_5,nil)
(1 : 0_5,0_2,nil)
(1 : 0_2,0_1,nil)
(1 : 0_1,nil,nil)
(2 : 87_175,87_131,nil)
(2 : 87_131,87_109,nil)
(2 : 87_109,nil,98_109)
(2 : 98_109,98_103,nil)
(2 : 98_103,nil,100_103)
(2 : 100_103,100_101,nil)
(2 : 100_101,nil,nil)
(7 : 175_350,175_262,262_350)
(3 : 175_262,175_218,nil)
(3 : 175_218,nil,196_218)
(3 : 196_218,196_207,nil)
(3 : 196_207,196_201,nil)
(3 : 196_201,nil,198_201)
(3 : 198_201,nil,199_201)
(3 : 199_201,nil,200_201)
(3 : 200_201,nil,nil)
(4 : 262_350,262_306,nil)
(4 : 262_306,nil,284_306)
(4 : 284_306,nil,295_306)
(4 : 295_306,nil,300_306)
(4 : 300_306,300_303,nil)
(4 : 300_303,300_301,nil)
(4 : 300_301,nil,nil)
(18 : 350_700,350_525,525_700)
(11 : 350_525,350_437,437_525)
(5 : 350_437,nil,393_437)
(5 : 393_437,393_415,nil)
(5 : 393_415,393_404,nil)
(5 : 393_404,nil,398_404)
(5 : 398_404,398_401,nil)
(5 : 398_401,nil,399_401)
(5 : 399_401,nil,400_401)
(5 : 400_401,nil,nil)
(6 : 437_525,nil,481_525)
(6 : 481_525,481_503,nil)
(6 : 481_503,nil,492_503)
(6 : 492_503,nil,497_503)
(6 : 497_503,nil,500_503)
(6 : 500_503,500_501,nil)
(6 : 500_501,nil,nil)
(7 : 525_700,525_612,nil)
(7 : 525_612,nil,568_612)
(7 : 568_612,nil,590_612)
(7 : 590_612,590_601,nil)
(7 : 590_601,nil,595_601)
(7 : 595_601,nil,598_601)
(7 : 598_601,nil,599_601)
(7 : 599_601,nil,600_601)
(7 : 600_601,nil,nil)
(77 : 700_1400,700_1050,1050_1400)
(38 : 700_1050,700_875,875_1050)
(17 : 700_875,700_787,787_875)
(8 : 700_787,700_743,nil)
(8 : 700_743,700_721,nil)
(8 : 700_721,700_710,nil)
(8 : 700_710,700_705,nil)
(8 : 700_705,700_702,nil)
(8 : 700_702,700_701,nil)
(8 : 700_701,nil,nil)
(9 : 787_875,787_831,nil)
(9 : 787_831,787_809,nil)
(9 : 787_809,nil,798_809)
(9 : 798_809,798_803,nil)
(9 : 798_803,nil,800_803)
(9 : 800_803,800_801,nil)
(9 : 800_801,nil,nil)
(21 : 875_1050,875_962,962_1050)
(10 : 875_962,875_918,nil)
(10 : 875_918,nil,896_918)
(10 : 896_918,896_907,nil)
(10 : 896_907,896_901,nil)
(10 : 896_901,nil,898_901)
(10 : 898_901,nil,899_901)
(10 : 899_901,nil,900_901)
(10 : 900_901,nil,nil)
(11 : 962_1050,962_1006,nil)
(11 : 962_1006,nil,984_1006)
(11 : 984_1006,nil,995_1006)
(11 : 995_1006,nil,1000_1006)
(11 : 1000_1006,1000_1003,nil)
(11 : 1000_1003,1000_1001,nil)
(11 : 1000_1001,nil,nil)
(39 : 1050_1400,1050_1225,1225_1400)
(25 : 1050_1225,1050_1137,1137_1225)
(12 : 1050_1137,nil,1093_1137)
(12 : 1093_1137,1093_1115,nil)
(12 : 1093_1115,1093_1104,nil)
(12 : 1093_1104,nil,1098_1104)
(12 : 1098_1104,1098_1101,nil)
(12 : 1098_1101,nil,1099_1101)
(12 : 1099_1101,nil,1100_1101)
(12 : 1100_1101,nil,nil)
(13 : 1137_1225,nil,1181_1225)
(13 : 1181_1225,1181_1203,nil)
(13 : 1181_1203,nil,1192_1203)
(13 : 1192_1203,nil,1197_1203)
(13 : 1197_1203,nil,1200_1203)
(13 : 1200_1203,1200_1201,nil)
(13 : 1200_1201,nil,nil)
(14 : 1225_1400,1225_1312,nil)
(14 : 1225_1312,nil,1268_1312)
(14 : 1268_1312,nil,1290_1312)
(14 : 1290_1312,1290_1301,nil)
(14 : 1290_1301,nil,1295_1301)
(14 : 1295_1301,nil,1298_1301)
(14 : 1298_1301,nil,1299_1301)
(14 : 1299_1301,nil,1300_1301)
(14 : 1300_1301,nil,nil)

end of main1Array 13 101
end of main1Segment 388 3
end of main1Array 112 1697
end of main1Segment 3745 2
end of main1Array 223 3187
end of main1Segment 9095 2
*/
