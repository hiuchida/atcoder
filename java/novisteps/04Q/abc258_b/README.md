# Main
nを読み込み、String[] asに読み込む。
int[][] aryとmax=0を用意し、ary\[i\]\[j\]に入れ、最大値をmaxに更新する。
n桁の'0'のansを用意する。
iとjを0からn-1までループし、ary[i][j]==maxのとき、searchを呼び出す。
ループが終了したら、ansを出力する。

searchの中で、char[] caを用意し、ca\[0\]にary[i][j]の文字を入れる。
dをDYのサイズ回ループし、ii=i、jj=jを用意し、
hを1からn-1までループし、ii+=DY[d]+n、ii%=n、jjも同様にn歩進める。
ca[h]にary[ii][jj]の文字を入れる。
ループが終了したら、caから文字列sを作成し、ansと比べて最大値を更新する。
