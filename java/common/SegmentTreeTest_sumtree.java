import java.util.*;
public class Main {
	public static void main(String[] args) {
		main0(100);
//		main1(1);
//		main1(10);
//		main1(20);
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
	static class SegmentTreeNode { //SegmentTree_sumtree20250502
		String id;
		int value;
		SegmentTreeNode left;
		SegmentTreeNode right;
		SegmentTreeNode(String id, int value) {
			this.id=id;
			this.value=value;
		}
		@Override
		public String toString() {
			String l=(left!=null) ? left.id : "nul";
			String r=(right!=null) ? right.id : "nul";
			return "("+value+" : "+id+","+l+","+r+")";
		}
	}
	static class SegmentTree { //SegmentTree_sumtree20250502
		SegmentTreeNode root;
		List<SegmentTreeNode> list=new ArrayList<>();
		int siz;
		int def;
		int inf;
		SegmentTree(int n, int def, int inf) {
			this.root = new SegmentTreeNode("0_"+n, def);
			list.add(root);
			this.siz = n;
			this.def = def;
			this.inf = inf;
		}
		int add(int a, int b) {
			return a+b;
		}
		void update(int i, int value) {
			update(i, value, root, 0, siz);
		}
		int get(int i) {
			return get(i, root, 0, siz);
		}
		int query(int lt, int rt) {
			return query(lt, rt, root, 0, siz);
		}
		void update(int i, int value, SegmentTreeNode node, int a, int b) {
			if (a+1==b) {
				node.value=value;
				return;
			}
			int mid=(a+b)/2;
			if (i<mid) {
				if (node.left==null) {
					node.left=new SegmentTreeNode(""+a+"_"+mid, def);
					list.add(node.left);
				}
				update(i, value, node.left, a, mid);
			} else {
				if (node.right==null) {
					node.right=new SegmentTreeNode(""+mid+"_"+b, def);
					list.add(node.right);
				}
				update(i, value, node.right, mid, b);
			}
			int vl=def;
			int vr=def;
			if (node.left!=null) vl=node.left.value;
			if (node.right!=null) vr=node.right.value;
			node.value=add(vl, vr);
		}
		int get(int i, SegmentTreeNode node, int a, int b) {
			if (node==null) return def;
			if (a+1==b) return node.value;
			int mid=(a+b)/2;
			if (i<mid) {
				return get(i, node.left, a, mid);
			} else {
				return get(i, node.right, mid, b);
			}
		}
		int query(int lt, int rt, SegmentTreeNode node, int a, int b) {
			if (node==null || b <= lt || rt <= a) {
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
(105 : 0_1400,0_700,700_1400)
(28 : 0_700,0_350,350_700)
(10 : 0_350,0_175,175_350)
(3 : 0_175,0_87,87_175)
(1 : 0_87,0_43,nul)
(1 : 0_43,0_21,nul)
(1 : 0_21,0_10,nul)
(1 : 0_10,0_5,nul)
(1 : 0_5,0_2,nul)
(1 : 0_2,0_1,nul)
(1 : 0_1,nul,nul)
(2 : 87_175,87_131,nul)
(2 : 87_131,87_109,nul)
(2 : 87_109,nul,98_109)
(2 : 98_109,98_103,nul)
(2 : 98_103,nul,100_103)
(2 : 100_103,100_101,nul)
(2 : 100_101,nul,nul)
(7 : 175_350,175_262,262_350)
(3 : 175_262,175_218,nul)
(3 : 175_218,nul,196_218)
(3 : 196_218,196_207,nul)
(3 : 196_207,196_201,nul)
(3 : 196_201,nul,198_201)
(3 : 198_201,nul,199_201)
(3 : 199_201,nul,200_201)
(3 : 200_201,nul,nul)
(4 : 262_350,262_306,nul)
(4 : 262_306,nul,284_306)
(4 : 284_306,nul,295_306)
(4 : 295_306,nul,300_306)
(4 : 300_306,300_303,nul)
(4 : 300_303,300_301,nul)
(4 : 300_301,nul,nul)
(18 : 350_700,350_525,525_700)
(11 : 350_525,350_437,437_525)
(5 : 350_437,nul,393_437)
(5 : 393_437,393_415,nul)
(5 : 393_415,393_404,nul)
(5 : 393_404,nul,398_404)
(5 : 398_404,398_401,nul)
(5 : 398_401,nul,399_401)
(5 : 399_401,nul,400_401)
(5 : 400_401,nul,nul)
(6 : 437_525,nul,481_525)
(6 : 481_525,481_503,nul)
(6 : 481_503,nul,492_503)
(6 : 492_503,nul,497_503)
(6 : 497_503,nul,500_503)
(6 : 500_503,500_501,nul)
(6 : 500_501,nul,nul)
(7 : 525_700,525_612,nul)
(7 : 525_612,nul,568_612)
(7 : 568_612,nul,590_612)
(7 : 590_612,590_601,nul)
(7 : 590_601,nul,595_601)
(7 : 595_601,nul,598_601)
(7 : 598_601,nul,599_601)
(7 : 599_601,nul,600_601)
(7 : 600_601,nul,nul)
(77 : 700_1400,700_1050,1050_1400)
(38 : 700_1050,700_875,875_1050)
(17 : 700_875,700_787,787_875)
(8 : 700_787,700_743,nul)
(8 : 700_743,700_721,nul)
(8 : 700_721,700_710,nul)
(8 : 700_710,700_705,nul)
(8 : 700_705,700_702,nul)
(8 : 700_702,700_701,nul)
(8 : 700_701,nul,nul)
(9 : 787_875,787_831,nul)
(9 : 787_831,787_809,nul)
(9 : 787_809,nul,798_809)
(9 : 798_809,798_803,nul)
(9 : 798_803,nul,800_803)
(9 : 800_803,800_801,nul)
(9 : 800_801,nul,nul)
(21 : 875_1050,875_962,962_1050)
(10 : 875_962,875_918,nul)
(10 : 875_918,nul,896_918)
(10 : 896_918,896_907,nul)
(10 : 896_907,896_901,nul)
(10 : 896_901,nul,898_901)
(10 : 898_901,nul,899_901)
(10 : 899_901,nul,900_901)
(10 : 900_901,nul,nul)
(11 : 962_1050,962_1006,nul)
(11 : 962_1006,nul,984_1006)
(11 : 984_1006,nul,995_1006)
(11 : 995_1006,nul,1000_1006)
(11 : 1000_1006,1000_1003,nul)
(11 : 1000_1003,1000_1001,nul)
(11 : 1000_1001,nul,nul)
(39 : 1050_1400,1050_1225,1225_1400)
(25 : 1050_1225,1050_1137,1137_1225)
(12 : 1050_1137,nul,1093_1137)
(12 : 1093_1137,1093_1115,nul)
(12 : 1093_1115,1093_1104,nul)
(12 : 1093_1104,nul,1098_1104)
(12 : 1098_1104,1098_1101,nul)
(12 : 1098_1101,nul,1099_1101)
(12 : 1099_1101,nul,1100_1101)
(12 : 1100_1101,nul,nul)
(13 : 1137_1225,nul,1181_1225)
(13 : 1181_1225,1181_1203,nul)
(13 : 1181_1203,nul,1192_1203)
(13 : 1192_1203,nul,1197_1203)
(13 : 1197_1203,nul,1200_1203)
(13 : 1200_1203,1200_1201,nul)
(13 : 1200_1201,nul,nul)
(14 : 1225_1400,1225_1312,nul)
(14 : 1225_1312,nul,1268_1312)
(14 : 1268_1312,nul,1290_1312)
(14 : 1290_1312,1290_1301,nul)
(14 : 1290_1301,nul,1295_1301)
(14 : 1295_1301,nul,1298_1301)
(14 : 1298_1301,nul,1299_1301)
(14 : 1299_1301,nul,1300_1301)
(14 : 1300_1301,nul,nul)

end of main1Array 13 102
end of main1Segment 363 2
end of main1Array 126 1717
end of main1Segment 3455 3
end of main1Array 234 3523
end of main1Segment 8664 2
*/
