# Main
3件をString[] aryに読み込む。
int[] pos、x=0を用意する。
無限ループする。
p=pos[x]に入れ、p==ary[x].length()の場合、tbl.charAt(x)を出力して、終了する。
ch=ary[x].charAt(p)に入れ、pos[x]++を加算し、x=ch-'a'を入れる。
