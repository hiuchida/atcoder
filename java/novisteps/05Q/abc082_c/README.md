# Main
nを読み込み、int[] aryに読み込む。
Counter cntを用意し、cntにary[i]を追加する。

ans=0を用意し、cntの要素kでループし、v=cnt.get(k)に入れる。
v<kならば、ans+=vを加え、v>kならば、ans+=v-kを加える。
ループ終了したら、ansを出力する。
