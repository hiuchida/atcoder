# Main
n,mを読み込み、List<List<Integer>> listを用意する。
iをm回ループし、kを読み込み、List<Integer> lstを用意する。
jをk回ループし、sを読み込み、lstに追加する。
ループ終了したら、listにlstを追加する。
int[] aryを用意し、iをm回ループし、ary[i]に読み込む。
ans=0を用意し、iを0から2^n回ループし、Set<Integer> setを用意する。
jを0からn回ループし、mask=1 << jを用意し、(i&mask)>0ならばsetにj+1を追加する。

bng=falseを用意し、jを0からm回ループし、c=0を用意する。
list.get(j)の要素vをループし、setにvが存在すればc++をカウントする。
c%2!=ary[j]ならばbng=trueに更新する。
ループ終了したら、bng==falseの場合、ans++をカウントする。
ループ終了したら、ansを出力する。
