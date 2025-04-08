# Main
sを読み込み、char[] aryを用意する。
iを0からs.length回ループし、idx=s.charAt(i)-'a'を入れ、ary[idx]++にカウントする。
iを0から26回ループし、ary[i]==0の場合i+'a'を出力して終了する。
ループ終了したらNoneを出力する。
