# Main01,Main02,Main03,Main04,Main05,Main06,Main07,Main08
TreeSet<Point> setとUnionFind ufを組み合わせて回答。WA1個、RE7個。
REが出るのは珍しい。

# Main
ソースを削りに削って、最後に気が付いたのが、2次元座標を1次元座標に変換する際に、
y*w+x
とするべきところを
y*h+x
と書いていた。
h!=wのテストデータを試さなかったのが原因。

ひょっとしてREはArrayIndexOutOfBoundsExceptionならば、
UnionFindを2000x2000固定（1baseなら2001x2001固定）で確保すれば実行できていたかも。

# Main01_fix
Main01から
return (y-1)*w+(x-1);
のみ修正したところ、正解となった。

# Main01_4e6
Main01から
uf = new UnionFind(2000*2000, 2000);
のみ修正したところ、WA1個となった。

これはこれで原因を探るのは難しいか。

# Main_final
Main02から最も相応しいソース。
赤点の存在チェックは、TreeSet<Point> set。（途中からboolean[][]に切り替えたが密）
new UnionFind(h, w);で2次元座標を1次元配列で管理。
set.contains(p2)の存在チェックが範囲チェックも効くので、isAvail(p2)は不要。
