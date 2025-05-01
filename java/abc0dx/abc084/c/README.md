# Main
nを読み込み、int[] ac,as,afに読み込む。
iを0からn-1回ループし、ans=as[i]を用意し、
jをiからn-2までループし、
kを0から無限ループし、ans<=as[j]+k\*af[j]の場合、ans=as[j]+k\*af[j]を入れて中断する。
ans+=ac[j]を加える。
ansを出力する。
0を出力する。
