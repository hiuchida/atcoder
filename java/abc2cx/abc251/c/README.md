# Main
nを読み込み、TreeSet<String> setを用意する。
ans=0、high=0を初期化する。
iを1からnまでループし、s,tを読み込む。
setにsが存在する場合スキップする。それ以外はsetにsを追加する。
high<tならば、ans=i、high=tを更新する。
ループが終了したらansを出力する。
