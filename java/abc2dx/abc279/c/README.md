# Main
h,wを読み込み、String[] as,atに読み込む。
as2=convert(h, w, as)、at2=convert(h, w, at)を呼び出し縦横入れ替えて、ソートする。
iを0からas2.length回ループし、!as2[i].equals(at2[i])ならばng。
ループ終了したらok。

convertの中で、String[] ansを用意し、
xを0からwまでループし、StringBuilder sbを用意し、
yを0からhまでループし、sbにary[y].charAt(x)を追加する。
ループ終了したら、ans[x]=sb.toString()を入れる。
ループ終了したら、ansを返す。
