# Main01
n,sを読み込む。
List<Integer> la,lbを用意し、s.charAt(i)=='0'はla、それ以外はlbに追加し、la,lbをソートする。
lbのサイズが0は、nを出力する。
ans=0を用意し、iをlbのサイズ回までループする。
x=lb.get(i)を得て、laからxをバイナリーサーチし、idx<0ならばidx=~idxし、cnt=idx+lb.size()-iを計算する。
cntの最小値をansに更新する。
ループが終了したら、ansを出力する。
WA12個。

# Main02
Main01を元に、xでバイナリーサーチして、xがヒットした場合、xは含めてはいけないため、
idx<0はidx=~idx-1とする。（左端は-1）
idx>=0 && la.get(idx)==xのときidx--する。
cnt=idx+1+lb.size()-iを計算する。
TLE1個。

# Main
Main02を元に、idx--でループしないため、バイナリーサーチにComparatorを指定し、
o1.intValue()<o2.intValue() ? -1 : 1にする。（o1==o2のときに0を返さない）

# Main\_fix
Mainを元に、Main02で追加したidx--するループを削除。

