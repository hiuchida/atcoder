# Main
前半と後半を入れ替えるのにコストがかかるので、bChangeで管理する。
idx(int i)で1baseを0baseに変換し、bChangeによる補正を行う。
文字列を生成するのは最後の1回なので、bChangeで反転する場合は、
もう1つchar[]を作って入れ替えながらコピーする。
