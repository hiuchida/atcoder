# Main
sを読み込み、iをs.length()-1から1までループする。
ssにsの先頭i文字を切り取る。
check(ss)を呼び出し、trueならばss.length()を出力する。

checkの中で、len=s.length()を用意し、len%2==1ならばfalse。
iを0からlen/2回ループし、s.charAt(i)!=s.charAt(i+len/2)ならばfalse。
ループ終了したらtrue。
