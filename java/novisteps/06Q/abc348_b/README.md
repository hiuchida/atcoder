# Main
Point[] aryに読み込む。
iを0からn-1までループし、jも0からn-1までループする。
int idx=-1とlong val=-1を用意する。
i!=jのときに、ary\[i\]とary\[j\]の距離xを計算し、
x>valのときidxとvalを更新する。
ループが終了したら、idx+1を出力する。
