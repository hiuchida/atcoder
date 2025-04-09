# Main01
l1,r1,l2,r2を読み込む。ans=0を用意する。
l1<l2かつr1>l2のとき、ans=r1-l2。
l1>=l2かつr2>l1のとき、ans=r2-l1。
ansを出力する。
WA3個。

# Main02
l1,r1,l2,r2を読み込む。ans=0を用意する。
l1<l2かつr1>l2のとき、ans=r1-l2。
l1>l2かつr2>l1のとき、ans=r2-l1。
l1==l2のとき、r1>r2のときans=r2-l1、それ以外のときans=r1-l1。
ansを出力する。
WA2個。

# Main
l1,r1,l2,r2を読み込む。ans=0を用意する。
l1<l2かつr1>l2のとき、ans=min(r1, r2)-l2。
l1>l2かつr2>l1のとき、ans=min(r1, r2)-l1。
l1==l2のとき、r1>r2のときans=r2-l1、それ以外のときans=r1-l1。
ansを出力する。

# Main\_fix
Mainを元に、l1==l2のときもmin()を使うとif文が不要となる。
rt=min(r1, r2)とすると、l1,l2も同様にmax(l1, l2)と書ける。
ans=rt-ltがマイナスとなる場合があるので、max(0, ans)とする。

