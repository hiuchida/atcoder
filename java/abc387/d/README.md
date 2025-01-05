# Main0
Cが解けそうですぐに方針が固まらないので、先にD。
最初、スタート地点から、y軸方向と、x軸方向の2つをbfsのqueに入れたが、
どうにも上手くいかず、queのループを丸ごとコピペして、y軸方向と、x軸方向を
別々に実行したところまではよかったが、mapに最短距離を入れてあるのを
クリアするのを忘れていた。
WAが出てすぐに気が付いた。

# Main
すぐにmapのコピーを作ろうと思ったが、2次元配列で面倒なので、
int[][] map;とint[][] map2;を用意して、入力データを読み込むときに
両方とも初期化して、途中でqueのループを切り替えるときに、
map=map2;で参照値を変えてやった。

オリジナルを保存して、コピーする場合も2倍かかるから変わらんでしょ。
まあ、壁-1を残して、通路を全部0にクリアするとかあったかもだけど、
スタート地点を1で初期化とかあるので。。。

解説の白黒判定を(x+y)%2の余りで決めるというのはなかなかスマートだが、
Pointクラスに"UD"と"LR"の方向を持たせて、次の方向を限定した。

# Main_fix
Mainから動作の修正。
map2を止め、resetmapで1以上を0にクリアする。スタート地点をbfsの冒頭で1にセットする。
(ans > val-1)の判定を止め、行き止まりになるまで続行しても間に合う。
(map[p.y-1][p.x] == 0 || map[p.y-1][p.x] > map[p.y][p.x] + 1)の判定を0の判定のみにする。
（おそらくresetmapしていないときに問題があったらしいが、今は一度訪問した場所に短いステップで到達することはない）
Mazeクラスを作成し、map操作をメソッド化。

デバッグ出力で、ゴール（####PQP#）してもまだ探している。
#################################################################
#4343478ZaZadehilmlmpqtuxy12569ADEHI.....kjk....z......CBC...SRS#
#521256#YXYbcfgjkjknorsvwz.3478BCFGJ#####hi#####yx#####9A####PQP#
#43#347#VWZadehihilmpqtuxy12569ADEHI....#gf#...#vw....#87#...ONO#
#56#####UT####gfg####rs####34#CB#FGJ#####de#...#ut#####56#####ML#
#87#JKJ#RS#..#hed#..#ut#..#65#DE#IHI#....cb#...#rs#....434...#JK#
#9A#IHI#QP#####bc####vw####78####JKJ#####Za#####qp#####12#####IH#
#CBCFGJKNORSVWZad#521yx#HEDA9ADE#MLMPQTUXYbcfgjknorsvwz.3478BCFG#
#DEDEHILMPQTUXYbc#43.z.#GFCBCBCF#NONORSVWZadehilmpqtuxy12569ADEH#
#################################################################

#################################################################
#632367.dcdcdghklopstwx.14589CDGHKLOP...rqr.....323...JIJ.....VU#
#541458#ababefijmnqruvyz2367ABEFIJMN#####po#####.1#####HG####TST#
#67#767#ZYZcdghklopstwx.14589CDGHKLOP...#mn#...#zyz...#EF#....RQ#
#98#####WX####ijm####zy####BA#EF#NMN#####lk#...#wx#####DC#####OP#
#AB#NMN#VU#..#lkl#..#.1#..#CD#HG#OPO#...jij#...#vu#...BAB....#NM#
#DC#KLO#ST#####ji####32####FE####RQR#####hg#####st#####98#####KL#
#EFIJMNQRUVYZcdgh#98545#LKHGHKLO#STSTWXabefijmnqruvyz2367ABEFIJM#
#HGHKLOPSTWXabefi#A7676#MJIJIJMN#VUVUVYZcdghklopstwx.14589CDGHKL#
#################################################################

# Main_final
Main_fixから(ans > val-1)の判定を追加。

デバッグ出力で、ゴール（####P.P#）したら打ち切り。
#################################################################
#4343478ZaZadehilmlmpqtuxy12569ADEHI.....kjk....z......CBC......#
#521256#YXYbcfgjkjknorsvwz.3478BCFGJ#####hi#####yx#####9A####P.P#
#43#347#VWZadehihilmpqtuxy12569ADEHI....#gf#...#vw....#87#...ONO#
#56#####UT####gfg####rs####34#CB#FGJ#####de#...#ut#####56#####ML#
#87#JKJ#RS#..#hed#..#ut#..#65#DE#IHI#....cb#...#rs#....434...#JK#
#9A#IHI#QP#####bc####vw####78####JKJ#####Za#####qp#####12#####IH#
#CBCFGJKNORSVWZad#521yx#HEDA9ADE#MLMPQTUXYbcfgjknorsvwz.3478BCFG#
#DEDEHILMPQTUXYbc#43.z.#GFCBCBCF#NONORSVWZadehilmpqtuxy12569ADEH#
#################################################################

#################################################################
#632367.dcdcdghklopstwx.14589CDGHKLOP...rqr.....323...JIJ.......#
#541458#ababefijmnqruvyz2367ABEFIJMN#####po#####.1#####HG####...#
#67#767#ZYZcdghklopstwx.14589CDGHKLOP...#mn#...#zyz...#EF#......#
#98#####WX####ijm####zy####BA#EF#NMN#####lk#...#wx#####DC#####OP#
#AB#NMN#VU#..#lkl#..#.1#..#CD#HG#OPO#...jij#...#vu#...BAB....#NM#
#DC#KLO#ST#####ji####32####FE####RQR#####hg#####st#####98#####KL#
#EFIJMNQRUVYZcdgh#98545#LKHGHKLO#STSTWXabefijmnqruvyz2367ABEFIJM#
#HGHKLOPSTWXabefi#A7676#MJIJIJMN#VUVUVYZcdghklopstwx.14589CDGHKL#
#################################################################
