# Main
n,kを読み込み、int\[\] aryに読み込む。
int\[\] idxを用意し、idx\[ary\[i\]\]=iを入れる。
TreeSet<Integer> set、ans=INT\_MAXを用意する。
iを1からkまでループし、setに追加する。
xにset.last()-set.first()を入れ、xの最小値をansに更新する。
iを2からi+k-1<=nまでループし、setからidx[i-1]を取り除き、idx[i+k-1]を追加する。
xにset.last()-set.first()を入れ、xの最小値をansに更新する。
ループが終了したらansを出力する。
