# Main
n,mを読み込み、List<List<Integer>> listを用意する。
iを0からm回ループし、List<Integer> lstを用意し、kを読み込み、
jを0からk回ループし、lst.add(sc.nextInt())を追加する。

list.add(lst)を追加する。

int[] aryを用意し、iを0からn回ループし、bを読み込み、ary[b]=i+1を入れる。

int[] cntを用意し、iを0からm回ループし、max=0、lst=list.get(i)を入れ、
jを0からlst.size()回ループし、v=lst.get(j)を入れ、
maxにary[v])の最大値を更新する。

cnt[max]++をカウントする。

ans=0を用意し、iを1からnまでループし、ans+=cnt[i]を更新し、ansを出力する。
