# Main01
うっかりミス。
if (v == p.r) { println(v);しても例題は正しいが、vはdから計算した余り。

# Main02
Main01のミスに気が付かず、d += p.q - v + p.r;がオーバーフローしていると勘違いし、
long dd;に変更。

# Main
Main01のprintln(v);をprintln(d);に変更したもの。

# Main_elseif
if (v == p.r)が特別と見なしてcontinue;していたが、
<,==,>が対等で、最終的にprintln(d)するので、if-elseif-elseに変える。
