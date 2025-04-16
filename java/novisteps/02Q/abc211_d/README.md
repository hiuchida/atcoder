# Main
n,mを読み込み、Counter cntを用意する。
m回ループし、u,vを読み込み、cntに(u,v)と(v,u)を追加する。
int[] costを用意し、-1で初期化し、cost[1]=0を入れる。
long[] ansを用意し、ans[1]=1を入れる。
Deque<Integer> queを用意し、queに1を追加する。
queが空になるまでループし、curにqueから取り出す。
cnt.get(cur)の要素nxtをループし、
cost[nxt]<0の場合、cost[nxt]=cost[cur]+1、ans[nxt]=ans[cur]を入れ、queにnxtを追加する。
cost[nxt]==cost[cur]+1の場合、ans[nxt]=modadd(ans[nxt], ans[cur])を更新する。

ans[n]を出力する。
