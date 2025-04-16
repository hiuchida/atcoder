# Main
n,mを読み込み、int[] aryに読み込む。
List<Twin> lstを用意し、iを0からm回ループし、jをiからj+1<mまでループし、ary[j]+1!=ary[j+1]の場合中断する。
lstにTwin(i, j)を追加し、i=jを入れる。

int[] ansを用意し、iを0からn回ループし、ans[i]=i+1を入れる。

lstの要素tをループし、a=ary[t.a]、b=ary[t.b]+1を入れ、
iを0からi<=(b-a)/2までループし、swap(ans, a+i, b-i)を呼び出す。

ans\[\]を出力する。
