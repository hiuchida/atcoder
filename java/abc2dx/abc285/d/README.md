# Main
nを読み込み、String[] aryに読み込む。
TreeMap<String,String> mapst,mapts、TreeMap<String,Integer> mapを用意し、
mapstに(s,t)を追加、maptsに(t,s)を追加、mapに(s,i)を追加する。

boolean[] flagを用意する。
iを0からn-1までループし、flag[i]の場合スキップする。
start=ary[i]、cur=ary[i]を用意し、無限ループし、
prev=mapts.get(cur)を入れ、prev==nullの場合中断する。
prev.equals(start)の場合ng。
cur=prev、nxtidx=map.get(cur)を入れ、flag[nxtidx]の場合中断する。
flag[nxtidx]=trueを入れる。

ループ終了したら、flag[i]=trueを入れる。

ループ終了したらok。
