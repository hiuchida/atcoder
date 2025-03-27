# Main
n,kを読み込む。
Count[] aryを用意し、iを0からn-1までループし、
sを読み込み、各文字のカウントをary\[i\]に入れる。
dfs()を呼び出し、2^nの全探索する。
終了したansを出力する。

dfs()の中で、i==nならばcnt\[i\]==kとなるカウントを取り、ansの最大値を更新する。
それ以外ならば、c1を用意し、cをコピーして、dfs(i+1)を呼び出す。
その後にc1にary[i]を加えて、dfs(i+1)を呼び出す。

dfsを作る際にboolean\[\] flagを用意したが、常にfalse/trueの2分岐のため未使用。

# Main\_fix
boolean\[\] flagを削除する。

# Main\_bit
bit探索で0から2^n-1までループし、ビットが立っているary\[i\]を加えて、ansの最大値を更新する。

