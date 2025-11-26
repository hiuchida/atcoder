/*
ノルム(norm)

Lpノルム：絶対値のp乗の和のp乗根

L1ノルム：絶対値の和
マンハッタン距離(Manhattan distance)：幾何学的な別名
タクシーキャブ・ノルム(taxicab norm)：幾何学的な別名
Lassoペナルティ(Lasso penalty)：機械学習の文脈でのL1正則化項の名称
最小絶対偏差(least absolute deviations (LAD))：誤差関数としてL1ノルムを用いる手法
2次元平面の一定値の集合：ひし形(Rhombus)
3次元平面の一定値の集合：正八面体(Regular Octahedron)

L2ノルム：二乗和の平方根
ユークリッド・ノルム(Euclidean norm)：幾何学的な別名
ユークリッド距離(Euclidean distance)：距離として扱う場合
二乗誤差(squared error)：損失関数としてL2ノルムの二乗を用いる場合
Ridgeペナルティ(Ridge penalty)：機械学習の文脈でのL2正則化項の名称
二乗平均平方根(root-mean-square (RMS))：統計や信号処理で使われるL2ノルムに類似した概念
2次元平面の一定値の集合：円(Circle)
3次元平面の一定値の集合：球(Sphere)

L3ノルム：絶対値の3乗の和の3乗根
2次元平面の一定値の集合：円より角ばった形、角の丸い正方形
3次元平面の一定値の集合：球より角ばった形、角の丸い立方体

L∞ノルム(L-infinity norm)：lim(p→∞) 最大値
チェビシェフ距離(Chebyshev distance)：ロシアの数学者パフヌティ・チェビシェフ
キング・ムーブ距離(King's Move Distance)：チェスのキングの動き
2次元平面の一定値の集合：正方形(Square)
3次元平面の一定値の集合：立方体(Cube)、正六面体(Regular Hexahedron)

*/

//abc035_b,abc057_b,abc086_c,abc295_b:
static int calcL1(int x1, int y1, int x2, int y2) { //x1,y1からx2,y2までのマンハッタン距離
	int dx=x1-x2;
	int dy=y1-y2;
	return Math.abs(dx)+Math.abs(dy);
}

