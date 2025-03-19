# Main01
ary[i]のsumを計算する。
iをn-1から1までループする。
sumからary\[i\]を引く。
baseにary\[i\]を越える10^kを計算する。
xにsum\*baseを計算する。
yにary\[i\]\*iを計算する。
x+yをansに加える。
WA19個。

# Main
ary[i]のsumを計算する。
iをn-1から1までループする。
sumからary\[i\]を引く。
baseにary\[i\]を越える10^kを計算する。
xにsum\*baseを計算する。
yにary\[i\]\*iを計算する。
x+yをansに加える。

sum\*baseがオーバーフローするので、sumとbaseのそれぞれでMの余りを取る。
