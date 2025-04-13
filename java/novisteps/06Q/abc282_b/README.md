# Main
n,mを読み込み、String[] aryに読み込む。
ans=0を用意し、iを0からn-1までループし、jをi+1からn-1までループし、
s1=ary[i]、s2=ary[j]、bok=trueを用意し、kを0からs1.length回ループし、
s1.charAt(k)=='x' && s2.charAt(k)=='x'の場合、bok=falseを更新する。
ループ終了したら、bokの場合ans++をカウントする。

ansを出力する。
