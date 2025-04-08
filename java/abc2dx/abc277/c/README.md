# Main
nを読み込み、Counter cntを用意する。
iをn回ループし、a,bを読み込み、cntに(a,b)と(b,a)を追加する。
ans=1、Deque<Integer> que、Set<Integer> setを用意し、
queとsetに1を追加する。
queが空になるまでループし、
iにqueから取り出す。
ansをiの最大値で更新する。
cnt.get(i)から要素nxtをループし、
setにnxtが存在したらスキップする。
setにnxtを追加し、queにnxtを追加する。
ループが終了したら、ansを出力する。
