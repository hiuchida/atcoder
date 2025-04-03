# Main01
h,w,rs,cs,nを読み込み、Counter cntx,cntyを用意し、
n回ループし、r,cを読み込み、cntxに(c, r)、cntyに(r, c)を追加する。
qを読み込み、q回ループし、d,lを読み込み、
dが"U"の場合、v=cntx.get(cs)、vv=v.lower(rs)を得て、
vv==nullならばvv=1、それ以外はvv++。
ll=rs-vvを入れ、rs-=min(l, ll)を更新する。
dが"D"の場合、v=cntx.get(cs)、vv=v.higher(rs)を得て、
vv==nullならばvv=w、それ以外はvv--。
ll=vv-rsを入れ、rs+=min(l, ll)を更新する。
dが"L"の場合、v=cnty.get(rs)、vv=v.lower(cs)を得て、
vv==nullならばvv=1、それ以外はvv++。
ll=cs-vvを入れ、cs-=min(l, ll)を更新する。
dが"R"の場合、v=cnty.get(rs)、vv=v.higher(cs)を得て、
vv==nullならばvv=h、それ以外はvv--。
ll=vv-csを入れ、cs+=min(l, ll)を更新する。
rs,csを出力する。
WA4個。

# Main
Main01を元に、
vv==nullならばvv=w　を　vv=h　に修正
vv==nullならばvv=h　を　vv=w　に修正
（"U","D"と"R","L"を反対に書いていて、入れ替えたときに、修正漏れ）

