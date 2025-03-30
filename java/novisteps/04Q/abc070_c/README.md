# Main
nを読み込み、long[] aryに読み込む。
ans=ary[0]を用意し、iを1からn-1までループし、
gcd=gcd(ans, ary[i])を計算し、ans/gcd*ary[i]を更新する。
ループが終了したら、ansを出力する。
