# Main01
nを読み込み、List<Integer> la,lb,lcに読み込む。
ans = 0を用意し、iを0からla.size()回ループし、p = la.get(i)を用意する。
jを0からlb.size()回ループし、q = lb.get(j)を用意する。
p < qの場合、kを0からlc.size()回ループし、r = lc.get(k)を用意する。
q < rの場合、ans++をカウントする。
ansを出力する。
TLE25個。

# Main02
Main01を元に、
q < rの場合、ans += lc.size() - kを加える。
TLE25個。

# Main03
Main02を元に、
lb.add(ib[i])を追加する。
を
la.get(0) < ib[i]の場合lb.add(ib[i])を追加する。
に修正。

lc.add(ic[i])を追加する。
を
lb.get(0) < ic[i]の場合lc.add(ic[i])を追加する。
に修正。

iをlb.size() - 1から0まで-1ループし、
lc.get(lc.size() - 1) <= lb.get(i)の場合、lb.remove(i)を取り除く。
それ以外は中断する。

iをla.size() - 1から0まで-1ループし、
lb.get(lb.size() - 1) <= la.get(i)の場合、la.remove(i)を取り除く。
それ以外は中断する。
TLE16個、RE11個。

# Main04
Main03を元に、
iを0からlb.size()回ループし、v = lb.get(i)を用意し、
cnta = smallSearch(la, v)、cntc = bigSearch(lc, v)を呼び出し、
ans += cnta * cntcを加える。

smallSearchの中で、
l = 0、r = list.size() - 1を用意し、l < rの間ループし、
m = (l + r) / 2を用意し、vv = list.get(m)を入れ、
v <= vvの場合r = m - 1を入れ、それ以外はl = m + 1を入れる。
list.get(l) >= vの間ループし、l--を入れる。
l + 1を返す。

bigSearchの中で、
l = 0、r = list.size() - 1を用意し、l < rの間ループし、
m = (l + r) / 2を用意し、vv = list.get(m)を入れ、
v >= vvの場合l = m + 1を入れ、それ以外はr = m - 1を入れる。
list.get(l) <= vの間ループし、l++を入れる。
list.size() - lを返す。
RE11個。

# Main05
Main04を元に、
smallSearchの中で、
l < rの間ループし、
を
l <= rの間ループし、
に修正。

v <= vvの場合r = m - 1、それ以外はl = m + 1
を
v > vvの場合l = m + 1、それ以外はr = m - 1
に修正。

list.get(l) >= vの間ループし、l--を入れる。
を
l >= list.size()の場合l = list.size() - 1を入れる。
l >= 0 && list.get(l) >= vの間ループし、l--を入れる。

bigSearchの中で、
l < rの間ループし、
を
l <= rの間ループし、
に修正。

v >= vvの場合
を
v > vvの場合
に修正。

list.get(l) <= vの間ループし、l++を入れる。
を
l < 0の場合l = 0を入れる。
l < list.size() && list.get(l) <= vの間ループし、l++を入れる。
RE11個。

# Main06
smallSearchの中で、
v == vvの場合l = mを入れ、中断する。

bigSearchの中で、
v == vvの場合r = mを入れ、中断する。

l < 0の場合l = 0を入れる。
l < list.size() && list.get(l) <= vの間ループし、l++を入れる。
list.size() - lを返す。
を
r < 0の場合r = 0を入れる。
r < list.size() && list.get(r) <= vの間ループし、r++を入れる。
list.size() - rを返す。
RE11個。

# Main07
Main06を元に、
la.get(0) < ib[i]の場合lb.add(ib[i])を追加する。
を
lb.add(ib[i])を追加する。
に修正。

lb.get(0) < ic[i]の場合lc.add(ic[i])を追加する。
を
lc.add(ic[i])を追加する。
に修正。

iをlb.size() - 1から0まで-1ループし、
lc.get(lc.size() - 1) <= lb.get(i)の場合、lb.remove(i)を取り除く。
それ以外は中断する。
を削除。

iをla.size() - 1から0まで-1ループし、
lb.get(lb.size() - 1) <= la.get(i)の場合、la.remove(i)を取り除く。
それ以外は中断する。
を削除。
TLE4個。

# Main08
smallSearchの中で、
v == vvの場合l = mを入れ、中断する。
を削除。

bigSearchの中で、
v == vvの場合r = mを入れ、中断する。
を削除。
TLE4個。

# Main
Main08を元に、
pre = -1、precnt = -1を用意する。
pre >= 0 && pre == vの場合、ans += precntを加える。
それ以外はcnta = smallSearch(la, v)、cntc = bigSearch(lc, v)、ans += cnta * cntcを加え、
pre = v、precnt = cnta * cntcを入れる。
AC 574ms

# Main\_fix
書き直す。

nを読み込み、int[][] aryに読み込み、ary[i]をソートする。
ans=0を用意し、iを0からn回ループし、b=ary[1][i]を用意する。
i1=binarySearch(ary[0], b)、i2=binarySearch(ary[2], b+1)を呼び出し、
x=i1、x*=n-i2を入れ、ans+=xを加える。
ansを出力する。

binarySearchの中で、
lt=0、rt=ary.length-1を用意し、
lt<=rtの間ループし、mid=(lt+rt)/2を入れ、
ary[mid]<xの場合lt=mid+1、それ以外はrt=mid-1を入れる。
ループ終了したらltを返す。
AC 704ms
