# Main
n,tを読み込み、int[] aryに読み込む。
ans=0、pre=-1を用意し、iを0からn回ループし、
a=ary[i]を入れ、pre<=aの場合ans+=t、それ以外はans+=t-(pre-a)を加える。
pre=a+tを入れる。
ansを出力する。
