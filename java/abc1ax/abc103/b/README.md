# Main
s,tを読み込み、char\[\] as,atに展開する。
iを0からn-1までループし、bok=trueを用意し、
jを0からn回ループし、as[(n-i+j)%n]!=at[j]の場合、bok=falseにリセットする。
bokの場合ok。
ループ終了したらng。
