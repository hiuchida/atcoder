# Main
n,kを読み込み、int[] aryを読み込む。
k==1は常にok。
List<List<Integer>> listを用意し、k個ArrayList<>()を追加する。
iを0からn-1までループし、list.get(i%k)にary[i]を追加する。
iを0からk-1までループし、list.get(i)をソートし、逆順にする。
無限ループし、x=-1、bFlag=falseに初期化する。
iを0からk-1までループし、List<Integer> lst=list.get(i)を入れる。
lstのサイズ>0の場合、末尾の最小値を取り除きyに入れる。
x>yならばng。
x=y、bFlag=trueに更新する。
ループが終了したら、bFlag==falseならばループを中断する。
ループが終了したらok。
