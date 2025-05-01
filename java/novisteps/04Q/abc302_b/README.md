# Main
h,wを読み込み、String[] aryに読み込む。
y,xを0からh-1,w-1までループし、check(y, x)を呼び出す。

checkの中で、
ch=ary[y].charAt(x)を用意し、ch!='s'の場合、戻る。
dを0からDY.length回ループし、iを1からtbl.length()-1までループし、
ey=y+DY[d]*i、ex=x+DX[d]*iを入れ、ey<0 || h-1<ey || ex<0 || w-1<exの場合中断する。
ch=ary[ey].charAt(ex)を入れ、ch!=tbl.charAt(i)の場合中断する。

i==tbl.length()の場合、iを0からtbl.length()回ループし、
y+DY[d]*i+1とx+DX[d]*i+1を出力して、終了する。
