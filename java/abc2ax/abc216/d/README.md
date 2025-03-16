# Main01,Main02
List<Deque<Point>> listに筒の中のボールをPoint(a,i)で管理する。
TreeSet<Point> setに一番上のボールを管理する。
boolean[] flagにsetに追加済か管理する。
flag==falseならばqueからボールを取出しsetと重複チェックする。
重複していたら2つのボールをqueから削除する。
重複していないのならsetに追加する。
TLE5個。
queが空になったらflag=trueにしてスキップする。
TLE5個。

# Main
for (int i=0; i<m; i++) {で毎回ループせずに、
チェック対象の添字をDeque<Integer> qに登録し、
queが空でない場合のみqに追加する。
