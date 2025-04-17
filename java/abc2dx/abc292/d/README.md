# Main01
n,mを読み込み、Counter cnt、UnionFind ufを用意し、
iを0からn回ループし、u,vを読み込み、cntとufに(u,v)を追加する。
int[] aryを用意し、iを1からnまでループし、
	v=cnt.get(i).size()、r=uf.root(i)を入れ、ary[r]+=vを更新する。
	iを1からnまでループし、r=uf.root(i)、s=uf.size(i)を入れ、
	r==i && ary[r]!=sの場合ng。
	ループ終了したらok。
	WA4個。RE12個。

# Main02
Main01を元に、REがメモリ不足と誤解し、
Counter_int_listint20250413　から　Counter_int_int20250416　に変更し、
リンクのリストからリンク数に変える。
cnt.add(u, v);　を　cnt.inc(u);　に変更
int v=cnt.get(i).size();　を　int v=cnt.get(i);　に変更
	WA4個。RE12個。

# Main
Main02を元に、u,vを読み込みがn回ループしていた。m回ループに修正する。
AC

# Main01\_fix
Main01を元に、u,vを読み込みがn回ループを、m回ループに修正する。
AC

