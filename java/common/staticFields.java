	static final long M=998244353;

	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	
	static final int[] DY = { 1, 0,-1, 0 }; //N,E,S,W
	static final int[] DX = { 0, 1, 0,-1 }; //N,E,S,W
	
	//Knight Move
	static final int[] DY = { 2, 1,-1,-2,-2,-1, 1, 2}; //1,2,4,5,7,8,10,11
	static final int[] DX = { 1, 2, 2, 1,-1,-2,-2,-1}; //1,2,4,5,7,8,10,11
	
	static TreeSet<Integer> set = new TreeSet<>();
	static TreeMap<Integer,Integer> map=new TreeMap<>();
	static TreeMap<Long,Integer> map=new TreeMap<>();
