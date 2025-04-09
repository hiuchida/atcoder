# Main
n,qを読み込み、Counter cntを用意する。
q回ループし、t,a,bを読み込む。
t==1の場合、cntに(a, b)を追加する。
t==2の場合、cntから(a, b)を削除する。
t==3の場合、cnt.is(a, b) && cnt.is(b, a)のときok、それ以外はng。
