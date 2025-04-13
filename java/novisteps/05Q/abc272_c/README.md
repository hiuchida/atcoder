# Main
nを読み込み、int[] aryに読み込み、ソートする。
List<Integer> lo,leを用意し、n回ループし、a=ary[i]を入れ、
aが奇数ならばloにaを追加し、偶数ならばleにaを追加する。
ao=-1を用意し、
lo.size()>1の場合、aoにloの最大値＋2番目に大きい値を入れる。
ae=-1を用意し、
le.size()>1の場合、aeにleの最大値＋2番目に大きい値を入れる。
ansをmax(ao,ae)を入れ、ansを出力する。
（ao,aeのデフォルトが-1なので、両方-1でも正しく-1を出力する）
