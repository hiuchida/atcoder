# Main
nを読み込み、int[] aryに読み込み、ans=0とint[] tblを用意する。
iを0からn-1までループし、tbl[0]=1を入れる。
jを3から0まで-1ループし、
tbl[j]>0の場合、tbl[j]=0し、nxt=j+ary[i]を計算し、
nxt>3ならばans++、それ以外はtbl[nxt]=1する。
2重ループが終了したら、ansを出力する。
