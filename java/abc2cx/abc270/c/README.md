# Main
n,x,yを読み込み、Counter cntを用意し、cntに(u,v)と(v,u)を追加する。
List<Integer> listとboolean[] flagを用意し、dfs(x)を呼び出す。

dfs内でlistにiを追加する。
flag[i]=trueをセットし、i==yならばlistを出力し、終了する。
cnt.get(i)の全要素をループし、flag[nxt]==falseの場合にdfs(nxt)を呼び出す。
ループが終了したら、listの末尾を取り除き、flag[i]=falseをリセットする。
