# Main
nを読み込み、StrList sl、IntList il、sum = 0を用意する。
sl.add(sa[0])、il.add(pint(sa[1]))を追加し、sum += pint(sa[1])を加える。
ilをソートする。
mx = il.getVal(0)を入れ、
mx * 2 > sumの場合、sl.get(il.getIdx(0))を出力する。
それ以外の場合、"atcoder"を出力する。

# Main\_fix
合計と最大値だけ保持すれば出力できる。

nを読み込み、maxS=""、maxP=-1、sum=0を用意する。
n回ループし、s,pを読み込み、
maxP<pの場合、maxS=s、maxP=pを更新する。
sum+=pを加える。

maxP>sum/2の場合、maxSを出力する。
それ以外の場合、"atcoder"を出力する。

