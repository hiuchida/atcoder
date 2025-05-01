# Main
nを読み込み、int[] aryに読み込む。
boolean[] flag、pos=1、ans=0を用意し、無限ループする。
pos==2ならば中断する。
flag[pos]ならば、-1を出力して、終了する。
ans++、flag[pos]=true、pos=ary[pos]を入れる。
ループ終了したら、ansを出力する。
