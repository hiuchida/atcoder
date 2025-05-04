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
