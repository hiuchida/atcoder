# Main01
sを読み込み、char[] aryに展開する。
int[] cnt,cnt2を用意し、cnt\[\]を1で初期化する。
loop=nを用意し、loop%2==1の場合loop++する。
jをloop回ループする。
bdone=trueを用意し、cnt2\[\]を0で初期化する。
iを0からn回ループし、cnt[i]>0の場合、
ary[i]=='L'の場合、cnt2[i-1]+=cnt[i]を加える。それ以外の場合、cnt2[i+1]+=cnt[i]を加える。
bdone=falseにリセットする。
ループ終了したら、cnt2\[\]をcnt\[\]へコピーする。
bdoneの場合中断する。
ループ終了したら、cnt\[\]を出力する。
TLE16個。

# Main02
Main01を元に、int[] cnt　の代わりに　Counter cnt　で各添字の個数を管理する。
Counter cntを用意し、iを0からn-1までループし、cnt.inc(i)で初期化する。
jをloop回ループし、Counter cnt2を用意し、
cnt.keySet()の要素iをループし、v=cnt.get(i)を入れる。
ary[i]=='L'の場合、cnt2.add(i-1, v)を加え、それ以外はcnt2.add(i+1, v)を加える。
ループ終了したら、cnt=cnt2に入れる。
ループ終了したら、cnt.get(i)を出力する。
bdoneは、最後2回周期で循環するので、削除。
TLE17個。

# Main03
Main02を元に、
Counter bak1,bak2を用意する。
cnt=cnt2に入れた後で、cnt.equals(bak2)の場合中断する。
j%2==0の場合、bak1=cntを入れる。それ以外は、bak2=cntを入れる。
TLE12個。

# Main
ダブリングで作り直し。
m=18のつもりが勘違いでm=16で書いてしまいWA4個。
m=17ならばACだった。

# Main\_TestData
10^5件のRと10^5のLのテストデータを作成した。

