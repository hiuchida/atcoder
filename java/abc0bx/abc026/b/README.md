# Main
nを読み込み、int[] aryに読み込み、ソートする。
ans=0、bplus=trueを用意し、iをn-1から0までループし、
sign=bplus ? 1 : -1、ans+=sign\*Math.PI\*ary[i]\*ary[i]、bplus=!bplusを入れる。
ansを出力する。
