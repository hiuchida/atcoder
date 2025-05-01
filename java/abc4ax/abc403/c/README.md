# Main
n,m,qを読み込み、boolean[] flag、Counter cntを用意する。
q回ループし、cmd,xを読み込み、
cmd==1の場合、yを読み込み、cnt.add(x, y)を追加する。
cmd==2の場合、flag[x]=trueをセットする。
cmd==3の場合、yを読み込み、flag[x]の場合ok。
cnt.get(x).contains(y)の場合ok。
それ以外はng。
