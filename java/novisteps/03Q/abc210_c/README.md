# Main
n,kを読み込み、int[] aryに読み込む。
Counter cntを用意し、iを0からk回ループし、cntにary[i]を加算する。
ans=0を用意し、ansにcntサイズの最大値を更新する。
iをkからn-1までループし、cntからary[i-k]を減算し、ary[i]を加算する。
ansにcntサイズの最大値を更新する。
ansを出力する。
