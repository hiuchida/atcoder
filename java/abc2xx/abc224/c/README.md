# Main
3重ループして、任意の3点について、直線上にある場合を判定する。
傾きが無限大となるx座標がすべて同じ場合をfalseにする。
傾きを比較する際にdy/dxの結果としてdoubleを使わずに、
dy1/dx1=dy2/dx2より、dx2dy1=dx1dy2を判定する。