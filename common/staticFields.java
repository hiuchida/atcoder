	//Doubling size
	static final int m=17; //2^17=131,072
	static final int m=18; //2^18=262,144
	static final int m=30; //2^30=1,073,741,824
	static final int m=60; //2^60=1,152,921,504,606,846,976

	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	static final String DS = "UDLR";
	
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
	
	//DXX DXY  1 0  1  0  -1 0  -1  0  0 1   0 1  0 -1   0 -1
	//DYX DYY  0 1  0 -1   0 1   0 -1  1 0  -1 0  1  0  -1  0
	static int[] DXX= { 1, 1,-1,-1, 0, 0, 0, 0, };
	static int[] DXY= { 0, 0, 0, 0, 1, 1,-1,-1, };
	static int[] DYX= { 0, 0, 0, 0, 1,-1, 1,-1, };
	static int[] DYY= { 1,-1, 1,-1, 0, 0, 0, 0, };
	
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
