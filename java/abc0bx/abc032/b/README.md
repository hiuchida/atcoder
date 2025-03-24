# Main
s,kを読み込み、TreeSet<String> setを初期化する。
iを0からs.length()-kまでループする。
部分文字列ssにsubstring(i, i+k)を入れる。
setにssが存在しなければsetにssを追加する。
ループが終了したらset.sizeを出力する。
