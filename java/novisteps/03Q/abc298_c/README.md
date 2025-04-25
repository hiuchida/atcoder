# Main
n,qを読み込み、Counter[] cntを用意し、cnt[i]=new Counter()を初期化する。
Counter2 cnt2を用意する。
Counterは、値vの個数wを管理する。
Counter2は、値kに対する重複しないvのセットを管理する。
q回ループし、cmd,iを読み込み、
cmd==1の場合、jを読み込み、cnt[j-1].inc(i)にカウントし、cnt2.add(i, j)に追加する。
cmd==2の場合、c=cnt[i-1]を入れ、c.keySet()の要素keyでループし、
kを0からc.get(key)回ループし、keyを出力する。
cmd==3の場合、cnt2.get(i)の要素vでループし、vを出力する。
