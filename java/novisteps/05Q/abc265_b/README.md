# Main
n,m,tを読み込み、int[] aryに読み込む。
TreeMap<Integer,Integer> mapに読み込む。
iを0からn-2までループし、t<=ary[i]のときng。
t-=ary[i]を入れ、
v=map.get(i+2)を得て、v==nullのときv=0を入れて、t+=vを加える。
ループが終了したらok。
