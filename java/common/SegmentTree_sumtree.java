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
