	static final long M=998244353;  //10^9-1755647
	static final long M=1000000000; //10^9
	static final long M=1000000007; //10^9+7

	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	
	static final int[] DY = { 1, 0,-1, 0 }; //N,E,S,W
	static final int[] DX = { 0, 1, 0,-1 }; //N,E,S,W
	
	static final int[] DY = { -1, 1, 0, 0, -1,-1, 1, 1, }; //U,D,L,R, UL,UR,DL,DR
	static final int[] DX = {  0, 0,-1, 1, -1, 1,-1, 1, }; //U,D,L,R, UL,UR,DL,DR
	
	//Hex Move
	static final int[] DY = { -1,-1, 0, 0, 1, 1, }; //DL,DR,L,R,UL,UR
	static final int[] DX = { -1, 0,-1, 1, 0, 1, }; //DL,DR,L,R,UL,UR
	
	//Knight Move
	static final int[] DY = { 2, 1,-1,-2,-2,-1, 1, 2}; //1,2,4,5,7,8,10,11
	static final int[] DX = { 1, 2, 2, 1,-1,-2,-2,-1}; //1,2,4,5,7,8,10,11
	
	static final int[] DY = {
			-1,-1, 1, 1, //UL,UR,DL,DR
			-2,-2,-2, 2, 2, 2, //2U,2UL,2UR,2D,2DL,2DR
			 0,-1, 1, 0,-1, 1, //2L,2LU,2LD,2R,2RU,2RD
			-3, 3, 0, 0, //3U,3D,3L,3R
			};
	static final int[] DX = {
			-1, 1,-1, 1, //UL,UR,DL,DR
			 0,-1, 1, 0,-1, 1, //2U,2UL,2UR,2D,2DL,2DR
			-2,-2,-2, 2, 2, 2,  //2L,2LU,2LD,2R,2RU,2RD
			 0, 0,-3, 3, //3U,3D,3L,3R
			};
	
	static TreeSet<Integer> set = new TreeSet<>();
	static TreeMap<Integer,Integer> map=new TreeMap<>();
	static TreeMap<Long,Integer> map=new TreeMap<>();
